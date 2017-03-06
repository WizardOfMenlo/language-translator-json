/**
 * Created by gf45 on 02/03/17.
 */
public class MultipleRule implements IValidable
{
    private String name;
    public String getName() { return name; }

    private Clause clause;
    private ReplacementRegex[] replacements;
    private LanguageType applyTo;

    private int[] rearrangements;

    // Constructor
    public MultipleRule(String name, Clause clause, ReplacementRegex[] rregexs, LanguageType apply, int[] rearrangements)
    {
        this.name = name;
        this.clause = clause;
        replacements = rregexs;
        applyTo = apply;
        this.rearrangements = rearrangements;
    }

    public Word[] applyRule (Word[] arr, RuleManager rm)
    {
        // Gets the matching clause
        Word[] matches = clause.getMatching(arr, rm);
        // If no matches nothing to be done
        if (matches == null) { return arr; }
        boolean nullable = true;
        for (Word w : matches) { if (w != null) { nullable = false; break; } }
        if (nullable)
        {
            return arr;
        }

        // This will be returned
        Word[] ret = new Word[arr.length];

        int indexInArr = 0;

        // For each match
        int matchCount = 0;
        for (Word match : matches)
        {
            // Check arr[i], arr[i+1], ... arr[arr.length -1]
            for (int i = indexInArr; i < arr.length; i++)
            {
                Word val = arr[i];
                switch (applyTo)
                {
                    case ORIGINAl:
                        if (match.getOriginal().equals(val.getOriginal()))
                        {
                            indexInArr = i;
                            replacements[i].applyTo(val, applyTo);
                            ret[rearrangements[matchCount]] = val;
                            matchCount++;
                            break;
                        }
                        break;
                    case TRANSLATED:
                        if (match.getTranslated().equals(val.getTranslated()))
                        {
                            indexInArr = i;
                            replacements[i].applyTo(val, applyTo);
                            ret[rearrangements[matchCount]] = val;
                            matchCount++;
                            break;
                        }
                        break;
                }

            }
        }

        for (int i = 0; i < arr.length; i++)
        {
            if (ret[i] == null)
            {
                ret[i] = arr[i];
            }
        }

        return ret;
    }

    @Override
    public boolean valid()
    {
        return clause.valid() &&
                !name.trim().equals("") &&
                replacements.length > 0 &&
                replacements.length == rearrangements.length
                && rearrangements.length == clause.getSize();
    }
}
