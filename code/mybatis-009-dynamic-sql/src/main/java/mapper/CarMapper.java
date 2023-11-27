package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Car;

import java.util.List;

public interface CarMapper {
    /**
     * 使用foreach-or, 批量删除的第二种方式
     * @param ids
     * @return
     */
    int deleteByIds2(@Param("ids") String[] ids);

    /**
     * 批量插入,一次插入多条Car信息
     * @param cars
     * @return
     */
    int insertBatch(@Param("cars") List<Car> cars);

    /**
     * 使用foreach标签, 批量删除的第一种方式
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids")String[] ids);

    /**
     * 使用choose when otherwise
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    /**
     * 使用set标签
     *
     * @param car
     * @return
     */
    int updateBySet(Car car);


    /**
     * 更新Car
     *
     * @param car
     * @return
     */
    int updateById(Car car);


    /**
     * 使用trim标签
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    /**
     * 使用where标签， 让where字句更加智能
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    /**
     * 多条件查询
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);
}
