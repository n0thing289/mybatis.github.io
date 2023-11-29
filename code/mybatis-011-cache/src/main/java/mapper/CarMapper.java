package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Car;

public interface CarMapper {

    /**
     * 测试二级缓存
     * @param id
     * @return
     */
    Car selectById2(Integer id);

    /**
     * 向另外的一张班级表插入数据
     * @param cid
     * @param cname
     * @return
     */
    int insertMyClass(@Param("cid") Integer cid, @Param("cname") String cname);

    /**
     * 测试一级缓存根据id查找汽车
     * @param id
     * @return
     */
    Car selectById(Integer id);
}
