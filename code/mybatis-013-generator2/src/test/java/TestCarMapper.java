import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.pojo.CarExample;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * 主要测查询
 */
public class TestCarMapper {
    //CarExample负责封装查询条件的
    @Test
    public void testSelect(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        //执行查询
        //1. 查询一个
        Car car = mapper.selectByPrimaryKey(8L);
        System.out.println(car);
        //2. 查询所有(selectExample 根据条件查询 如果条件时null表示没有条件)
        List<Car> cars = mapper.selectByExample(null);
        //3. 按照条件进行查询
        //QBC 风格: Query By Criteria 一种查询方式，比较面向对象，看不到sgl语句.
        CarExample carExample = new CarExample();
        carExample.createCriteria().andBrandLike("%奔驰%").andGuidePriceGreaterThan(new BigDecimal(20.0));
        //添加or
//        carExample.or().andCarTypeEqualTo("燃油车");
        //执行查询
        List<Car> cars2 = mapper.selectByExample(carExample);
        System.out.println(cars2);
        //
        SqlSessionUtil.close();
    }
}
