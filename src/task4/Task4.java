package task4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

class Task4 {
    public static void main(String[] args) throws IOException {
        File[] files = new File("./src/txts").listFiles();
        assert files != null;
        final String[] keywords = {"racing", "Montoya"};
        System.out.println("Searching by keywords: " + Arrays.toString(keywords));

        for (File file : files) {
            final String text = Files.readString(file.toPath());
            if (KeyWordFinder.matchesKeywords(keywords, text)) {
                System.out.println("Found matches in file: " + file.getPath());
            }
        }
    }
}