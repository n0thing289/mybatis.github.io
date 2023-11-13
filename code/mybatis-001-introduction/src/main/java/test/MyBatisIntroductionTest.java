package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

public class MyBatisIntroductionTest {
    public static void main(String[] args) throws IOException {
        //先获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        //在获取SqlSessionFactory对象
//        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");// 默认从类的根路径下查找资源
//        InputStream is = Resources.getResourceAsStream("com/mybatis.xml");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = ssfb.build(is);// 一般一个数据库一个
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        int affectRows = sqlSession.insert("insertCar");

        System.out.println("插入了" + affectRows + "条记录");

        //手动提交
        sqlSession.commit();
    }
}
