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
        Clause.Match[] matches = clause.getMatching(arr, rm);

        // If no matches nothing to be done
        for (Clause.Match w : matches)
        {
            if (w == null || w.word == null) { return arr; }
        }

        // This will be returned
        Word[] ret = new Word[arr.length];

        for (int i = 0; i < matches.length; i++)
        {
            Clause.Match m = matches[i];
            if (m != null && m.word != null)
            {
                Word w = arr[m.indexWord];
                int newIndex =  matches[rearrangements[i]].indexWord;
                ReplacementRegex r = replacements[i];
                ret[newIndex] = r.applyTo(w, applyTo);
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
        for (ReplacementRegex r : replacements)
        {
            if (r == null || !r.valid())
            {
                return false;
            }
        }

        return clause.valid() &&
                !name.trim().equals("") &&
                replacements.length > 0 &&
                replacements.length == rearrangements.length
                && rearrangements.length == clause.getSize();
    }
}
