package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private static final Session session = getSessionFactory().openSession();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String command = "CREATE TABLE users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(command).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Создать таблицу не удалось");
        }
    }

    @Override
    public void dropUsersTable() {
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String command = "DROP TABLE IF EXISTS users";

            Query query = session.createSQLQuery(command).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Удалить таблицу не удалось");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM users WHERE id= ?").setParameter(1, id);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Query<User>  query = session.createQuery("select e from User e");
        return query.list();
    }

    @Override
    public void cleanUsersTable() {
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM users");
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
