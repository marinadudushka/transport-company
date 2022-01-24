package dao;

import configuration.SessionFactoryUtil;
import entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO {
    public static void saveClient(Client client){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }

    public static void deleteClient(Client client){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }
    public static void updateClient(Client client){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        }
    }
    public static Client getClient(long id){
        Client client;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class,id);
            transaction.commit();
        }
        return client;
    }
}
