import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by gf45 on 01/03/17.
 */
public class Check
{
    public Check(String name, GrammaticalType[] appliesTo, LanguageType applyTo, String regex)
    {
        this.name = name;
        this.appliesTo = appliesTo;
        this.applyTo = applyTo;
        regexChecker = regex;
    }

    private String name;

    private GrammaticalType[] appliesTo;
    private LanguageType applyTo;

    private String regexChecker;

    public boolean check(String toCheck)
    {
        Pattern p = Pattern.compile(regexChecker);
        Matcher m = p.matcher(toCheck);
        return m.find();
    }

    public boolean check(Word wd)
    {
        if (Stream.of(appliesTo).filter(t -> t == wd.getType()).count() == 0) { return false; }
        else
        {
            switch (applyTo)
            {
                case ORIGINAl:
                    return check(wd.getOriginal());
                case TRANSLATED:
                    return check(wd.getTranslated());
            }
        }
        throw new AssertionError("Unreachable code detected");
    }

    public String getName()
    {
        return name;
    }
}
