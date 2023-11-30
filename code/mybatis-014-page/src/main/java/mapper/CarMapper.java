package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Car;

import java.util.List;

public interface CarMapper {
    /**
     * 使用pagehelper
     * @return
     */
    List<Car> selectAll();

    /**
     * 使用limit
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Car> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
