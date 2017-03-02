

public class Word
{
    public Word(String original, String translated, GenderType gender, NumberType number, GrammaticalType type)
    {
        original_ = original;
        translated_ = translated;

        gender_ = gender;
        number_ = number;
        type_ = type;
    }

    public static Word fromCsv(String line, String original, RuleManager rm)
    {
        String[] parts = line.split(",");

        String translated = parts[1];

        GenderType gm = rm.getCheck("male").check(translated) ? GenderType.MALE : GenderType.FEMALE;
        NumberType nt = rm.getCheck("singular").check(original) ? NumberType.SINGULAR : NumberType.PLURAL;

        GrammaticalType gt;
        switch (parts[2].trim().toLowerCase())
        {
            case "adjective":
                gt = GrammaticalType.ADJECTIVE;
                break;
            case "article":
                gt = GrammaticalType.ARTICLE;
                break;
            case "noun":
                gt = GrammaticalType.NOUN;
                break;
            case "pronoun":
                gt = GrammaticalType.PRONOUN;
                break;
            case "verb":
                gt = GrammaticalType.VERB;
                break;
            case "possessive":
                gt = GrammaticalType.POSSESSIVE;
                break;
            case "conjunction":
                gt = GrammaticalType.CONJUNCTION;
                break;
            case "proposition":
                gt = GrammaticalType.PROPOSITION;
                break;
            case "negation":
                gt = GrammaticalType.NEGATION;
                break;
            default:
                gt = GrammaticalType.NOUN;
        }

        return new Word(original, translated, gm, nt, gt);


    }

    public GrammaticalType getType() { return type_; }
    public GenderType getGender() { return gender_; }
    public NumberType getNumber() { return number_; }

    private String original_;
    private String translated_;

    private GenderType gender_;
    private NumberType number_;
    private GrammaticalType type_;

    public void setOriginal(String s) { original_ = s; }
    public String getOriginal()
    {
        return original_;
    }

    public void setTranslated(String s) { translated_ = s; }
    public String getTranslated()
    {
        return translated_;
    }
}
