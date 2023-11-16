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
