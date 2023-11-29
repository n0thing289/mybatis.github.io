import mapper.CarMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.Car;
import utils.SqlSessionUtil;

import java.io.IOException;

public class TestCarMapper {
    /**
     * 测试二级缓存
     *  什么时候失效
     *      两次查询之间有增删改操作
     *  cache标签有一些属性
     *      eviction
     *      flushInterval
     *      readOnly
     *      size
     */
    @Test
    public void testSelectById2() throws IOException {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = factory.openSession();
        SqlSession sqlSession2 = factory.openSession();
        CarMapper carMapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper carMapper2 = sqlSession2.getMapper(CarMapper.class);
        //这行代码执行结束之后，实际上数据是缓存到一级缓存当中了。(sqlSession1是一级缓存。)
        Car car1 = carMapper1.selectById2(8);
        System.out.println(car1);

        //如果这里不关闭SgLSession1对象的话，二级缓存中还是没有数据的.
        //如果这里关闭sqlSession1对象, 一级缓存的数据会存入二级缓存
        sqlSession1.close();
        //这行代码执行结束之后，实际上数据是缓存到一级缓存当中了。(sqlSession2是一级缓存。)
        Car car2 = carMapper2.selectById2(8);
        System.out.println(car2);

        //程序执行到这里的时候，会将sqlSession1这个一级缓存中的数据写入到二级缓存当中。
        //sqlSession1.close();
        //程序执行到这里的时候，会将sqlSession2这个一级缓存中的数据写入到二级缓存当中。
        sqlSession2.close();
    }

    /**
     * 测试一级缓存
     * 不使用工具类
     */
    @Test
    public void testSelectByIdWithNoThreadLocal() throws IOException {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = factory.openSession();
        SqlSession sqlSession2 = factory.openSession();

        CarMapper carMapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = carMapper1.selectById(8);
        System.out.println(car1);

        CarMapper carMapper2 = sqlSession2.getMapper(CarMapper.class);
        Car car2 = carMapper2.selectById(8);
        System.out.println(car2);

        sqlSession1.close();
        sqlSession2.close();
    }

    /**
     * 测试一级缓存
     *  什么时候不走缓存
     *      sqlSession对象不是同一个
     *      查询条件不一样,也不走
     *  什么时候一级缓存失效
     *      第一次DQL和第二次DQL之间你做了以下两件事中的任意一件，都会让一级缓存清空:
     *          1.执行了sqlSession的clearCache()方法，这是手动清空缓存。
     *          2。执行了INSERT或DELETE或UPDATE语句。不管你是操作哪张表的，都会清空一级缓存。
     */
    @Test
    public void testSelectById() {
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectById(8);
        System.out.println(car);

        //手动清空一级缓存
//        sqlSession.clearCache();
        //在这里执行insert|update|delete任意一个语句, 并且和表没有关系
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.insertMyClass(303, "高三三班");

        //观察日志执行了几次sql语句
        //试试mapper不同
        //测试得知, 与mapper无关, 而是与sqlSession有关:走的还是一级缓存
        CarMapper carMapper2 = sqlSession.getMapper(CarMapper.class);
        Car car2 = carMapper2.selectById(8);
        System.out.println(car2);
        //
        sqlSession.commit();
        SqlSessionUtil.close();
    }
}
