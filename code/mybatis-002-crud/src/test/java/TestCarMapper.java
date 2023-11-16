import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class TestCarMapper {

    @Test
    public void testDeleteCarById() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        Car car = new Car(2L, null, null, null, null, null);
        int affectRows = sqlSession.delete("deleteCarById", car);
        System.out.println("删除了： " + affectRows + "行记录");
        //
        sqlSession.commit();
        //
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPojo() {
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //insert
        //  1. 封装数据
        Car car = new Car(null, "1003", "比亚迪秦plus", 20.00, "2023-11-16", "电车");
        //  2. 传入insert方法
        sqlSession.insert("insertCar", car);//第一个参数填sqlId(CarMapper.xml中的属性值),第二个参数填封装数据的对象
        //commit
        sqlSession.commit();
        //close
        sqlSession.close();
    }

    @Test
    public void testInsertCar() {
        //获取sqlSession
        SqlSession sqlSession = SqlSessionUtil.openSession();

        //假设前端现在传过来了值
        //先用map封装
        Map<String, Object> map = new HashMap<>();
//        map.put("k1", "1002");
//        map.put("k2", "比亚迪汉");
//        map.put("k3", 10.00);
//        map.put("k4", "2023-11-16");
//        map.put("k5", "电车");
        //但是这样不明确
        //key应该这样写
        map.put("car_num", "1002");
        map.put("brand", "比亚迪汉");
        map.put("guide_price", 10.00);
        map.put("produce_time", "2023-11-16");
        map.put("car_type", "电车");


        //insert
        //  第一个参数是sqlId
        //  第二个参数是封装数据的对象
        int count = sqlSession.insert("insertCar", map);
        System.out.println("insert count: " + count);
        //commit
        sqlSession.commit();
        //close
        sqlSession.close();
    }
}
