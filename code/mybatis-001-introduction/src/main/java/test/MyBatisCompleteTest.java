package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisCompleteTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //SqlSessionFactoryBuilder
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            //SqlSession
            sqlSession = sqlSessionFactory.openSession();
            //crud
            int count = sqlSession.insert("insertCar");
            //commit
            sqlSession.commit();
        } catch (Exception e) {
            //出现异常最好回滚
            if(sqlSession != null){
                sqlSession.rollback();
            }

            e.printStackTrace();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }


    }
}
