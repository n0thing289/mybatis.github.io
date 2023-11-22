package mapper;

import pojo.Log;

import java.util.List;

public interface LogMapper {

    /**
     *
     * @param date
     * @return
     */
    List<Log> selectAllByTable(String date);

}
