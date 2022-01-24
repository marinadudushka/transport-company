package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="license_plate",nullable = false)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(name="type",nullable = false)
    private VehicleType vehicleType;

    @OneToOne
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    public Vehicle() {
    }

    public Vehicle(long id, String licensePlate, VehicleType vehicleType) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }


}
