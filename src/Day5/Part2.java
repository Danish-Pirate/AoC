package Day5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {
    public static void main(String[] args) {
        try (var stream = Files.lines((Path.of("input")))) {
            List<String> list = stream.toList();
            var stack = solveQ1(list);
            var instructions = getMoveOrder(list);
            solvePart1(stack, instructions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void solvePart1(List<LinkedList<Character>> stack, List<List<Integer>> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            int numberOfMoves = instructions.get(i).get(0);
            int stackToMoveFrom = instructions.get(i).get(1)-1;
            int stackToMoveTo = instructions.get(i).get(2)-1;
            System.out.println(instructions.get(i));
            for (int j = numberOfMoves; j > 0; j--) {
                int offset = stack.get(stackToMoveFrom).size() - j;
                char c = stack.get(stackToMoveFrom).get(offset);
                stack.get(stackToMoveFrom).remove(offset);
                stack.get(stackToMoveTo).add(c);
            }
        }
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i).getLast());
        }
    }

    static List<List<Integer>> getMoveOrder(List<String> input) {
        int index = 10;
        List<List<Integer>> moveOrders = new ArrayList<>();
        while (input.get(index).charAt(0) == 'm') {
            String moves = input.get(index).replaceAll("[^0-9]+", "");
            List<Integer> moveOrderInt = new ArrayList<>();
            if (moves.length() == 3) {
                moveOrderInt.add(Integer.parseInt(String.valueOf(moves.charAt(0))));
                moveOrderInt.add(Integer.parseInt(String.valueOf(moves.charAt(1))));
                moveOrderInt.add(Integer.parseInt(String.valueOf(moves.charAt(2))));
            }
            else {
                moveOrderInt.add(Integer.parseInt(moves.substring(0, 2)));
                moveOrderInt.add(Integer.parseInt(String.valueOf(moves.charAt(2))));
                moveOrderInt.add(Integer.parseInt(String.valueOf(moves.charAt(3))));
            }
            moveOrders.add(moveOrderInt);
            index++;
            if (index == input.size())
                break;
        }
        return moveOrders;
    }

    static List<LinkedList<Character>> solveQ1(List<String> input) {

        int numberOfStacks = 9;

        // prepare the stacks
        var stacks = IntStream
                .range(0, numberOfStacks)
                .mapToObj(i -> new LinkedList<Character>())
                .toList();

        int index = 0;
        // while we are in the first part of the input
        while (index != 8) {
            // for each line
            var line = input.get(index);
            // fill the value if it's there
            for (int i = 0; i < 9; i++) {
                var ch = line.charAt(1 + (i * 4));
                if (ch != ' ') {
                    stacks.get(i).addFirst(ch);
                }
            }
            index++;
        }
        return stacks;
    }
}
