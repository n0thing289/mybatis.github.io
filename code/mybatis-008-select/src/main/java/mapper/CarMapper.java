package mapper;

import org.apache.ibatis.annotations.MapKey;
import pojo.Car;

import java.util.List;
import java.util.Map;

public interface CarMapper {
    /**
     * 获取car的总记录条数
     * @return
     */
    Long selectTotal();

    /**
     * 查询所有car信息,但启用驼峰自动映射
     * @return
     */
    List<Car> selectAllByMapUnderScoreToCamelCase();

    /**
     * 查询所有的car信息.使用resultMap标签进行结果映射(结果列名属性名不一致导致的查询问题)
     * @return
     */
    List<Car> selectAllByResultMap();

    /**
     * 查询所有的Car,返回一个大Map集合.
     * Map集合的key是每条记录的主键值
     * Map集合的value是每条记录
     * @return
     */
    @MapKey("id")//将查询结果的id值作为整个大Map集合的key
    Map<Long, Map<String, Object>> selectAllRetMapMap();

    /**
     * 查询所有的car信息,返回一个存放map的集合
     * @return
     */
    List<Map<String, Object>> selectAllRetListMap();

    /**
     * 根据id查询car信息,但返回map（如果查询的结果没有合适的pojo接收就可以使用map姐搜）
     * +----+---------+-------+-------------+--------------+----------+
     * | id | car_num | brand | guide_price | produce_time | car_type |
     * +----+---------+-------+-------------+--------------+----------+
     * |  7 | 10086   | li    |       10.00 | 2023-11-23   | 电车     |
     * +----+---------+-------+-------------+--------------+----------+
     *
     * mybatis会自动把结果集的列名作为key， 对应的值作为value
     * @param id
     * @return
     */
    Map<String, Object> selectByIdRetMap(Long id);

    /**
     * 根据id查找一个car信息,但放到list中. 这种情况可以使用list集合接收吗
     *
     * @param id
     * @return
     */
    List<Car> selectById2(Long id);

    /**
     * 根据品牌进行模糊查询, 模糊查询的结果可能有多个,但是我采用一个pojo对象接收,测试是否有出问题
     *
     * @param brand
     * @return
     */
    Car selectByBrandLike(String brand);

    /**
     * 查询所有Car信息
     *
     * @return
     */
    List<Car> selectAll();

    /**
     * 根据id查询Car信息
     *
     * @param id
     * @return
     */
    Car selectById(Long id);
}
