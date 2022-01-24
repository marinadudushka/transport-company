package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="phone_number",nullable = false)
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "client")
    private List<TransportCourse> transportCourseList;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    public Client() {
    }

    public Client(long id, String name, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.transportCourseList = new ArrayList<>();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<TransportCourse> getTransportCourseList() {
        return transportCourseList;
    }

    public void setTransportCourseList(List<TransportCourse> transportCourseList) {
        this.transportCourseList = transportCourseList;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public void addTransportCourse(TransportCourse transportCourse){
        this.transportCourseList.add(transportCourse);
    }

}
