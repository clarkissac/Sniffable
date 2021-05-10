package at.ac.fhstp.demo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeartApp {
    private static final String HEART_PATH = "src\\main\\java\\at\\ac\\fhstp\\demo\\heart.csv";

    public static String getHearts(int olderThan) {
        try {
            List<Heart> hearts = streamFile(HEART_PATH).skip(1)
                    .map(mapToHeart)
                    .filter(h -> h.age > 60)
                    .collect(Collectors.toList());
            return hearts.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    private static Stream<String> streamFile(String source) throws Exception {
        Path path = Paths.get(source);
        Stream<String> lines = Files.lines(path);
        return lines;
    }

    private static Function<String, Heart> mapToHeart = (line) -> {
        String[] p = line.split(",");
        Heart heart = new Heart(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4]);
        return heart;
    };
}
