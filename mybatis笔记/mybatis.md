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



# mybatis的configuration



# 手写框架（听）

- 跳过了，以后再补

# 在web项目中使用mybatis

# 面向接口编程完成crud

- 在web项目使用mybatis的不足之一： dao的源码其实很固定，我能不能不写？

  ```java
  public class AccountDaoImpl implements AccountDao {
      
      @Override
      public Account selectByActno(String Actno) {
          //使用mybatis
          SqlSession sqlSession = SqlSessionUtil.openSession();
          //执行sql语句
          Account act = sqlSession.selectOne("AccountMapper.selectByActno", Actno);
          return act;
      }
  
      @Override
      public int updateByActno(Account act) {
          //使用mybatis
          SqlSession sqlSession = SqlSessionUtil.openSession();
          //执行sql
          int count = sqlSession.update("updateByActno", act);
          return count;
      }
  }
  
  ```

- 答案是可以, javassist技术可以帮我们通过代码来创建一个实现接口和接口方法的实现类

- 什么是javassist?

  - //

- 使用javasist创建一个类并且调用类中方法

  ```java
  //获取类池
  ClassPool pool = ClassPool.getDefault();
  //创建类
  CtClass ctClass = pool.makeClass("CtClassByJavassist");
  //创建方法
  String methodCode =
          "public void hello(){" +
          "System.out.println(\"Hello World!\");" +
          "}";
  CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
  //添加到类中
  ctClass.addMethod(ctMethod);
  //在内存中生成class
  ctClass.toClass();  //事实上这个toClass()方法会返回一个class对象
  
  //反射机制
  Class<?> clazz = Class.forName("CtClassByJavassist");
  //创建对象
  Object obj = clazz.newInstance();
  //获取其方法
  Method method = clazz.getDeclaredMethod("hello");
  //调用
  method.invoke(obj);
  //打印信息
  System.out.println(obj);
  
  /** 控制台信息
  * Hello World!
  * CtClassByJavassist@22f71333
  */
  ```

- 使用javasist创建一个实现AccountDao接口的实现类

  ```java
  //获取类池
  ClassPool pool = ClassPool.getDefault();
  //制造类
  CtClass ctClass = pool.makeClass("com.bjpowernode.dao.impl.AccountDaoImpl");
  //制造接口 ?为什么要制造接口而不能获取一个已有的接口呢
  CtClass ctInterface = pool.makeInterface("com.bjpowernode.dao.AccountDao");
  //添加接口到类中 AccountDaoImpl implements AccountDao
  ctClass.addInterface(ctInterface);
  //实现接口中的方法-制造方法
  String methodCode =
  	"public void hello(){" +
  	"System.out.println(\"Hello World2!\");" +
  	"}";
  CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
  //将方法添加到类中
  ctClass.addMethod(ctMethod);
  //在内存中生成类, 同时将生成的类加载到JVM当中
  Class<?> clazz = ctClass.toClass();
  AccountDao accountDao = (AccountDao) clazz.newInstance();
  accountDao.hello();
  ```

- 以上两个例子的区别在哪里？

  - 第二个例子比起第一个例子多了 制造接口， 实现接口以及生成类后的向上转型直接调用方法 这些步骤
  - 最重要的是，第二个例子生成的类因为实现了接口，可以直接调用接口的方法（方法的动态绑定机制） 
  - 第一个例子，因为没有实现接口，在编译阶段是找不到一个叫CtClassByJavassist的类的，也就不能在编译阶段直接调用方法，全程要使用反射

- 那我现在如何通过这个技术去实现给定一个接口,如何给我动态生成一个对应具有crud的实现类呢?

  - 我的理解：在javasist里， 类是由接口，方法，属性组装到一起的
  - 如果实现接口ctClass.addInterface()  ->  AccountDaoImpl implements AccountDao

  - 如果把接口里的方法都实现
    - 这样就可以生成一个对应具有crud的实现类了, 但是思考几个问题
    - 我是要给定一个dao接口, 如何帮我自动生成具有crud功能的dao实现类
  - 如何去把接口的方法都实现才是最难的 -> 拼串
  - TODO
