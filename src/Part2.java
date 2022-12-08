import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
    public static void main(String[] args) {
        try( BufferedReader br = new BufferedReader(new FileReader("input"))) {
            int sum = 0;
            String[] threeLineArray = new String[3];
            for (int i = 0; i < 301 / 3; i++) {
                for (int j = 0; j < 3; j++) {
                    threeLineArray[j] = br.readLine();
                }
                sum += getScore(threeLineArray);
            }
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getScore(String[] s) {
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
