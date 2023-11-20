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

configuration
    -- settings
        -- setting
    -- properties
        -- property
    -- environments
        -- environment
            -- transactionManager
            -- datasource
    -- mappers
        -- mapper