/**
 * Created by gf45 on 02/03/17.
 */
public class Clause implements IValidable
{
    public Clause(String[][] checks)
    {
        this.checks = checks;
    }

    // Of the form:
    //"clause" : { "checks" : [["adj", "plural" ], ["noun", "plural" ]}
    public String[][] checks;

    class Match
    {
        public Match(Word w, int i) { word = w; index = i;}
        Word word;
        int index;
    }

    // Returns null if no match found
    public Match[] getMatching(Word[] input, RuleManager rm)
    {
        // Creates an array where the return will be put
        Match[] ret = new Match[checks.length];

        // Which word are we checking
        int currentIndex = 0;

        // For each i in the return array
        for (int i = 0; i < ret.length; i++)
        {
            // For each word after the index
            for (int j = currentIndex; j < input.length; j++)
            {
                // If it matches with the i-th rule
                if (matchesAll(input[j], checks[i], rm))
                {
                    ret[i] = new Match(input[j], j-1);
                    currentIndex = j;
                }
            }
        }
        return ret;
    }

    // Checks if a word matches the list of checks
    public boolean matchesAll(Word toCheck, String[] checks, RuleManager rm)
    {
        for (String check : checks)
        {
            // If it doesn't match return false
            if (!rm.getCheck(check).check(toCheck))
            {
                return false;
            }
        }
        return true;
    }

    public int getSize() { return checks.length; }

    @Override
    public boolean valid()
    {
        return checks.length > 0;
    }
}
