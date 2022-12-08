package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {
    public static void main(String[] args) {
        try(var stream = Files.lines(Path.of("input"))) {
            var sum = stream
                    .map(x -> x.split(","))
                    .map(Part1::convertToIntArray)
                    .mapToInt(Part1::isOverLapping).sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[][] convertToIntArray(String[] s) {
        int[][] intArray = new int[2][2];
        for (int i = 0; i < s.length; i++) {
            String[] sa = s[i].split("-");
            intArray[i][0] = Integer.parseInt(sa[0]);
            intArray[i][1] = Integer.parseInt(sa[1]);
        }
        return intArray;
    }

    static int isOverLapping(int[][] intArray) {
        if ((intArray[0][0] >= intArray[1][0] && intArray[0][1] <= intArray[1][1]) || (intArray[1][0] >= intArray[0][0] && intArray[1][1] <= intArray[0][1]))
            return 1;
        else
            return 0;
    }
}
