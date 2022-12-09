package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {
    public static void main(String[] args) {
        try(var stream = Files.lines(Path.of("input"))) {
            var sum = stream
                    .map(x -> x.split(","))
                    .map(Part2::convertToIntArray)
                    .mapToInt(Part2::isOverLapping).sum();
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
        int array1Start = intArray[0][0];
        int array1End = intArray[0][1];
        int array2Start = intArray[1][0];
        int array2End = intArray[1][1];
        if ((array1Start <= array2Start || array1End >= array2End))
            return 1;
        else
            return 0;
    }
}
