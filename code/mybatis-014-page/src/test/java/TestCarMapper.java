import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.util.List;

public class TestCarMapper {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        //在执行DQL之前开启分页功能
        int pageNum = 2;
        int pageSize = 3;
        PageHelper.startPage(pageNum, pageSize);
        //执行查询语句
        List<Car> cars = mapper.selectAll();
//        cars.forEach(System.out::println);
        //在查询之后获取分页信息对象
        //导航页数:就是百度页面下面的页数条
        PageInfo<Car> info = new PageInfo<>(cars, 3);
        System.out.println(info);
        SqlSessionUtil.close();
        /*
        PageInfo{pageNum=2, pageSize=3, size=3, startRow=4, endRow=6, total=11, pages=4,
        list=Page{count=true, pageNum=2, pageSize=3, startRow=3, endRow=6, total=11, pages=4, reasonable=false, pageSizeZero=false}
        [Car{id=11, car_num='100', brand='宝马520Li', guide_price=41.0, produce_time='2023-11-12', car_type='燃油车'},
        Car{id=12, car_num='101', brand='奔驰E300L', guide_price=54.0, produce_time='2023-11-12', car_type='新能源'},
        Car{id=13, car_num='100', brand='宝马520Li', guide_price=41.0, produce_time='2023-11-12', car_type='燃油车'}],
        prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true, hasNextPage=true, navigatePages=3,
        navigateFirstPage=1, navigateLastPage=3, navigatepageNums=[1, 2, 3]}

         */
    }

    /**
     * 测试使用limit
     */
    @Test
    public void testSelectByPage() {
        //获取每页显示条数
        int pageSize = 3;
        //获取显示第几页
        int pageNum = 1;
        //计算起始下标
        int startIndex = (pageNum - 1) * pageSize;

        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByPage(startIndex, pageSize);
        System.out.println(cars);

        SqlSessionUtil.close();
    }
}
