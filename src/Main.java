import com.google.gson.Gson;


public class Main
{
    public static void main(String[] args)
    {
        Dictionary d = new Dictionary(args[0]);
        RuleManager rm = new RuleManager();

        String order[] = new String[] { "makeSingular", "checkSomething"};
        rm.setOrderOfOperation(order);

        Check plural = new Check("plural", new GrammaticalType[]{GrammaticalType.ADJECTIVE}, LanguageType.ORIGINAl, "fafad");
        Check feminine = new Check("feminine", new GrammaticalType[] { GrammaticalType.NOUN}, LanguageType.TRANSLATED, "lafjdajf");
        rm.setChecks(new Check[] { plural, feminine });

        SingleRule makeSingular = new SingleRule("makeSingular", new String[] { plural.getName() }, new ReplacementRegex("a", "b"), LanguageType.TRANSLATED);
        rm.setSrules(new SingleRule[] { makeSingular });

        Clause standardClause = new Clause(new String[][]{
            new String[] { plural.getName(), feminine.getName() }
        });
        MultipleRule checkSomething = new MultipleRule("checkSomething", standardClause, new ReplacementRegex[] { new ReplacementRegex("c" , "d")}, LanguageType.ORIGINAl);
        rm.setMrules(new MultipleRule[]{ checkSomething});

        Gson gs = new Gson();
        System.out.println(gs.toJson(rm));
    }
}
