package entity;

import dao.*;
import dto.TransportCourseDTO;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.core.util.JsonUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="transport_company")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="address",nullable = false)
    private String address;

    @Column(name="foundation_date",nullable = false)
    private LocalDate foundationDate;

    @Column(name="initial_capital",nullable = false)
    private BigDecimal initialCapital;

    @OneToMany(mappedBy = "transportCompany")
    private List<TransportCourse> transportCourseList;

    @OneToMany(mappedBy ="transportCompany")
    private List<Vehicle> vehicleList;

    @OneToMany(mappedBy = "transportCompany")
    private List<Driver> driverList;

    @OneToMany(mappedBy = "transportCompany")
    private List<Client> clientList;

    public TransportCompany() {
    }

    public TransportCompany(long id, String name, String address, LocalDate foundationDate, BigDecimal initialCapital) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foundationDate = foundationDate;
        this.initialCapital = initialCapital;
        this.driverList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
        this.transportCourseList = new ArrayList<>();
        this.clientList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "id=" + id +
                ", vehicleList=" + vehicleList +
                ", driverList=" + driverList +
                '}';
    }

     //vehicle
    public void addVehicle(Vehicle vehicle){
        vehicle.setTransportCompany(this);
        this.vehicleList.add(vehicle);
        VehicleDAO.saveVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicleList.remove(vehicle);
        VehicleDAO.deleteVehicle(vehicle);
    }
    //driver
    public void hireDriver(Driver driver,DrivingLicense drivingLicense){
        driver.setTransportCompany(this);
        driverList.add(driver);
        driver.setDrivingLicense(drivingLicense);
        drivingLicense.setDriver(driver);
        DriverDAO.saveDriver(driver);
        DrivingLicenseDAO.saveDrivingLicense(drivingLicense);

    }

    public void assignVehicle(Driver driver,Vehicle vehicle){
        vehicle.setDriver(driver);
        driver.setVehicle(vehicle);
        DriverDAO.updateDriver(driver);
        VehicleDAO.updateVehicle(vehicle);
    }

    public void addTransportCourse(TransportCourse transportCourse,Client client,Driver driver){
        transportCourse.setTransportCompany(this);
        driver.addTransportCourse(transportCourse);
        transportCourse.setDriver(driver);
        transportCourse.setClient(client);
        client.addTransportCourse(transportCourse);
        this.transportCourseList.add(transportCourse);
        DriverDAO.updateDriver(driver);
        ClientDAO.updateClient(client);
        TransportCourseDAO.saveTransportCourse(transportCourse);
    }

    public void payTransport(TransportCourse transportCourse,BigDecimal payment){
        transportCourse.setPaid(true);
        TransportCourseDAO.updateTransportCourse(transportCourse);
        initialCapital.add(payment);
    }

    public void displayCompanyTransportsByDestination(){
        System.out.println("<--------------------- Display transport courses by destination --------------------------->");
       List<TransportCourseDTO> transportCourseDTOS =  TransportCompanyDAO.getTransportCourses(this.id);
       transportCourseDTOS.sort(TransportCourseDTO.CompareByName);
       transportCourseDTOS.forEach(System.out::println);
    }
    public void displayEarningsFromCourses(){
        System.out.println("<--------------------- Display earnings from courses --------------------------->");
        System.out.println("Company "+id+ "earnings from fulfilled courses: "+TransportCompanyDAO.getEarningsFromCourses(this));
    }

    public void displayNumberOfFulfilledTransportCourses(){
        System.out.println("<--------------------- Display number of fulfilled courses --------------------------->");
        System.out.println("Company " + id + " number of fulfilled transport courses: "+TransportCompanyDAO.getNumberOfFulfilledTransportCourses(this));
    }

    public void displayEarningForAPeriod(LocalDate date1,LocalDate date2){
        System.out.println("<--------------------- Display earnings for a period  --------------------------->");
        System.out.println("Company " + id + " earnings from a period of time: "+TransportCourseDAO.companyEarningsPeriod(date1,date2,this));
    }
    //client
    public void addClient(Client client){
        client.setTransportCompany(this);
        clientList.add(client);
        ClientDAO.saveClient(client);
    }

    public void removeClient(Client client){
        clientList.remove(client);
        ClientDAO.deleteClient(client);
    }

    public void updateClient(Client client){
        clientList.add(client);
        ClientDAO.updateClient(client);
    }

    public void displayCompanyDriversByCourses(){
        System.out.println("<-------------------------------Drivers and number of courses------------------------->");
        TransportCompanyDAO.getDriversAndNumberOfCourses(this.id).stream().forEach(System.out::println);
    }

    public void displayCompanyDriversByQualificationAndSalary(){
        System.out.println("<-------------------------------Drivers sorted by qualifications and salary------------------------->");
        TransportCompanyDAO.getTransportCompanyDriversSorted(this.id).stream().forEach(System.out::println);
    }

    public void displayDriversAndEarnings(){
        System.out.println("<-------------------------------Drivers and their earnings for the company------------------------->");
        TransportCompanyDAO.getDriversAndEarnings(this.id).stream().forEach(System.out::println);
    }

   public static Comparator<TransportCompany> CompareByName = new Comparator<TransportCompany>() {
       @Override
       public int compare(TransportCompany o1, TransportCompany o2) {
           return o1.name.compareTo(o2.name);
       }
   };

    public static Comparator<TransportCompany> CompareByIncome = new Comparator<TransportCompany>() {
        @Override
        public int compare(TransportCompany o1, TransportCompany o2) {
            return o1.initialCapital.compareTo(o2.initialCapital);
        }
    };

}
