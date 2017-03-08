/**
 * Created by gf45 on 01/03/17.
 */
public class RuleManager implements IValidable
{
    private Check[] checks;
    private SingleRule[] srules;
    private MultipleRule[] mrules;
    private String[] orderOfOperation;

    public Check getCheck(String checkName)
    {
        // Iterates and get the first one matching the checkName
        for (Check c : checks)
        {
            if (c.getName().equals(checkName)) { return c; }
        }
        throw new IllegalArgumentException("No check of name " + checkName + " found");
    }

    public Word[] applyAllRules(Word[] input)
    {
        // For each string in the order of operation array
        for (String s : orderOfOperation)
        {
            // Try to get the single rule
            SingleRule r = getSingleRuleByName(s);

            // If this isn't null
            if (r != null)
            {
                // Apply it to all words in the input
                for (int i = 0; i < input.length; i++)
                {
                    input[i] = r.apply(input[i], this);
                }
            }
            // If r is null
            else
            {
                // Get the rule m
                MultipleRule m = getMultipleRuleByName(s);
                // If this isn't null
                if (m != null)
                {
                    // Apply to the input
                    input = m.applyRule(input, this);
                }
                else
                {
                    // No rule was applied
                    throw new IllegalArgumentException("Rule named " + s + " was not found");
                }
            }
        }
        return input;
    }

    public SingleRule getSingleRuleByName(String name)
    {
        for (SingleRule s : srules)
        {
            if (s.getName().equals(name))
            {
                return s;
            }
        }
            return null;
    }

    public MultipleRule getMultipleRuleByName(String name)
    {
        for (MultipleRule m : mrules)
        {
            if (m.getName().equals(name))
            {
                return m;
            }
        }
        return null;
    }


    public void setSrules(SingleRule[] srules)
    {
        this.srules = srules;
    }

    public void setMrules(MultipleRule[] mrules)
    {
        this.mrules = mrules;
    }

    public void setChecks(Check[] checks)
    {
        this.checks = checks;
    }

    public void setOrderOfOperation(String[] orderOfOperation)
    {
        this.orderOfOperation = orderOfOperation;
    }

    @Override
    public boolean valid()
    {
        for (Check c : checks)
        {
            if (c == null && !c.valid()) return false;
        }

        for (SingleRule r : srules)
        {
            if (r == null && !r.valid()) return false;
        }

        for (MultipleRule m : mrules)
        {
            if (m == null || !m.valid())
            {
                return false;
            }
        }

        return true;
    }
}
