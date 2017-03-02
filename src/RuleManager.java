/**
 * Created by gf45 on 01/03/17.
 */
public class RuleManager
{
    private Check[] checks;
    private SingleRule[] srules;
    private MultipleRule[] mrules;
    private String[] orderOfOperation;

    public Check getCheck(String checkName)
    {
        for (Check c : checks)
        {
            if (c.getName().equals(checkName)) { return c; }
        }
        throw new IllegalArgumentException("No check of name " + checkName + " found");
    }

    public Word[] applyAllRules(Word[] input)
    {
        for (String s : orderOfOperation)
        {
            SingleRule r = getSingleRuleByName(s);
            if (s != null)
            {
                for (int i = 0; i < input.length; i++)
                {
                    input[i] = r.apply(input[i], this);
                }
            }
            else
            {
                MultipleRule m = getMultipleRuleByName(s);
                if (m != null)
                {
                    input = m.applyRule(input, this);
                }
                else
                {
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
}
