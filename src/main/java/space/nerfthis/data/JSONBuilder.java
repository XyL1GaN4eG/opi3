package space.nerfthis.data;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Utility class for converting collections of Point objects to JSON format.
 * <p>
 * Provides static methods to serialize Point data into a JSON array
 * representation with specific numeric formatting rules. Uses {@link Locale#US}
 * for consistent decimal number formatting (e.g., periods as decimal
 * separators).
 * </p>
 *
 * <p>This class is not meant to be instantiated.</p>
 */
public class JSONBuilder {

  /**
   * Converts a list of Point objects to a JSON array string.
   *
   * Formats numeric values with the following precision:
   * <ul>
   *   <li>X/Y coordinates: 10 decimal places</li>
   *   <li>R value: 6 decimal places</li>
   *   <li>Flag: boolean literal</li>
   * </ul>
   * Uses US locale formatting to ensure consistent decimal representation.
   *
   *
   * @param dataList List of Point objects to serialize (non-null)
   * @return JSON array string in the format:
   *         [{x: xVal, y: yVal, r: rVal, flag: flagVal}, ...]
   * @throws NullPointerException if dataList is null
   */
  public static String buildJson(List<Point> dataList) {

    return dataList.stream()
        .map(data
             -> String.format(
                 Locale.US,
                 "{\"x\": %.10f, \"y\": %.10f, \"r\": %f, \"flag\": %s}",
                 data.getX(), data.getY(), data.getR(), data.isFlag()))
        .collect(Collectors.joining(", ", "[", "]"));
  }
}
