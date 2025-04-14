package space.nerfthis.data;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Managed bean for database interaction using Hibernate.
 * Provides operations to save and retrieve Point objects from the database.
 *
 * <p>Uses session scope (SessionScoped) to maintain state between user
 * requests. Hibernate configuration is loaded from hibernate.cfg.xml.</p>
 *
 * @ManagedBean annotation marks this class as a JSF managed bean with name
 * "dataBase".
 * @SessionScoped indicates the bean's lifecycle spans a user session.
 */

@ManagedBean(name = "dataBase", eager = true)
@SessionScoped
public class DataBaseBean {
  private String result;
  private SessionFactory sessionFactory;
  private List<Point> points;

  public DataBaseBean() {
    try {
      sessionFactory = new Configuration()
                           .configure("hibernate.cfg.xml")
                           .buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Инициализация SessionFactory завершилась неудачей: " +
                         ex);
      throw new RuntimeException(ex);
    }
  }

  /**
   * Saves a Point object to the database within a transaction.
   *
   * @param point the Point object to persist
   * @return operation result ("added [Point]" on success, "error" on failure)
   */
  public String addPoint(Point point) {
    Transaction transaction = null;
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      session.persist(point);
      transaction.commit();
      return "added " + point;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      return "error";
    }
  }
  /**
   * Returns the list of points loaded from the database.
   *
   * @return list of Point objects
   */
  public List<Point> getPoints() { return points; }

  /**
   * Gracefully shuts down the Hibernate SessionFactory.
   * Should be called during application shutdown to release resources.
   */
  public void shutdown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }

  /**
   * Loads all points from the database and updates the internal list.
   *
   * @return navigation result "goToTablePage" on successful execution
   */
  public String getAllPoints() {
    try (Session session = sessionFactory.openSession()) {
      points = session.createQuery("FROM Point", Point.class).getResultList();
      result = "goToTablePage";
      return result;
    }
  }
}
