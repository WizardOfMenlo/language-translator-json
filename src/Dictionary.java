import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Dictionary
{
    private Path path_;

    public Dictionary(String path)
    {
        path_ = Paths.get(path);
    }

    public Word getWord(String word, RuleManager rm) throws IllegalArgumentException
    {
        // Bring the word to lower case
        word = word.trim().toLowerCase();

        // Get a singular form of the word
        String singular = rm.getSingleRuleByName("makeSingular").apply(word);

        // Open the dictionary file
        try (Stream<String> ss = Files.lines(path_))
        {
            // Find the first match with the singular
            Optional<String> line = ss.filter(s -> s.split(",")[0].equals(singular)).findFirst();

            // If we find one, create a word from the csv
            if (line.isPresent()) { return Word.fromCsv(line.get(), word, rm); }
            else { throw new IllegalArgumentException("Word not present in the dictionary " + singular); }
        }
        catch (IOException ioe)
        {
            throw new IllegalArgumentException("Error in accessing the database");
        }

    }
}
