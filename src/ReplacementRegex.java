/**
 * Created by gf45 on 02/03/17.
 */
public class ReplacementRegex
{
    public ReplacementRegex(String regex, String replacement, GenderReplacementType replacementGender, NumberReplacementType replacementNumber)
    {
        this.regex = regex;
        this.replacement = replacement;
        this.replacementGender = replacementGender;
        this.replacementNumber = replacementNumber;
    }

    public String regex;
    public String replacement;

    public GenderReplacementType replacementGender;
    public NumberReplacementType replacementNumber;

    public Word applyTo(Word w, LanguageType lang)
    {
        switch (replacementGender)
        {
            case MALE:
                w.setGender(GenderType.MALE);
                break;
            case FEMALE:
                w.setGender(GenderType.FEMALE);
                break;
            case UNCHANGED:
                break;
        }

        switch (replacementNumber)
        {
            case SINGULAR:
                w.setNumber(NumberType.SINGULAR);
                break;
            case PLURAL:
                w.setNumber(NumberType.PLURAL);
                break;
            case UNCHANGED:
                break;
        }

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
