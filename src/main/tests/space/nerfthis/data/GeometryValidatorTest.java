package space.nerfthis.data;
import org.junit.Test;
import static org.junit.Assert.*;
public class GeometryValidatorTest {

    @Test
    public void testInsideTriangle() {
        assertTrue(GeometryValidator.isInsideArea(-2, 3, 4)); // Точка внутри треугольника
    }

    @Test
    public void testOnTriangleEdge() {
        assertTrue(GeometryValidator.isInsideArea(-4, 0, 8)); // На границе треугольника
    }

    @Test
    public void testInsideRectangle() {
        assertTrue(GeometryValidator.isInsideArea(-3, -2, 6)); // Внутри прямоугольника
    }

    @Test
    public void testOnRectangleEdge() {
        assertTrue(GeometryValidator.isInsideArea(-6, -3, 12)); // На границе прямоугольника
    }

    @Test
    public void testInsideCircle() {
        assertTrue(GeometryValidator.isInsideArea(2, 2, 3)); // Внутри круга
    }

    @Test
    public void testOnCircleEdge() {
        assertTrue(GeometryValidator.isInsideArea(3, 0, 3)); // На границе круга
    }

    @Test
    public void testOutsideCircle() {
        assertFalse(GeometryValidator.isInsideArea(4, 4, 5)); // Вне круга
    }
}