/**
 * Created by gf45 on 02/03/17.
 */
public class ReplacementRegex
{
    public ReplacementRegex(String regex, String replacement, GenderType replacementGender, NumberType replacementNumber)
    {
        this.regex = regex;
        this.replacement = replacement;
        this.replacementGender = replacementGender;
        this.replacementNumber = replacementNumber;
    }

    public String regex;
    public String replacement;

    public GenderType replacementGender;
    public NumberType replacementNumber;

    public Word applyTo(Word w, LanguageType lang)
    {
        w.setGender(replacementGender);
        w.setNumber(replacementNumber);

        String s;
        switch (lang)
        {
            case ORIGINAl:
                s = w.getOriginal();
                s = s.replaceAll(regex, replacement);
                w.setOriginal(s);
                break;
            case TRANSLATED:
                s = w.getTranslated();
                s = s.replaceAll(regex, replacement);
                w.setTranslated(s);
                break;
        }

        return w;
    }
}
