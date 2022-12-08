package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

class Part2_Alt_V2 {
    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Path.of("input"))) {
            int sum = stream.collect(ElfCollector.groupByThreeLines())
                    .stream()
                    .map(Part2_Alt_V2::findSharedChar)
                    .mapToInt(Part2_Alt_V2::getScore)
                    .sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getScore(char sharedChar) {
        if (sharedChar >= 'A' && sharedChar <= 'Z')
            return sharedChar - 64 + 26;
        else
            return sharedChar - 96;
    }

    private static char findSharedChar(List<String> l) {
        String[] s = l.toArray(new String[0]);
        for (int i = 0; i < s[0].length(); i++) {
            char c = s[0].charAt(i);
            if (s[1].indexOf(c) != -1 && s[2].indexOf(c) != -1)
                return c;
        }
        throw new RuntimeException("No shared char in the group");
    }
}

class ElfCollector<String> implements Collector<String, List<List<String>>, List<List<String>>> {
    static ElfCollector<java.lang.String> groupByThreeLines() {
        return new ElfCollector<>();
    }

    @Override
    public Supplier<List<List<String>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<List<String>>, String> accumulator() {
        return (outerList, elf) -> {
            if (outerList.isEmpty() || outerList.get(outerList.size() - 1).size() == 3) {
                outerList.add(new ArrayList<>());
            }
            outerList.get(outerList.size() - 1).add(elf);
        };
    }

    @Override
    public BinaryOperator<List<List<String>>> combiner() {
        return null;
    }

    @Override
    public Function<List<List<String>>, List<List<String>>> finisher() {
        return Collections::unmodifiableList;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}
