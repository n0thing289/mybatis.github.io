import mapper.MyStudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.MyStudent;
import utils.SqlSessionUtil;

public class TestMyStudentMapper {
    /**
     * 测试第三种多对一映射方式
     */
    @Test
    public void testSelectByIdStep1(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        MyStudentMapper myStudentMapper = sqlSession.getMapper(MyStudentMapper.class);
        MyStudent stu = myStudentMapper.selectByIdStep1(5);
//        System.out.println(stu);
        //只需要看学生的名字(测试延迟加载: 用不到那张表就不查)
        System.out.println(stu.getSname());
        //程序执行到这里了再查班级名字
        System.out.println(stu.getMyClass().getCname());
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试第二种多对一方式
     */
    @Test
    public void testSelectByIdAssociation(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        MyStudentMapper myStudentMapper = sqlSession.getMapper(MyStudentMapper.class);
        MyStudent stu = myStudentMapper.selectByIdAssociation(5);
        System.out.println(stu);
        //
        SqlSessionUtil.close();
    }

    /**
     * 测试第一种多对一方式
     */
    @Test
    public void testSelectById(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        MyStudentMapper myStudentMapper = sqlSession.getMapper(MyStudentMapper.class);
        MyStudent stu = myStudentMapper.selectById(1);
        System.out.println(stu);
        //
        SqlSessionUtil.close();
    }
}
