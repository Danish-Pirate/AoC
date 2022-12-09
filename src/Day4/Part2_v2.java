package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part2_v2 {
    public static void main(String[] args) {
        try(var stream = Files.lines(Path.of("input"))) {
            var sum = stream
                    .map(x -> x.split(","))
                    .map(Part2_v2::convertToRangeArray)
                    .filter(a -> a[0].overlaps(a[1])).count();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Range[] convertToRangeArray(String[] s) {
        Range[] rangeArray = new Range[2];
        for (int i = 0; i < s.length; i++) {
            String[] sa = s[i].split("-");
            int startRange = Integer.parseInt(sa[0]);
            int endRange = Integer.parseInt(sa[1]);
            rangeArray[i] = new Range(startRange, endRange);
        }
        return rangeArray;
    }
}

record Range(int startRange, int endRange) {
    boolean overlaps(Range other) {
        return (startRange >= other.startRange && startRange <= other.endRange) || (other.startRange >= startRange && other.startRange <= endRange);
    }
}
