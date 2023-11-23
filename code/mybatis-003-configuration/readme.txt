* 笔记在mybatis-config.xml中的注释上
* 大部分配置是根据需求写的

第三章configuration主要内容:
1. properties
    <properties resource="mysql.properties">
    <properties>
        <property name="" value=""/>
        <property name="" value=""/>
        <property name="" value=""/>
    </properties>

2. 环境
    <environments default="">
        <environment id="">
            <datasource type=""></datasource>
        </environment>
    <environments>

3. 事务管理器
4. 数据源datasource
    池的几个配置

一个基本的mybatis核心配置文件
    configuration 根标签
        -- settings
            -- setting
        -- properties resource属性
            -- property name属性 value属性
        -- environments default属性
            -- environment id属性
                -- transactionManager type属性=JDBC|MANAGED
                -- datasource type属性=POOLED|UNPOOLED|JNDI
                    -- property name属性 value属性
        -- mappers
            -- mapper