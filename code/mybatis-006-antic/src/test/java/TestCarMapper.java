import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;

public class TestCarMapper {
    @Test
    public void testSelectByBrandLike(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByBrandLike("奔驰");
        cars.forEach(car -> System.out.println(car));
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testDeleteBatch(){
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

    @Test
    public void testSelectAllAscOrDesc(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAllAscOrDesc("desc");
        cars.forEach(car -> System.out.println(car));

        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByCarType(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByCarType("新能源");
        cars.forEach(car -> System.out.println(car));

        SqlSessionUtil.close(sqlSession);
    }
}
