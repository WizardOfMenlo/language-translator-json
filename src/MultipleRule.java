/**
 * Created by gf45 on 02/03/17.
 */
public class MultipleRule
{
    private String name;
    public String getName() { return name; }

    private Clause clause;
    private ReplacementRegex[] replacements;
    private LanguageType applyTo;

    public MultipleRule(String name, Clause clause, ReplacementRegex[] rregexs, LanguageType apply)
    {
        this.name = name;
        this.clause = clause;
        replacements = rregexs;
        applyTo = apply;
    }

    public Word[] applyRule (Word[] arr, RuleManager rm)
    {
        Word[] matches = clause.getMatching(arr, rm);
        if (matches == null) { return arr; }

        int indexInArr = 0;

        for (Word match : matches)
        {
            for (int i = indexInArr; i < arr.length; i++)
            {
                switch (applyTo)
                {
                    case ORIGINAl:
                        String valTr = arr[i].getTranslated();
                        if (match.getTranslated().equals(valTr))
                        {
                            indexInArr = i;
                            valTr = valTr.replaceAll(replacements[i].regex, replacements[i].replacement);
                            arr[i].setTranslated(valTr);
                            break;
                        }
                        break;
                    case TRANSLATED:
                        String valOr = arr[i].getTranslated();
                        if (match.getTranslated().equals(valOr))
                        {
                            indexInArr = i;
                            valOr = valOr.replaceAll(replacements[i].regex, replacements[i].replacement);
                            arr[i].setTranslated(valOr);
                            break;
                        }
                        break;
                }

            }
        }

        return arr;
    }
}
