package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import utils.SqlSessionUtil;

public class TestCarMapper {
    @Test
    public void testInsertCarByUtil(){
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行insert
        int count = sqlSession.insert("insertCar");
        //提交事务
        sqlSession.commit();
        //关闭session
        sqlSession.close();
    }

    @Test
    public void testInsertCar(){
        //编写mybatis方法
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
