package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 编写我的第一个mybatis程序
 */
public class HelloMybatis {
    public static void main(String[] args) throws IOException {
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //获取SqlSessionFactory
        //  第一种写法
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //  第二种写法
        InputStream is2 = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取SqlSession
        //  可以传入一个参数,如果传入参数true,意味着自动提交=不开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //执行sql语句
        //  调用insert
        //  这里传入你在CarMapper.xml的id属性值
        int affectRows = sqlSession.insert("insertCar");

        System.out.println("插入了" + affectRows + "条记录");
        //手动提交
        sqlSession.commit();
    }
}
