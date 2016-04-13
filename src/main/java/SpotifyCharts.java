/*
 * SpotifyCharts
 */

import java.util.*;
import com.google.common.collect.*;

public class SpotifyCharts {

    private static final Map<Integer,String> SHAPES = ImmutableMap.of(
        3, "triangle",
        4, "quadrilateral",
        5, "pentagon"
    );

    public static Optional<String> getShape(int numOfSides) {
        String shape = SHAPES.get(numOfSides);
        if (shape == null) {
          return Optional.empty();
        }
        else {
          return Optional.of(shape);
        }
    }

    // defaults to 0
    private int length;
    private int width;

    public SpotifyCharts(final int length, final int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getArea() {
        return length * width;
    }
}
