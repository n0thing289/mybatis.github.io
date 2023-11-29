import mapper.MyClassMapper;
import mapper.MyStudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.MyClass;
import pojo.MyStudent;
import utils.SqlSessionUtil;

public class TestMyClassMapper {
    /**
     * 测试一对多:分布查询第一步
     */
    @Test
    public void testSelectByStep1(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        MyClassMapper myClassMapper = sqlSession.getMapper(MyClassMapper.class);
        MyClass clazz = myClassMapper.selectByStep1(301);
        System.out.println(clazz);
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试一对多:collection方式
     */
    @Test
    public void testSelectByCollection(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        MyClassMapper myClassMapper = sqlSession.getMapper(MyClassMapper.class);
        MyClass clazz = myClassMapper.selectByCollection(301);
        System.out.println(clazz);
        //
        SqlSessionUtil.close();
    }

}
