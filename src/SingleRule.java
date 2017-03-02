/**
 * Created by gf45 on 01/03/17.
 */
public class SingleRule
{

    public SingleRule(String name, String[] checks, ReplacementRegex rregex, LanguageType applyTo)
    {
        this.name = name;
        this.checks = checks;
        replacementRegex = rregex;
        this.applyTo = applyTo;
    }

    private String name;

    public String getName() { return name; }

    private String[] checks;

    private ReplacementRegex replacementRegex;

    private LanguageType applyTo;

    public String apply(String s)
    {
        return s.replaceAll(replacementRegex.regex, replacementRegex.replacement);
    }

    public Word apply(Word w, RuleManager rm)
    {
        boolean satisfies = true;
        for (String check : checks)
        {
            satisfies = satisfies && rm.getCheck(check).check(w);
        }

        if (satisfies)
        {
            String s = "";
            switch (applyTo)
            {
                case ORIGINAl:
                    s = w.getOriginal();
                    break;
                case TRANSLATED:
                    s = w.getTranslated();
                    break;
            }

            s = s.replaceAll(replacementRegex.regex, replacementRegex.replacement);

            switch (applyTo)
            {
                case ORIGINAl:
                    w.setOriginal(s);
                    break;
                case TRANSLATED:
                    w.setTranslated(s);
                    break;
            }

            return w;
        }
        else
        {
            return w;
        }

    }
}
