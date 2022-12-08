package Day3;

public class InputParser {
    public static int getScore(String s) {
        char sharedChar = findSharedChar(s);
        if (sharedChar >= 'A' && sharedChar <= 'Z')
            return sharedChar - 64 + 26;
        else
            return sharedChar - 96;
    }

    private static char findSharedChar(String s) {
            int half = s.length() / 2;
            for (int i = 0; i < half; i++) {
                char c = s.charAt(i);
                if (s.indexOf(c, half) != -1) {
                    return c;
                }
            }
            return 0;
        }
}
