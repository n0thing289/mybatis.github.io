import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;

public class TestCrudByMybatisProxy {
    /**
     * 业务层调用dao(mapper)
     * 测试crud
     */
    private final CarMapper carMapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);

    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(null,"100","宝马520Li",41.00,"2023-11-12", "燃油车");
        int count = carMapper.insert(car);
        System.out.println("增加了"+count+"条数据");
        //提交
        sqlSession.commit();
        //关闭
        sqlSession.close();
    }
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Long id = 1L;
        int count = carMapper.deleteById(id);
        System.out.println("删除了"+count+"条数据");
        //提交
        sqlSession.commit();
        //关闭
        SqlSessionUtil.close(sqlSession);
    }
    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(2L, "110", "五菱宏光",100.00,"2023-11-22","燃油车");
        int count = carMapper.update(car);
        System.out.println("修改了"+count+"条数据");
        //提交
        sqlSession.commit();
        //关闭
        SqlSessionUtil.close(sqlSession);
    }
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = carMapper.selectById(1L);
        System.out.println(car);
        //
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Car> cars = carMapper.selectAll();
        cars.forEach(car -> {
            System.out.println(car);
        });
        //关闭
        SqlSessionUtil.close(sqlSession);
    }
}
