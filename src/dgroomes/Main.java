package dgroomes;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        verifyIt("2019-01-01T12:00:00.123", DateTimeFormatter.ISO_DATE_TIME);
        verifyIt("2019-01-01 12:00:00.123", DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS"));
    }

    /**
     * Try different "DateTimeFormatter"s. Trying to learn DateTimeFormatter patterns and the pre-defined options like
     * DateTimeFormatter.ISO_DATE_TIME by exercising different input datetime strings
     */
    private static void verifyIt(String s, DateTimeFormatter f) {
        try {
            var parsed = f.parse(s);
            int found = parsed.get(ChronoField.MILLI_OF_SECOND);
            var expected = 123;
            if (!Objects.equals(expected, found)) {
                System.err.println(String.format("Not equal! Expected %s but found %s for DateTimeFormatter %s", expected, found, f));
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println(String.format("Failed to parse for DateTimeFormatter '%s'. Message: %s", f, e.getMessage()));
            System.exit(1);
        }
    }

}
