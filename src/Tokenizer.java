import java.util.stream.Stream;

/**
 * Created by gf45 on 03/03/17.
 */
public class Tokenizer
{
    public static String[] tokenize(String in)
    {
        return Stream.of(in.split(" "))
                .map(s -> s.trim())
                .filter(s -> !s.equals(""))
                .toArray(String[]::new);
    }
}
