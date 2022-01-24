package dao;

import configuration.SessionFactoryUtil;
import dto.DriverEarningsDTO;
import dto.DriverCourseDTO;
import dto.DriverDTO;
import dto.TransportCourseDTO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;


public class TransportCompanyDAO {
    public static void saveTransportCompany(TransportCompany transportCompany){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(transportCompany);
            transaction.commit();
        }
    }
    public static void deleteTransportCompany(TransportCompany transportCompany){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(transportCompany);
            transaction.commit();
        }
    }
    public static void updateTransportCompany(TransportCompany transportCompany){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(transportCompany);
            transaction.commit();
        }
    }
    public static TransportCompany getTransportCompany(long id){
       TransportCompany transportCompany;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            transportCompany = session.get(TransportCompany.class,id);
            transaction.commit();
        }
        return transportCompany;
    }
/**
* Get Transport Company's Drivers sorted by their qualification and their salary
*/

    public static List<DriverDTO> getTransportCompanyDriversSorted(long id) {
        List<DriverDTO> driverList;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driverList = session.createQuery(
                    "select new dto.DriverDTO(d.id,d.name,d.salary,license.drivingCategory) from Driver d "+
                            "join d.transportCompany as company "+
                            "join DrivingLicense as license on license.driver=d " +
                            "where company.id=?1 " +
                            "group by d.id, license.drivingCategory "+
                            "order by license.drivingCategory desc, d.salary asc"
                    ,DriverDTO.class).setParameter(1, id).getResultList();
            transaction.commit();
        }
        return driverList;
    }


/**
*  Get Transport Company's drivers and number of transport courses they fulfilled
*/

    public static List<DriverCourseDTO> getDriversAndNumberOfCourses(long id){
        List<DriverCourseDTO> driverList;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driverList = session.createQuery(
                    "select new dto.DriverCourseDTO(d.id,d.name,count(course.id)) from Driver d "+
                            "join d.transportCompany as company "+
                            "join TransportCourse as course on course.driver=d " +
                            "where company.id=?1 and course.transportCourseStatus='FULFILLED' " +
                            "group by d.id"
                            ,DriverCourseDTO.class).setParameter(1, id).getResultList();
            transaction.commit();
        }
        return driverList;
    }
    /**
     * Get the revenue that every driver has brought the company by fulfilling transport courses
     * */
    public static List<DriverEarningsDTO> getDriversAndEarnings(long id){
        List<DriverEarningsDTO> driverList;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driverList = session.createQuery(
                    "select new dto.DriverEarningsDTO(d.id,d.name,sum(course.price)) from Driver d "+
                            "join d.transportCompany as company "+
                            "join TransportCourse as course on course.driver=d " +
                            "where company.id=?1 and course.transportCourseStatus='FULFILLED' and course.paid=true " +
                            "group by d.id"
                    , DriverEarningsDTO.class).setParameter(1, id).getResultList();
            transaction.commit();
        }
        return driverList;
    }
    /**
    * Get all transport courses of a company
    * */

    public static List<TransportCourseDTO> getTransportCourses(long id){
        List<TransportCourseDTO> transportCourses;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCourses = session.createQuery(
                    "select new dto.TransportCourseDTO(t.id,t.startPoint, t.endPoint, t.startDate, t.endDate, t.transportType,t.transportCourseStatus) from TransportCourse t " +
                            "join t.transportCompany c " +
                            "where c.id=?1",TransportCourseDTO.class).setParameter(1, id).getResultList();
            transaction.commit();
        }
        return transportCourses;
    }

/**
 * Get Transport Company's earnings from all fulfilled courses.
 * */

    public static BigDecimal getEarningsFromCourses(TransportCompany transportCompany){
        BigDecimal earnings;
    try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<TransportCourse> root = criteriaQuery.from(entity.TransportCourse.class);
        criteriaQuery.select(criteriaBuilder.sum(root.get("price"))).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("transportCompany"),transportCompany)),criteriaBuilder.equal(root.get("transportCourseStatus"),TransportCourseStatus.FULFILLED));
        earnings = session.createQuery(criteriaQuery).getSingleResult();
        transaction.commit();
    }
    return earnings;
    }

    /**
     * Get the number of Transport Company's fulfilled transport courses
     * */

    public static Long getNumberOfFulfilledTransportCourses(TransportCompany transportCompany){
        Long countOfFulfilled;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<TransportCourse> root = criteriaQuery.from(entity.TransportCourse.class);
            criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(TransportCourse.class))).
                    where(criteriaBuilder.and(criteriaBuilder.equal(root.get("transportCourseStatus"),TransportCourseStatus.FULFILLED),
                            criteriaBuilder.equal(root.get("transportCompany"),transportCompany)));
            countOfFulfilled = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
        }
        return countOfFulfilled;
    }






}
