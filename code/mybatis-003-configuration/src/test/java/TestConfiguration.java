import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import utils.SqlSessionUtil;

import java.io.IOException;

public class TestConfiguration {
    @Test
    public void testDataSource() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        //会话1
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        sqlSession1.insert("CarMapper.insertCar");
        sqlSession1.commit();
        sqlSession1.close();

        //会话2
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        sqlSession2.insert("CarMapper.insertCar");
        sqlSession2.commit();
        sqlSession2.close();
    }

    @Test
    public void testEnvironment() throws IOException {
        //获取SqlSessionFactory对象（采用默认的方式获取）
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //这种方式获取的默认环境
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        //这种方式就是通过环境id来使用指定的环境
        SqlSessionFactory sqlSessionFactory_hsp = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "hsp01");



    }
}
