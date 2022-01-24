package dao;

import configuration.SessionFactoryUtil;
import entity.DrivingLicense;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DrivingLicenseDAO {
    public static void saveDrivingLicense(DrivingLicense drivingLicense){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            session.save(drivingLicense);
            transaction.commit();
        }
    }

    public static void deleteDrivingLicense(DrivingLicense drivingLicense){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(drivingLicense);
            transaction.commit();
        }
    }
    public static void updateDrivingLicense(DrivingLicense drivingLicense){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(drivingLicense);
            transaction.commit();
        }
    }
    public static DrivingLicense getDrivingLicense(long id){
        DrivingLicense drivingLicense;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            drivingLicense = session.get(DrivingLicense.class,id);
            transaction.commit();
        }
        return drivingLicense;
    }
}
