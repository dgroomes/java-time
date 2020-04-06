package dgroomes;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class Main {

    private static final Instant JAN_1_2019_INSTANT = Instant.parse("2019-01-01T12:00:00.123Z");
    private static final ZoneId AMERICA_CHICAGO = ZoneId.of("America/Chicago");

    public static void main(String[] args) {
        verifyParsing("2019-01-01T12:00:00.123", DateTimeFormatter.ISO_DATE_TIME);
        verifyParsing("2019-01-01 12:00:00.123", DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS"));

        verifyFormatting(DateTimeFormatter.ISO_DATE_TIME.withZone(AMERICA_CHICAGO), "2019-01-01T06:00:00.123-06:00[America/Chicago]");
        verifyFormatting(DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(AMERICA_CHICAGO), "2019-01-01T06:00:00.123-06:00");
    }

    /**
     * Try different "DateTimeFormatter"s. Trying to learn DateTimeFormatter patterns and the pre-defined options like
     * DateTimeFormatter.ISO_DATE_TIME by exercising different input datetime strings
     */
    private static void verifyParsing(String s, DateTimeFormatter f) {
        try {
            var parsed = f.parse(s);
            int found = parsed.get(ChronoField.MILLI_OF_SECOND);
            var expected = 123;
            if (expected != found) {
                System.err.println(String.format("Not equal! Expected %s but found %s for DateTimeFormatter %s", expected, found, f));
                System.exit(1);
            }
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Failed to parse for DateTimeFormatter '%s'. Message: %s", f, e.getMessage()));
        }
    }

    private static void verifyFormatting(DateTimeFormatter f, String expected) {
        var found = f.format(JAN_1_2019_INSTANT);
        if (!expected.equals(found)) {
            throw new IllegalStateException(String.format("Not equal! Expected %s but found %s for DateTimeFormatter %s", expected, found, f));
        }
    }
}
