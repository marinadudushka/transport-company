package dao;

import configuration.SessionFactoryUtil;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {
    public static void saveVehicle(Vehicle vehicle){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        }
    }
    public static void saveVehicles(List<Vehicle> vehicleList){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            vehicleList.stream().forEach((vehicle)->session.save(vehicle));
            transaction.commit();
        }
    }
    public static void deleteVehicle(Vehicle vehicle){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }
    public static void updateVehicle(Vehicle vehicle){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.update(vehicle);
            transaction.commit();
        }
    }
    public static Vehicle getVehicle(long id){
        Vehicle vehicle;
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            vehicle = session.get(Vehicle.class,id);
            transaction.commit();
        }
        return vehicle;
    }
}
