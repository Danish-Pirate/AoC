import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part2_Alt {
    public static void main(String[] args) {
        try(Stream<String> stream = Files.lines(Path.of("input"))) {
            List<String> list = stream.toList();
            int groupSize = 3;
            int sum = IntStream.range(0, list.size() / groupSize)
                    .mapToObj(i -> list.subList(i * groupSize, Math.min(groupSize * (i + 1), list.size())))
                    .mapToInt(Part2_Alt::getScore).sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getScore(List<String> l) {
        String[] s = l.toArray(new String[0]);
        char sharedChar = findSharedChar(s);
        if (sharedChar >= 'A' && sharedChar <= 'Z')
            return sharedChar - 64 + 26;
        else
            return sharedChar - 96;
    }

    private static char findSharedChar(String[] s) {
        for (int i = 0; i < s[0].length(); i++) {
            char c = s[0].charAt(i);
            if (s[1].indexOf(c) != -1 && s[2].indexOf(c) != -1)
                return c;
        }
        return 0;
    }
}

