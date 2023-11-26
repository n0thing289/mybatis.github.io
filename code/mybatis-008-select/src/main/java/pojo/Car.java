package pojo;

import java.util.Objects;

public class Car {
    private Long id;
    private String carNum;
    private String brand;
    private Double guidePrice;
    private String produceTime;
    private String carType;

    public Car() {
    }

    public Car(Long id, String car_num, String brand, Double guide_price, String produce_time, String car_type) {
        this.id = id;
        this.carNum = car_num;
        this.brand = brand;
        this.guidePrice = guide_price;
        this.produceTime = produce_time;
        this.carType = car_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Double guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(String produceTime) {
        this.produceTime = produceTime;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(carNum, car.carNum) && Objects.equals(brand, car.brand) && Objects.equals(guidePrice, car.guidePrice) && Objects.equals(produceTime, car.produceTime) && Objects.equals(carType, car.carType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carNum, brand, guidePrice, produceTime, carType);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car_num='" + carNum + '\'' +
                ", brand='" + brand + '\'' +
                ", guide_price=" + guidePrice +
                ", produce_time='" + produceTime + '\'' +
                ", car_type='" + carType + '\'' +
                '}';
    }
}
