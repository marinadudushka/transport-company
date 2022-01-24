package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="transport_course")
public class TransportCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="start_point",nullable = false)
    private String startPoint;

    @Column(name="end_point",nullable = false)
    private String endPoint;

    @Column(name="start_date",nullable = false)
    private LocalDate startDate;

    @Column(name="end_date",nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name="transport_type",nullable = false)
    private TransportType transportType;

    @Column(name="price",nullable = false)
    private BigDecimal price;

    @Column(name="paid",nullable = false)
    private Boolean paid;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private TransportCourseStatus transportCourseStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    public TransportCourse() {
    }

    public TransportCourse(long id, String startPoint, String endPoint, LocalDate startDate, LocalDate endDate, TransportType transportType, TransportCourseStatus transportCourseStatus, BigDecimal price, Boolean paid) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transportType = transportType;
        this.transportCourseStatus = transportCourseStatus;
        this.price = price;
        this.paid = paid;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public TransportCourseStatus getTransportCourseStatus() {
        return transportCourseStatus;
    }

    public void setTransportCourseStatus(TransportCourseStatus transportCourseStatus) {
        this.transportCourseStatus = transportCourseStatus;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "TransportCourse{" +
                "id=" + id +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", transportType=" + transportType +
                ", price=" + price +
                ", client=" + client +
                '}';
    }
}
