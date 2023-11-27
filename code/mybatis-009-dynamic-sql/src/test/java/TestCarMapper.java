import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.ArrayList;
import java.util.List;

public class TestCarMapper {

    /**
     * 测试foreach之批量删除
     */
    @Test
    public void testDeleteByIds2() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        String[] ids = {"9", "10", "11"};
        int count = carMapper.deleteByIds2(ids);
        System.out.println("删除了" + count + "条数据");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试foreach之批量插入
     */
    @Test
    public void testInsertBatch(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(null,"001","宝马520Li",41.00,"2023-11-12", "燃油车"));
        cars.add(new Car(null,"002","宝马520Li",41.00,"2023-11-12", "燃油车"));
        cars.add(new Car(null,"003","宝马520Li",41.00,"2023-11-12", "燃油车"));
        cars.add(new Car(null,"004","宝马520Li",41.00,"2023-11-12", "燃油车"));
        int count = carMapper.insertBatch(cars);
        System.out.println("删除了" + count + "条数据");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试foreach之批量删除
     */
    @Test
    public void testDeleteByIds() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        String[] ids = {"5", "6", "7"};
        int count = carMapper.deleteByIds(ids);
        System.out.println("删除了" + count + "条数据");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试使用choose when otherwise
     */
    @Test
    public void testSelectByChoose() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        // 三个都不是空
        //List<Car> cars = carMapper.selectByChoose("奔驰", 10.0, "新能源");
        // 第一个是空
        //List<Car> cars = carMapper.selectByChoose(null, 10.0, "新能源");
        // 前两个是空
        //List<Car> cars = carMapper.selectByChoose(null, null, "新能源");
        // 全都是空
        List<Car> cars = carMapper.selectByChoose(null, null, null);
        cars.forEach(System.out::println);
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试使用set标签
     */
    @Test
    public void testUpdateBySet() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        //Car car = new Car(7L, "10086", "比亚迪", 10.00, "2023-11-27", "燃油车");
        Car car = new Car(7L, null, null, null, null, "新能源");
        int count = carMapper.updateBySet(car);
        System.out.println("修改类" + count + "条记录");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 与set标签测试程序的对照
     */
    @Test
    public void testUpdateById() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        //Car car = new Car(7L, "10086", "比亚迪", 10.00, "2023-11-27", "燃油车");
        Car car = new Car(7L, null, null, null, null, "燃油车");
        int count = carMapper.updateById(car);
        System.out.println("修改类" + count + "条记录");
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试trim标签
     */
    @Test
    public void testSelectByMultiConditionWithTrim() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        //假设三个条件都不是空
        List<Car> cars = carMapper.selectByMultiConditionWithTrim("奔驰", 10.00, "新能源");

        // 假设三个条件都是空
        //List<Car> cars = carMapper.selectByMultiConditionWithTrim(null, null, null);

        // 假设后两个条件不为空. 第一个条件为空
        //List<Car> cars = carMapper.selectByMultiConditionWithTrim(null, 10.00, "新能源");

        // 假设后两个条件为空,第一个条件不为空
        //List<Car> cars = carMapper.selectByMultiConditionWithTrim("奔驰", null, null);
        cars.forEach(System.out::println);
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试where标签
     */
    @Test
    public void testSelectByMultiConditionWithWhere() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        //假设三个条件都不是空
        List<Car> cars = carMapper.selectByMultiConditionWithWhere("奔驰", 10.00, "新能源");

        // 假设三个条件都是空
        //List<Car> cars = carMapper.selectByMultiConditionWithWhere(null, null, null);

        // 假设后两个条件不为空. 第一个条件为空
        //List<Car> cars = carMapper.selectByMultiConditionWithWhere(null, 10.00, "新能源");

        // 假设后两个条件为空,第一个条件不为空
        //List<Car> cars = carMapper.selectByMultiConditionWithWhere("奔驰", null, null);
        cars.forEach(System.out::println);
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试if标签
     */
    @Test
    public void testSelectByMultiCondition() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        //假设三个条件都不是空
        //List<Car> cars = carMapper.selectByMultiCondition("奔驰", 10.00, "新能源");

        // 假设三个条件都是空
        //List<Car> cars = carMapper.selectByMultiCondition(null, null, null);

        // 假设后两个条件不为空. 第一个条件为空
        //List<Car> cars = carMapper.selectByMultiCondition(null, 10.00, "新能源");

        // 假设后两个条件为空,第一个条件不为空
        List<Car> cars = carMapper.selectByMultiCondition("奔驰", null, null);
        cars.forEach(System.out::println);
        //
        SqlSessionUtil.close();
    }
}
