import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;

public class TestCarMapper {

    @Test
    public void testSelectAll(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        //
//        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectCarById(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectCarById(18L);
        System.out.println(car);
        //
//        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testUpdateCarById(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "110", "坦克300",100.00,"2023-11-30","燃油车");
        int count = mapper.updateCarById(car);
        //
        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testDeleteCarById(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteCarById(18L);
        System.out.println("删除了" + count + "条数据");
        //
//        sqlSession.commit();
        //
        SqlSessionUtil.close();
    }

    @Test
    public void testInsertCar(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.insertCar(
                new Car(null,"10086","比亚迪",10.00,"2023-11-30","电车"));
        System.out.println("插入了"+count+"条数据");
        sqlSession.commit();
        SqlSessionUtil.close();
    }
}
