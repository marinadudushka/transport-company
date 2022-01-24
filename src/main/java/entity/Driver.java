package entity;

import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="salary",nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @OneToOne(mappedBy = "driver")
    private DrivingLicense drivingLicense;

    @OneToOne(mappedBy = "driver",fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    @OneToMany(mappedBy = "driver")
    private List<TransportCourse> transportCourseList;

    public Driver() {
        this.transportCourseList = new ArrayList<>();
    }

    public Driver(long id, String name, BigDecimal salary, DrivingLicense drivingLicense) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.drivingLicense = drivingLicense;
        this.transportCourseList = new ArrayList<>();
    }

    public void addTransportCourse(TransportCourse transportCourse) {
        this.transportCourseList.add(transportCourse);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrivingLicense getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(DrivingLicense drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", drivingLicense=" + drivingLicense +
                ", vehicle=" + vehicle +
                ", transportCompany=" + transportCompany +
                ", transportCourseList=" + transportCourseList +
                '}';
    }

    public static Comparator<Driver> compareByQualification = new Comparator<Driver>() {
        @Override
        public int compare(Driver o1, Driver o2) {
            return o1.drivingLicense.getDrivingCategory().compareTo(o2.getDrivingLicense().getDrivingCategory());
        }
    };
    public static Comparator<Driver> compareBySalary = new Comparator<Driver>() {
        @Override
        public int compare(Driver o1, Driver o2) {
            return o1.salary.compareTo(o2.salary);
        }
    };
}
