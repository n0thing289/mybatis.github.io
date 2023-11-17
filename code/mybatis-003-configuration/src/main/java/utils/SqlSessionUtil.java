package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;
    //工具类的构造方法一般都是私有化的
    private SqlSessionUtil(){}

    //类加载执行
    //工具类第一次加载的时候,解析mybatis-config.xml文件,创建SqlSessionFactory对象
    static{
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            //获取SqlSessionFactory
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //所有方法都是静态的
//    public static SqlSession openSession(){
//        //获取SqlSessionFactoryBuilder
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//
//        try {
//            //获取SqlSessionFactory
//            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
//            //获取SqlSession
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
