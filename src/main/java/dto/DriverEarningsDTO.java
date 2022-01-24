package dto;

import java.math.BigDecimal;

//TODO: display drivers and earning for the company
public class DriverEarningsDTO {
    private long id;
    private String name;
    private BigDecimal earnings;

    public DriverEarningsDTO(long id, String name, BigDecimal earnings) {
        this.id = id;
        this.name = name;
        this.earnings = earnings;
    }

    @Override
    public String toString() {
        return "DriverByEarningsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", earnings=" + earnings +
                '}';
    }
}
