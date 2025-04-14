package space.nerfthis.data;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * Managed bean for handling graph-related operations and user input in a JSF
 * application. Maintains session-scoped state for coordinate parameters (X, Y,
 * R) and point data. Integrates with database operations through DataBaseBean
 * and provides JSON representation of points.
 *
 * <p>Coordinates and radius values are captured from user input and used to
 * calculate/display points on a graph. Automatically initializes with default
 * radius value (R=1.0).</p>
 */
@ManagedBean
@SessionScoped
public class GraphBean {
  private Double x;
  private Double y;
  private Double r;
  private List<Point> points = new ArrayList<>();
  private String pointsJson = "";
  @Inject private DataBaseBean dataBaseBean;

  /**
   * Initializes bean after dependency injection.
   * Loads existing points from database and converts them to JSON format.
   * Sets default radius value (R=1.0).
   */
  @PostConstruct
  public void init() {
    dataBaseBean.getAllPoints();
    points = dataBaseBean.getPoints();
    pointsJson = JSONBuilder.buildJson(points);
    r = 1.0;
  }

  /**
   * @return current X coordinate value
   */
  public Double getX() { return x; }

  /**
   * @param x new X coordinate value to set
   */
  public void setX(Double x) { this.x = x; }

  /**
   * @return current Y coordinate value
   */
  public Double getY() { return y; }

  /**
   * @param y new Y coordinate value to set
   */
  public void setY(Double y) { this.y = y; }

  /**
   * @return current radius (R) value
   */
  public Double getR() { return r; }

  /**
   * @param r new radius (R) value to set
   */
  public void setR(Double r) { this.r = r; }

  /**
   * @return list of stored Point objects
   */
  public List<Point> getPoints() { return points; }

  /**
   * @return JSON representation of points for frontend visualization
   */
  public String getPointsJson() { return pointsJson; }

  /**
   * @param pointsJson JSON string to set (typically not used directly)
   */
  public void setPointsJson(String pointsJson) { this.pointsJson = pointsJson; }

  /**
   * Creates and stores a new Point based on current X/Y/R values.
   * Performs validation through GeometryValidator and persists to database.
   * Updates JSON representation after adding new point.
   */
  public void addPoint() {
    if (x != null && y != null && r != null) {
      Point newPoint =
          new Point(x, y, r, GeometryValidator.isInsideArea(x, y, r));
      points.add(newPoint);
      dataBaseBean.addPoint(newPoint);
      pointsJson = JSONBuilder.buildJson(points);
    }
  }

  /**
   * Validates if all required form fields (X/Y/R) have values
   * @return true if all coordinate parameters are set, false otherwise
   */
  public boolean isFormValid() { return x != null && y != null && r != null; }
}
