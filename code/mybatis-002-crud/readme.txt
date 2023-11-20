使用mybatis完成crud

1. 什么是crud？
    c ：create
    r : Retrieve
    u : update
    d : delete

2. insert
    <insert id="insertCar">
            INSERT INTO t_car
            VALUES (null, 100, "宝马520Li", 41.00, "2023-11-12", "燃油车");
    </insert>
    这样写的问题是?
        值写死到配置文件中
        在实际开发中是不存在的
        一定是前端的form表单提交过来数据,然后将值传给sql语句
    例如: JDBC的代码时怎么写的


    在JDBC当中占位符采用的是?,mybatis当中是什么呢?
        和 ? 等效的写法是: #{}
        在mybatis当中不能使用?,必须使用#{} 来代替JDBC当中的 ?
        #{} 和 JDBC当中的 ? 是等效的

    java程序中使用map可以给sql语句的占位符传值
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "1002");
        map.put("k2", "比亚迪汉");
        map.put("k3", 10.00);
        map.put("k4", "2023-11-16");
        map.put("k5", "电车");

        INSERT INTO t_car VALUES (null, #{k1}, #{k2}, #{k3}, #{k4}, #{k5});
        注意: #{这里面写什么? 写map集合的key,如果key不存在,获取的是null}

        一般map集合的key起名要见名知意
            map.put("car_num", "1002");
            map.put("brand", "比亚迪汉");
            map.put("guide_price", 10.00);
            map.put("produce_time", "2023-11-16");
            map.put("car_type", "电车");

            INSERT INTO t_car VALUES (null, #{car_num}, #{brand}, #{guide_price}, #{produce_time}, #{car_type});

    java程序中使用pojo类给sql语句的占位符传值:
        Car car = new Car(null, "1003", "比亚迪秦plus", 20.00, "2023-11-16", "电车");
        sql语句的大括号里写什么呢?
            写pojo类的属性名
        INSERT INTO t_car VALUES (null, #{car_num}, #{brand}, #{guide_price}, #{produce_time}, #{car_type});

        如果把sql语句写成这个德行
            INSERT INTO t_car VALUES (null, #{xyz}, #{brand}, #{guide_price}, #{produce_time}, #{car_type});
        出现了什么问题呢?
            会报一个错误: There is no getter for property named 'xyz' in 'class pojo.Car'
            因为mybatis会去找 Car类中getXyz()方法,但是没找到;
        怎么解决的?
            可以在Car类中提供一个getXyz()方法,这样问题就解决了
        通过这个测试,得出一个结论:
            严格意义上来说: 如果使用pojo对象传递值的话,#{}中到底写什么?
                写的是get方法的方法名去掉get,然后将剩下的首字母小写,最后得到的就是你要写的
                例如: getUsername() --> #{username}
                例如: getEmail() --> #{email}
                ...
        也就是说mybatis在底层给?传值的时候,先要获取值, 怎么获取的?
            调用了pojo对象的get方法.例如: car.getCar_num(), car.getCar_type(), car.getBrand()

3. delete
    <delete id="deleteCarById">
        delete from t_car where id = #{id}
    </delete>

    Car car = new Car(2L, null, null, null, null, null);
    int affectRows = sqlSession.delete("deleteCarById", car);

    如果占位符只有一个, 那么#{}可以随便写,但最好还是见名知意,并且 调用delete方法传第二个参数的时候可以不需要传入一个对象

4. update
    * 需求:根据id修改某条记录

    实现:
        <update id="updateCarById">
            update t_car
            set brand=#{brand},
                guide_price=#{guide_price},
                produce_time=#{produce_time},
                car_type=#{car_type}
            where id = #{id};
        </update>

        Car car = new Car(3L, "10086", "五菱宏光", 100.00, "2023-11-16", "燃油车");
        int count = sqlSession.update("updateCarById", car);

5. select(需求:根据主键查询一个)
    需求:根据id查询

    实现

        <select id="selectCarById">
            select *
            from t_car
            where id = #{id};
        </select>

        Object car = sqlSession.selectOne("selectCarById", 3);


        为什么现在你去执行会报错 It's likely that neither a Result Type nor a Result Map was specified.
            //mybatis底层执行了select语句之后,一定会返回一个结果集对象:ResultSet
            //JDBC中叫做ResultSet, 接下来就是mybatis应该从ResultSet中取出数据,封装Java对象
            因为你现在查询完了,mybatis会取出数据封装,但是你并没有告诉mybatis,封装成什么对象, 所以报错了
        怎么告诉mybatis要封装成什么对象?
            //  select标签加一个属性
            resultType="全限定类名(copy reference)"

        为什么老杜的结果有些没有值有些有值?
            因为老杜的pojo跟我的pojo中属性名不一样
                我的严格与数据库的列名一致
                老杜的没有下划线以及小驼峰命名法
                mybatis去查询结果集的时候, 例如mybatis在一次传值: 然后会调用resultType指定的类 Car中的setCarType(),
                    那么mybatis会根据你的方法名, carType(参考el,以及mybatis传参原理)去跟结果集的列名中找名为carType的列
                    但是结果集列名叫car_type, 找不到carType的列, mybatis默认给null了

        怎么解决?
            通过MySQL语句中的 as 关键字, 把查询结果的列名起别名(与Car类的属性名一致)
            日后还可以再优化, 不用起别名的土方法

6. select(查所有)

    <select id="selectCarAll" resultType="pojo.Car">
            select * from t_car;
    </select>

    List<Car> cars = sqlSession.selectList("selectCarAll");

    注意：
        告诉mybatis封装成什么对象， 还是在select标签中写属性： resultType="list集合中的数据类型(全限定类名)"


7. 命名空间
    情景:
        如果我们新建了一个UserMapper.xml 里面的内容只包含一条selectAll语句
        然后我们将其配置到mybatis-config.xml里面去
        我们写一个testNamespace()方法在去测试selectCarAll语句
        报错了
        selectCarAll is ambiguous in Mapped Statements collection (try using the full name including the namespace, or rename one of the entries)

    为什么会报错?
        因为mybatis去mapper文件中去找sql语句的时候,找到了id都叫做selectCarAll的语句
        mybatis不清楚你到底要用谁,就报错了

    解决方法:
        使用命名空间.sqlId传给方法是最规范的
        也就是说命名空间是为了防止sqlId命名冲突而设置的

ajax基本使用：
    发get
    发post
    用原生发送/手写的框架发送/jQuery发送