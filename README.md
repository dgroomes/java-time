# java-time-playground

📚 Learning and experimenting with [`java.time`](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/time/package-summary.html).

## Instructions

Run with `java src/dgroomes/Main.java` or execute it from within an IDE like Intellij. It should print:

```
ZonedDateTime: 2019-01-01T06:00:00.123-06:00[America/Chicago]
LocalDateTime: 2019-01-01T06:00:00.123
OffsetDatetime: 2019-01-01T06:00:00.123-06:00
ISO_LOCAL_TIME: 06:00:00.123
Custom time: 06:00:00
```

## Notes

I'm trying out Java support in Visual Studio Code. This repository is a good candidate for trying something besides
Intellij because this repo has no dependencies (meaning, there's no need for the complexity of *dependency management*)
and it does not abide by the conventional src/main/java/...` file structure. How simple can a Java project be? How
lightweight can it be? To be fair, I still love Intellij, but for the sake of learning Visual Studio Code I need to let
Intellij sit this one out.
