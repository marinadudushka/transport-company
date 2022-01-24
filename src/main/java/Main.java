import entity.*;
import dao.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String args[]){

        System.out.println("<----------- Transport Company 1 ------------------------>");

        TransportCompany transportCompany1 = new TransportCompany(1,"Transport Company 1",
                                            "bul. Okolovrasten pat 145,Sofia,Bulgaria",
                                                    LocalDate.of(1998,5,13),
                                                     BigDecimal.valueOf(5000000));
        TransportCompanyDAO.saveTransportCompany(transportCompany1);


        DrivingLicense drivingLicense = new DrivingLicense(1,
                "1234567890",
                "ul. Gorno nanadolnishte",LocalDate.now(),LocalDate.now(),
                "45654568",DrivingCategory.C);
        DrivingLicense drivingLicense1 = new DrivingLicense(1,
                "1234567890",
                "ul. Gorno nanadolnishte",LocalDate.now(),LocalDate.now(),
                "45654568",DrivingCategory.B);

        Driver driver = new Driver(1,"Petar Georgiev",BigDecimal.valueOf(1500),drivingLicense);
        Driver driver1 = new Driver(1,"Petra Ivanova",BigDecimal.valueOf(2000),drivingLicense1);
        transportCompany1.hireDriver(driver,drivingLicense);
        transportCompany1.hireDriver(driver1,drivingLicense1);

        Vehicle vehicle = new Vehicle(1,"CB1545KA",VehicleType.TRUCK);
        Vehicle vehicle1 = new Vehicle(2,"CB5544HA",VehicleType.CAR);

        transportCompany1.addVehicle(vehicle);
        transportCompany1.addVehicle(vehicle1);

        transportCompany1.assignVehicle(driver,vehicle);
        transportCompany1.assignVehicle(driver1,vehicle1);

        Client client = new Client(1,"Gosho Peshov","0215487554","ul, Kriva prava 14, Sofia,Bulgaria");
        Client client1 = new Client(2,"Ivana Petrova","0845875164","ul. Pryaka kriva 14,Sofia,Bulgaria");
        transportCompany1.addClient(client);
        transportCompany1.addClient(client1);

        TransportCourse transportCourse = new TransportCourse(1,"Sofia","Ruse",LocalDate.of(2022,1,15),LocalDate.of(2022,1,16),TransportType.GOODS,TransportCourseStatus.FULFILLED,BigDecimal.valueOf(158),true);
        TransportCourse transportCourse1 = new TransportCourse(1,"Sofia","Plovdiv",LocalDate.of(2021,5,9),LocalDate.of(2021,5,14),TransportType.GOODS,TransportCourseStatus.FULFILLED,BigDecimal.valueOf(250),false);
        TransportCourse transportCourse2 = new TransportCourse(1,"Pernik","Blagoevgrad",LocalDate.of(2022,2,9),LocalDate.of(2022,2,11),TransportType.GOODS,TransportCourseStatus.UNFULFILLED,BigDecimal.valueOf(305),false);
        TransportCourse transportCourse3 = new TransportCourse(1,"Kazanluk","Sofia",LocalDate.of(2022,1,23),LocalDate.of(2022,1,30),TransportType.GOODS,TransportCourseStatus.UNFULFILLED,BigDecimal.valueOf(199),false);
        TransportCourse transportCourse4 = new TransportCourse(1,"Sofia","Dupnica",LocalDate.of(2022,1,21),LocalDate.of(2022,1,23),TransportType.GOODS,TransportCourseStatus.FULFILLED,BigDecimal.valueOf(2000),true);
        TransportCourse transportCourse5 = new TransportCourse(1,"Sofia","Burgas",LocalDate.of(2022,1,22),LocalDate.of(2022,1,25),TransportType.GOODS,TransportCourseStatus.FULFILLED,BigDecimal.valueOf(5000),true);

        transportCompany1.addTransportCourse(transportCourse,client,driver);
        transportCompany1.addTransportCourse(transportCourse1,client1,driver1);
        transportCompany1.addTransportCourse(transportCourse2,client1,driver1);
        transportCompany1.addTransportCourse(transportCourse3,client,driver);
        transportCompany1.addTransportCourse(transportCourse4,client1,driver);
        transportCompany1.addTransportCourse(transportCourse5,client1,driver1);

        //Display drivers sorted by qualification and salary
        transportCompany1.displayCompanyDriversByQualificationAndSalary();
        //Display courses by destination
        transportCompany1.displayCompanyTransportsByDestination();
        //Display all courses fulfilled
        transportCompany1.displayNumberOfFulfilledTransportCourses();
        //Display earnings from transports
        transportCompany1.displayEarningsFromCourses();
        //Display drivers and number of courses they have fulfilled
        transportCompany1.displayCompanyDriversByCourses();
        //Display earnings for a period
        transportCompany1.displayEarningForAPeriod(LocalDate.of(2022,1,1),LocalDate.of(2022,1,30));
        //Display earnings by driver
        transportCompany1.displayDriversAndEarnings();
    }
}
