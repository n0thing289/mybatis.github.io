# Mybatis概述

## JDBC的不足

- sql语句一旦被编译，不好进行拓展增加查询字段时，要修改java代码再编译打包等等
- 问号传值繁琐
- 遍历结果集封装数据繁琐

## 了解Mybatis

- 使用的版本是 3.5.10

- mybatis的maven坐标

  ```xml
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.10</version>
  </dependency>
  ```

- Mybatis只有一个核心jar文件,导入依赖就可以使用这个框架了

- 认识ORM思想![](./document/001-ORM思想-对象关系映射.png)

- mybatis就是实现orm的一个框架

# MyBatis入门程序

- 组件版本

  ```xml
  <!--我自己有,-->
  Mysql驱动 5.17
  
  MyBatis 3.5.10
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.10</version>
  </dependency>
  
  logback 1.2.11
  ```

- 直接放到resources目录的东西，相当于放到类的根路径下

- 大部分核心笔记在每章的readme.txt中

# 使用mybatis完成CRUD



# 手写框架（听）

- 跳过了，以后再补
