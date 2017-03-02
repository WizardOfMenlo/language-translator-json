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
        // TODO
        // Get the singular of a word
        String singular = rm.getSingleRuleByName("makeSingular").apply(word, rm);

        try (Stream<String> ss = Files.lines(path_))
        {
            Optional<String> line = ss.filter(s -> s.split(",")[0].equals(singular)).findFirst();
            if (line.isPresent()) { return Word.fromCsv(line.get(), word, rm); }
            else { throw new IllegalArgumentException("Word not present in the dictionary"); }
        }
        catch (IOException ioe)
        {
            throw new IllegalArgumentException("Error in accessing the database");
        }

    }
}
