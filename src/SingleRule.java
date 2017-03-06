/**
 * Created by gf45 on 01/03/17.
 */
public class SingleRule implements IValidable
{

    // Default ctor
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

    // Apply the replacement to the string
    public String apply(String s)
    {
        return s.replaceAll(replacementRegex.regex, replacementRegex.replacement);
    }

    public Word apply(Word w, RuleManager rm)
    {
        // If if doesn't match all the checks, do nothing
        for (String check : checks)
        {
            if(!rm.getCheck(check).check(w)) return w;
        }

        return replacementRegex.applyTo(w, applyTo);
    }

    @Override
    public boolean valid()
    {
        return !name.trim().equals("") && checks.length > 0 && replacementRegex != null;
    }
}
