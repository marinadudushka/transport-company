package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="driving_license")
public class DrivingLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="pid_number",nullable = false)
    private String pidNumber;

   @Column(name="address",nullable = false)
    private String address;

    @Column(name="issued",nullable = false)
    private LocalDate issued;

    @Column(name="expires",nullable = false)
    private LocalDate expires;

    @Column(name="license_number",nullable = false)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="category",nullable = false)
    private DrivingCategory drivingCategory;

    @OneToOne
    Driver driver;



    public DrivingLicense() {
    }

    public DrivingLicense(long id, String pidNumber, String address, LocalDate issued, LocalDate expires, String licenseNumber, DrivingCategory drivingCategory) {
        this.id = id;
        this.pidNumber = pidNumber;
        this.address = address;
        this.issued = issued;
        this.expires = expires;
        this.licenseNumber = licenseNumber;
        this.drivingCategory = drivingCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPidNumber() {
        return pidNumber;
    }

    public void setPidNumber(String pidNumber) {
        this.pidNumber = pidNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public void setExpires(LocalDate expires) {
        this.expires = expires;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DrivingCategory getDrivingCategory() {
        return drivingCategory;
    }

    public void setDrivingCategory(DrivingCategory drivingCategory) {
        this.drivingCategory = drivingCategory;
    }

    @Override
    public String toString() {
        return "DrivingLicense{" +
                "id=" + id +
                ", pidNumber='" + pidNumber + '\'' +
                ", address=" + address +
                ", issued=" + issued +
                ", expires=" + expires +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", driver=" + driver +
                ", drivingCategory=" + drivingCategory +
                '}';
    }
}
