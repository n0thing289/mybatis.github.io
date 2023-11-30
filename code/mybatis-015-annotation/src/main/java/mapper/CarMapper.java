package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Car;

import java.util.List;

public interface CarMapper {

    @Select("select * from t_car")
    @Results({
            @Result(property="carNum", column="car_num"),
            @Result(property="brand", column="brand"),
            @Result(property="guidePrice", column="guide_price"),
            @Result(property="produceTime", column="produce_time"),
            @Result(property="carType", column="car_type"),
    })
    List<Car> selectAll();

    @Select("select * from t_car where id = #{id}")
    Car selectCarById(Long id);

    @Update("update t_car set " +
            "car_num=#{carNum}," +
            "brand=#{brand}," +
            "guide_price=#{guidePrice}," +
            "produce_time=#{produceTime}," +
            "car_type=#{carType}" +
            "where id = #{id}")
    int updateCarById(Car car);

    @Delete("delete from t_car where id = #{id}")
    int deleteCarById(Long id);

    @Insert("insert into t_car values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})")
    int insertCar(Car car);


}
