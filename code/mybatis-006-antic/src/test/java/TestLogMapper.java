import mapper.LogMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Log;
import utils.SqlSessionUtil;

import java.util.List;

public class TestLogMapper {
    @Test
    public void testSelectAllByTable(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logs = logMapper.selectAllByTable("2023_11_22");
        logs.forEach(log -> System.out.println(log));
        SqlSessionUtil.close();
    }
}
