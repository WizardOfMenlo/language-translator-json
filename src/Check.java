import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by gf45 on 01/03/17.
 */
public class Check
{
    public Check(String name, GrammaticalType[] appliesToGT, NumberType[] appliesToNumber, GenderType[] appliesToGender, LanguageType applyToLang, String regex)
    {
        this.name = name;
        this.appliesToType = appliesToGT;
        this.appliesToGender = appliesToGender;
        this.appliesToNumber = appliesToNumber;
        this.applyToLanguage = applyToLang;
        regexChecker = regex;
    }

    private String name;

    private GrammaticalType[] appliesToType;
    private NumberType[] appliesToNumber;
    private GenderType[] appliesToGender;
    private LanguageType applyToLanguage;

    private String regexChecker;

    public boolean check(String toCheck)
    {
        Pattern p = Pattern.compile(regexChecker);
        Matcher m = p.matcher(toCheck);
        return m.find();
    }

    public boolean check(Word wd)
    {
        if (Stream.of(appliesToType).filter(t -> t == wd.getType()).count() == 0) { return false; }
        else if (Stream.of(appliesToGender).filter(t -> t == wd.getGender()).count() == 0) { return false; }
        else if (Stream.of(appliesToNumber).filter(t -> t == wd.getNumber()).count() == 0) { return false; }
        else
        {
            switch (applyToLanguage)
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
