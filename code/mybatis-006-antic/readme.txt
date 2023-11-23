mybatis小技巧
1. #{}和${}的区别

#{}的执行结果
2023-11-22 15:19:35.845 [main] DEBUG mapper.CarMapper.selectByCarType - ==>  Preparing: select * from t_car where car_type=?;
2023-11-22 15:19:35.869 [main] DEBUG mapper.CarMapper.selectByCarType - ==> Parameters: 新能源(String)
2023-11-22 15:19:35.886 [main] DEBUG mapper.CarMapper.selectByCarType - <==      Total: 3

${}的执行结果
2023-11-22 15:29:54.614 [main] DEBUG mapper.CarMapper.selectByCarType - ==>  Preparing: select * from t_car where car_type=新能源;
2023-11-22 15:29:54.651 [main] DEBUG mapper.CarMapper.selectByCarType - ==> Parameters:

org.apache.ibatis.exceptions.PersistenceException:
### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column '新能源' in 'where clause'
### The error may exist in CarMapper.xml
### The error may involve defaultParameterMap
### The error occurred while setting parameters
### SQL: select * from t_car where car_type=新能源;
### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column '新能源' in 'where clause'

#{}和${}的区别:
    #{}: 底层使用PreparedStatement. 特点:先进行SQL语句的编译, 然后给SQl语句的占位符问号? 以值的方式传入.可以避免sql注入的风险
    ${}: 底层使用Statement. 特点: 先进性sql语句的拼接, 然后在对sql语句进行编译,存在sql注入的风险

#{}的执行结果
    Preparing: select * from t_car order by produce_time ?;
    Parameters: asc(String)
${}的执行结果
    Preparing: select * from t_car order by produce_time asc;
    Parameters:

综上所述: 如果需要sql语句的关键字放到sql语句中, 只能使用${}, 因为#{}是以值的形式放到sql语句中的

2. 向sql语句当中拼接表名, 就需要使用${}
    向SQL语句当中拼接表名，就需要使用$I现实业务当中，可能会存在分表存储数据的情况。因为一张表存的话，数据量太大。
    查询效率比较低。可以将这些数据有规律的分表存储，这样在查询的时候效率就比较高。
    因为扫描的数据量变少了。日志表: 专门存储日志信息的。
    如果t_Log只有一张表，这张表中每一天都会产生很多Log，慢慢的，这个表中数据会很多。
    怎么解决问题?
        可以每天生成一个新表。每张表以当天日期作为名称，例如:
        t_log_20220901
        t_log_20220902
        ...
    你想知道某一天的日志信息怎么办?
        假设今天是20220901，那么直接查: t_Log_20220901的表即可。

3. 批量删除
    批量删除的SQl语句有两种写法：
            第一种or：delete from t_car where id=1 or id=2 or id=3;    //日后学动态SQL解决
            第二种int: delete from t_car where id in(1,2,3)

        应该采用${}的方式

4. 模糊查询
    需求:根据汽车品牌进行模糊查询
        select * from t_car where brand like  %奔驰%'
        select * from t car where brand like %比亚迪%'
    第一种方案:
        brand like '%$tbrand}%'
    第二种方案: concat函数, 这个是mysql语法中的一个函数
        brand like concat('%', #{brand},'%')
    第三种方案: 鸡肋
        brand like concat('%','${brand}',%')
    第四种方案: 常用!
        brand like "%"#{brand}"%"

5. 关于mybatis别名机制
    在mybatis-config.xml中写标签, 然后就可以在mapper.xml中的resultType使用别名了
    <typeAliases>
        <typeAlias type="pojo.Car" alias="aaa"/>
    </typeAliases>
    type: 指定给哪个类型起别名
    alias: 指定别名
    注意:所有别名不区分大小写。
        namespace不可以起别名,必须写全限定接口名称
        alias可以省略, 那么alias默认是不带包名的简单类名
        <package name="pojo"/> 将这个包下的所有类全部自动起别名.别名就是简单类名,不区分大小写

6. mybatis-config.xml文件中的mappers标签
    <mapper resource="CarMapper.xml"/> 要求类的根路径下必须有: CarMapper.xml
    <mapper url="file:///d:/CarMapper.xml"/> 要求在d:/下有CarMapper.xml文件
    <mapper class="全限定接口名，带有包名"/>

    mapper标签的属性可以有三个:
        resource:这种方式是从类的根路径下开始查找资源。采用这种方式的话，你的配置文件需要放到类路径当中才行。
        url:这种方式是一种绝对路径的方式，这种方式不要求配置文件必须放到类路径当中，哪里都行，只要提供一个绝对路径就行。这种方式使用极少，因为移植性差
        cLass: 这个位置提供的是mapper接口的全限定接口名，必须带有包名的。
            思考: mapper标签的作用是指定SqLMapper.xml文件的路径，指定接口名有什么用呢?
                <mapper class="com.powennode.mybatis.mapper.CarMapper"/>
                如果你class指定是: com.powernode.mybatis.mapper.CarMapper
                那么mybatis框架会自动去com/powernode/mybatis/mapper目录下查找CarMapper.xml文件.
            注意:也就是说:如果你采用这种方式，那么你必须保证CarMapper.xml文件和CarMapper接口必须在同一个目录下。并且名字一致。
            CarMapper接口-> CarMapper.xml
            LogMapper接口-> LogMapper.xml
            ....
    提醒!!!!!!!!!!!!!!!!!!!!!!!
        在IDEA的resources目录下新建多重目录的话，必须是这样创建:
        com/powernode/mybatis/mapper
        不能这样:
        com.powernode.mybatis.mapper
7. 使用自动生成的主键值
    useGeneratedKeys="true" 使用自动生成的主键值
    keyProperty="id" 指定主键值赋值给对象的哪个属性.这个就表示将主键值赋值给Car对象的id属性
    <insert id="insertCarUseGeneratedKeys" useGeneratedKeys="true" keyProperty="id">
        insert into t_car values(#{id}, #{car_num},#{brand},#{guide_price},#{produce_time},#{car_type});
    </insert>
