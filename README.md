# Student Tracker CLI (Java)

A console-based student record management system built with **Java Core**, demonstrating Object-Oriented Programming, the Java Collections Framework (`HashMap`, `ArrayList`), custom comparators, and persistent File I/O (`java.nio.file`).

## Key Engineering Highlights
* **$O(1)$ Record Lookup:** Uses `HashMap<String, Student>` backed by custom `equals()` and `hashCode()` implementations on unique student IDs.
* **Multi-Criteria Sorting:** Extracts and sorts records alphabetically or by descending GPA using `ArrayList` and `Comparator`.
* **Persistent CSV Storage:** Automatically serializes and restores application state on disk via `BufferedReader` and `BufferedWriter`.
* **Defensive Input Validation:** Exception-safe CLI menu handling malformed numeric inputs and out-of-range GPA values.

## How to Run
```bash
javac src/*.java
java -cp src Main
