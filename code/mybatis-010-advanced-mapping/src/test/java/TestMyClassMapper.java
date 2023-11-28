import mapper.MyClassMapper;
import mapper.MyStudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.MyClass;
import pojo.MyStudent;
import utils.SqlSessionUtil;

public class TestMyClassMapper {
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
