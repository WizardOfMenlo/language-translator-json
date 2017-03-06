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
        public Match(Word w, int iw) { word = w; indexWord = iw; }
        Word word;
        int indexWord;
    }

    // Returns null if no match found
    public Match[] getMatching(Word[] input, RuleManager rm)
    {
        // Creates an array where the return will be put
        Match[] ret = new Match[checks.length];


        int checkRow = 0;
        int currentWord = 0;
        while(checkRow < checks.length && currentWord < input.length)
        {
            Word w = input[currentWord];
            String[] currentChecks = checks[checkRow];
            if (matchesAll(w, currentChecks, rm))
            {
                ret[checkRow] = new Match(w, currentWord);
                currentWord++;
                checkRow++;
            }
            else
            {
                currentWord++;
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
