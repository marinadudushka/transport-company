package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="weight",nullable = false)
    private BigDecimal weight;

    @ManyToOne
    private TransportCourse transportCourse;

    @Column(name="price",nullable = false)
    private BigDecimal price;
}
