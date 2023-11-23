import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;

public class TestCarMapper {
    /**
     * 测试获取自动生成的主键值
     */
    @Test
    public void testInsertByUseGeneratedKeys() {
        //获取sqlSession
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        Car car = new Car(null, "10086", "li", 10.00, "2023-11-23", "电车");
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        int count = carMapper.insertByUseGeneratedKeys(car);
        System.out.println("插入了" + count + "条数据");
        System.out.println("获取到的主键值: " + car.getId());
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试小技巧之模糊查询
     */
    @Test
    public void testSelectByBrandLike() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByBrandLike("奔驰");
        cars.forEach(car -> System.out.println(car));
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试小技巧之批量删除
     */
    @Test
    public void testDeleteBatch() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        carMapper.deleteBatch("2,3,4");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试小技巧之#{}和${}的区别
     */
    @Test
    public void testSelectAllAscOrDesc() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAllAscOrDesc("desc");
        cars.forEach(car -> System.out.println(car));

        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 测试小技巧之#{}和${}的区别
     */
    @Test
    public void testSelectByCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByCarType("新能源");
        cars.forEach(car -> System.out.println(car));

        SqlSessionUtil.close(sqlSession);
    }
}
