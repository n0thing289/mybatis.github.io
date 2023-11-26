import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCarMapper {
    /**
     * 测试总记录条数
     */
    @Test
    public void testSelectTotal() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Long count = carMapper.selectTotal();
        System.out.println("总记录条数: "+count);
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试驼峰命名自动映射
     */
    @Test
    public void testSelectAllByMapUnderScoreToCameCase() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> listMap = carMapper.selectAllByMapUnderScoreToCamelCase();
        listMap.forEach(map -> System.out.println(map));
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试结果映射之
     * 使用resultMap标签解决属性名列名不一致的查询问题
     */
    @Test
    public void testSelectAllByResultMap() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> listMap = carMapper.selectAllByResultMap();
        listMap.forEach(map -> System.out.println(map));
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectAllRetMapMap() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> Maps = carMapper.selectAllRetMapMap();
        Set<Long> keys = Maps.keySet();
        for (Long key : keys) {
            System.out.println(key + "=" + Maps.get(key));
        }
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试返回多个Map
     */
    @Test
    public void testSelectAllRetListMap() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> listMap = carMapper.selectAllRetListMap();
        listMap.forEach(map -> System.out.println(map));
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试返回map
     */
    @Test
    public void testSelectByIdRetMap() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Map<String, Object> map = carMapper.selectByIdRetMap(7L);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + "=" + map.get(key));
        }
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试一条记录可以被list接受吗
     */
    @Test
    public void testSelectById2() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectById2(7L);
        cars.forEach(car -> System.out.println(car));
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试返回多个Car, 但只用一个pojo接收
     * <p>
     * org.apache.ibatis.exceptions.TooManyResultsException:
     * Expected one result (or null) to be returned by selectOne(), but found: 2
     * 你期望是一条,但是查到多条,
     */
    @Test
    public void testSelectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectByBrandLike("奔驰");
        System.out.println(car);
        //
        SqlSessionUtil.close();

    }

    /**
     * 测试返回多个Car
     */
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAll();
        cars.forEach(car -> System.out.println(car));
        //
        SqlSessionUtil.close();

    }

    /**
     * 测试返回Car
     */
    @Test
    public void testSelectById() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectById(7L);
        System.out.println(car);
        //
        SqlSessionUtil.close();
    }
}
