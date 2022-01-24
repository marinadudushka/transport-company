package dao;

import configuration.SessionFactoryUtil;
import dto.TransportCourseDTO;
import entity.TransportCompany;
import entity.TransportCourse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransportCourseDAO {
    public static void saveTransportCourse(TransportCourse transportCourse){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.save(transportCourse);
            transaction.commit();
        }
    }
    public static void updateTransportCourse(TransportCourse transportCourse){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.update(transportCourse);
            transaction.commit();
        }
    }

    public static void deleteTransportCourse(TransportCourse transportCourse){
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            session.delete(transportCourse);
            transaction.commit();
        }
    }
    public static TransportCourse getTransportCourse(long id){
        TransportCourse transportCourse;
        try(Session session= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction= session.beginTransaction();
            transportCourse = session.get(TransportCourse.class,id);
            transaction.commit();
        }
        return transportCourse;
    }

    public static BigDecimal companyEarningsPeriod(LocalDate date1, LocalDate date2,TransportCompany transportCompany){
        BigDecimal earnings;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
            Root<TransportCourse> root = criteriaQuery.from(TransportCourse.class);
            criteriaQuery.select(criteriaBuilder.sum(root.get("price"))).where(criteriaBuilder.and(criteriaBuilder.between(root.get("startDate"),date1,date2),
                            criteriaBuilder.equal(root.get("transportCompany"),transportCompany)));
            earnings = session.createQuery(criteriaQuery).getSingleResult();
        }
        return earnings;
    }


//    public static int getNumberOfFulfilledTransportCourses(long id){
//        int count;
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            count = session.createQuery(
//                    "select new dto.TransportCourseDTO(t.id) from TransportCourse t " +
//                            "join t.transportCompany c " +
//                            "where c.id=?1 and t.status='FULFILLED'" , TransportCourseDTO.class).setParameter(1, id).getFetchSize();
//            transaction.commit();
//        }
//        return count;
//    }

}

/*select new dto.TransportCourseDTO(t.id) from TransportCourse t " +
                            "join t.transportCompany c " +
                            "where c.id=?1 and t.status=FULFILLED" , TransportCourse.class).*/