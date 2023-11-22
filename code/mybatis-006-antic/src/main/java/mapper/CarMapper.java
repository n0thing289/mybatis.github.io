package mapper;

import pojo.Car;

import java.util.List;

public interface CarMapper {
    List<Car> selectByBrandLike(String keyword);

    int deleteBatch(String ids);

    List<Car> selectAllAscOrDesc(String ascOrDesc);

    List<Car> selectByCarType(String carType);
}
