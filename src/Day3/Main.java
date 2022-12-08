package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try (var stream = Files.lines(Path.of("input"))) {
            long sum = stream.mapToInt(InputParser::getScore).sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}