package dto;

import entity.DrivingCategory;
import entity.DrivingLicense;

import java.math.BigDecimal;

public class DriverSalaryQualificationDTO {
    private long id;
    private String name;
    private DrivingCategory drivingCategory;
    private BigDecimal salary;

    public DriverSalaryQualificationDTO(long id, String name, DrivingCategory drivingCategory, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.drivingCategory = drivingCategory;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "DriverSalaryQualificationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", drivingCategory=" + drivingCategory +
                ", salary=" + salary +
                '}';
    }
}
