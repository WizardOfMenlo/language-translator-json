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

    // Returns null if no match found
    public Word[] getMatching(Word[] input, RuleManager rm)
    {
        Word[] ret = new Word[checks.length];
        int currentIndex = 0;
        for (int i = 0; i < ret.length; i++)
        {
            if (matchesAll(input[currentIndex], checks[i], rm))
            {
                ret[i] = input[currentIndex];
            }
            else if (currentIndex + 1 < input.length)
            {
                currentIndex++;
            }
            else
            {
                return null;
            }
        }
        return ret;
    }

    public boolean matchesAll(Word toCheck, String[] checks, RuleManager rm)
    {
        boolean matches = true;
        for (String check : checks)
        {
            matches = matches && rm.getCheck(check).check(toCheck);
        }
        return matches;
    }

    public int getSize() { return checks.length; }

    @Override
    public boolean valid()
    {
        return checks.length > 0;
    }
}
