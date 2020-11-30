package dgroomes;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static java.time.temporal.ChronoField.*;

public class Main {

    private static final Instant JAN_1_2019_INSTANT = Instant.parse("2019-01-01T12:00:00.123Z");
    private static final ZoneId AMERICA_CHICAGO = ZoneId.of("America/Chicago");

    public static void main(String[] args) {
        verifyParsing("2019-01-01T12:00:00.123", DateTimeFormatter.ISO_DATE_TIME);
        verifyParsing("2019-01-01 12:00:00.123", DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS"));

        verifyFormatting(DateTimeFormatter.ISO_DATE_TIME.withZone(AMERICA_CHICAGO), "2019-01-01T06:00:00.123-06:00[America/Chicago]");
        verifyFormatting(DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(AMERICA_CHICAGO), "2019-01-01T06:00:00.123-06:00");

        // Print the default formatting for various types in "java.time" when "toString" is called. In this case, the
        // toString method is called by the printf method.
        ZonedDateTime zonedDateTime = JAN_1_2019_INSTANT.atZone(AMERICA_CHICAGO);
        System.out.printf("ZonedDateTime: %s%n", zonedDateTime);
        LocalDateTime localDateTime = LocalDateTime.from(JAN_1_2019_INSTANT.atZone(AMERICA_CHICAGO));
        System.out.printf("LocalDateTime: %s%n", localDateTime);
        OffsetDateTime offsetDateTime = OffsetDateTime.from(JAN_1_2019_INSTANT.atZone(AMERICA_CHICAGO));
        System.out.printf("OffsetDatetime: %s%n", offsetDateTime);

        // Custom formatting
        String localTime = DateTimeFormatter.ISO_LOCAL_TIME.format(JAN_1_2019_INSTANT.atZone(AMERICA_CHICAGO));
        System.out.printf("ISO_LOCAL_TIME: %s%n", localTime);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();
        String customTime = formatter.format(JAN_1_2019_INSTANT.atZone(AMERICA_CHICAGO));
        System.out.printf("Custom time: %s%n", customTime);

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
