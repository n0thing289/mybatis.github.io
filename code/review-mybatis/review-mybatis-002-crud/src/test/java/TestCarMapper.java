import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 在这个测试程序里面编写crud 相关的mybatis代码
 */
public class TestCarMapper {
    @Test
    public void testInsertCar() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // sqlId object
        Car car = new Car(null, "100", "宝马520Li", 41.00, "2023-11-12", "燃油车");
        int count = sqlSession.insert("insertCar", car);
        System.out.println("增加了 "+count+" 条数据");
        //提交
        sqlSession.commit();
        //关闭
        sqlSession.close();
    }

    @Test
    public void testDeleteCarByCarNum(){
        //使用自己封装的SQL SessionUtil创建sqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql
        String carNum = "100";
        int count = sqlSession.delete("CarMapper.deleteCarByCarNum", carNum);//如果sql语句只有一个#{}, #{}里面怎么写都可以/传一个变量(不需要对象),但最好见名之意
        System.out.println("删除了 "+count+" 条数据");
        //提交
        sqlSession.commit();
        //关闭
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testUpdateCarByCarNum(){
        //sqlSession
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql
        Car car = new Car(null,"100","五菱宏光",10.00,"2023-11-20","电车");
        int count = sqlSession.update("CarMapper.updateCarByCarNum", car);
        System.out.println("修改了 "+count+" 条数据");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectCarByCarNum(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        String carNum = "100";
        Object car = sqlSession.selectOne("CarMapper.selectCarByCarNum", carNum);
        System.out.println(car);
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectCarAll(){
        //获取sqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        List<Car> cars = sqlSession.selectList("CarMapper.selectCarAll");
        cars.forEach(car -> System.out.println(car));
        //
        sqlSession.commit();
        //
        sqlSession.close();
    }
}
