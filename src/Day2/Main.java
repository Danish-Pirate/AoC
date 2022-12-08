package Day2;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        try (var stream = Files.lines(Path.of("input"))) {
           long sum = stream.map(x -> x.split(" ")).mapToInt(x -> new GameState().getScore(x)).sum();
            System.out.println(sum);
        } catch (UncheckedIOException uncheckedIOException) {
            throw uncheckedIOException.getCause();
        }
    }
}