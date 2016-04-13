import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import com.google.common.collect.*;

/*
 * SpotifyChartsTest
 */

public class SpotifyChartsTest {
    @Test public void testConstructor() {
        SpotifyCharts instance = new SpotifyCharts(6,9);
        assertEquals("constructor sets length as first argument", 6, instance.getLength());
        assertEquals("constructor sets width as second argument", 9, instance.getWidth());
    }
    @Test public void testArea() {
        SpotifyCharts instance = new SpotifyCharts(6,9);
        assertEquals("getArea() returns the product of length and width", 54, instance.getArea());
    }
    @Test public void testGetShape() {
        // Optional<String> shape = SpotifyCharts.getShape(3);
        // if (shape.isPresent()) {
        //     System.out.println(shape.get());
        // }
        assertEquals("SpotifyCharts.getShape() returns triangle w/param of 3", Optional.of("triangle"),  SpotifyCharts.getShape(3));
        assertEquals("SpotifyCharts.getShape() returns pentagon w/param of 5", Optional.of("pentagon"),  SpotifyCharts.getShape(5));
        assertEquals("SpotifyCharts.getShape() returns absent for shape not in dict", Optional.empty(),  SpotifyCharts.getShape(80));
    }
}
