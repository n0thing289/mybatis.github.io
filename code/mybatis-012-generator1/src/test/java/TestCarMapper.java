import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestCarMapper {
    @Test
    public void testSelectAll(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
    }

    @Test
    public void testDeleteByPrimaryKey(){
        //
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);

    }
}
