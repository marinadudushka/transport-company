package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DriverDAO {
    public static void saveDriver(Driver driver){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
        }
    }
    public static void updateDriver(Driver driver){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(driver);
            transaction.commit();
        }
    }
    public static void saveDrivers(List<Driver> driverList){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            driverList.stream().forEach((driver)->session.save(driver));
            transaction.commit();
        }
    }
    public static void deleteDriver(Driver driver){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(driver);
            transaction.commit();
        }
    }
    public static Driver getDriver(long id){
        Driver driver;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
           driver= session.get(Driver.class,id);
            transaction.commit();
        }
        return driver;
    }
//TODO:

    //TODO: get drivers sorted

    public static class DrivingLicenseDAO {

    }
}
