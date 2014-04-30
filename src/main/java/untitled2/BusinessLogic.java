package untitled2;

import com.vaadin.ui.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by epic on 24.4.2014.
 */
@Service
@Scope("singleton")
public class BusinessLogic {

    @PersistenceContext
    EntityManager em;

    @Transactional
    void createUser(User user) throws Exception {
        Address address = new Address();
        address.setCity("city");
        address.setStreet("s");
        user.setAddress(address);

        em.persist(user);
    }

    Collection<User> getAllUsers() {
        List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
        return users;
    }

    User findUser(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    public int getUsersCount() {
        final SpringContextHelper ctx = new SpringContextHelper();
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Connection conn = DataSourceUtils.getConnection(dataSource);
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("select count(*) from tb_user");
            rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    @Transactional
    public void createRandomUser(User user) {
        final SpringContextHelper ctx = new SpringContextHelper();
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into tb_user (id, name, surname) values (?, ?, ?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            int i = stmt.executeUpdate();
            if (i != 1)
                throw new RuntimeException("xxx");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Transactional
    public void createTwoUsers12(User user1, User user2) throws Exception {
        this.createUser(user1);
        this.createRandomUser(user2);
    }

    @Transactional
    public void createTwoUsers21(User user1, User user2) throws Exception {
        this.createRandomUser(user2);
        this.createUser(user1);
    }

    @PostConstruct
    public void initIt() throws Exception {
        System.out.println("Business logic initialized");
    }

    @PreDestroy
    public void cleanUp() throws Exception {
        System.out.println("Business logic destroyed");
    }
}
