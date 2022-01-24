package dto;

import entity.Driver;
import entity.DrivingCategory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class DriverDTO {

    private long id;
    private String name;
    private BigDecimal salary;
    private DrivingCategory drivingCategory;

    public DriverDTO() {
    }

    public DriverDTO(long id, String name, BigDecimal salary, DrivingCategory drivingCategory) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.drivingCategory = drivingCategory;
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", drivingCategory=" + drivingCategory +
                '}';
    }
}
