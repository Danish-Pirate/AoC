package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Open the file
            FileReader fr = new FileReader("input");
            BufferedReader br = new BufferedReader(fr);

            // Read the file as a stream of characters
            int nextChar;
            List<Character> charList = new ArrayList<>();
            while ((nextChar = br.read()) != -1) {
                charList.add((char)nextChar);
            }
            var startMarkerStart = findStartMarker(charList);
            var messageMarkerStart = findMessageMarker(charList);
            System.out.println(startMarkerStart);
            System.out.println(messageMarkerStart);

            // Close the file
            br.close();
        } catch (IOException e) {
            // Handle any errors that may have occurred
            e.printStackTrace();
        }
    }

    private static int findStartMarker(List<Character> charList) {
        for (int i = 0; i < charList.size(); i++) {
            Set<Character> uniqueSet = new HashSet<>();
            uniqueSet.add(charList.get(i));
            uniqueSet.add(charList.get(i+1));
            uniqueSet.add(charList.get(i+2));
            uniqueSet.add(charList.get(i+3));
            if (uniqueSet.size() == 4) {
                return i + 4;
            }
        }
        throw new RuntimeException("Marker not found");
    }

    private static int findMessageMarker(List<Character> charList) {
        for (int i = 0; i < charList.size(); i++) {
            int expected = 14;
            Set<Character> uniqueSet = new HashSet<>();
            for(int j = i; j < i + expected; j++) uniqueSet.add(charList.get(j));
            if (uniqueSet.size() == expected)  return i + 14;
        }
        throw new RuntimeException("Marker not found");
    }
}
