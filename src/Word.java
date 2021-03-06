

public class Word
{
    // Default ctor
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
        // Split the line at the comma (since csv)
        String[] parts = line.split(",");

        // The translation is the second word
        String translated = parts[1];

        // gets the correct grammatical type from the stuff
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
            case "preposition":
                gt = GrammaticalType.PREPOSITION;
                break;
            case "negation":
                gt = GrammaticalType.NEGATION;
                break;
            default:
                gt = GrammaticalType.NOUN;
        }

        GenderType gm = GenderType.MALE;
        NumberType nt = NumberType.SINGULAR;

        if (gt == GrammaticalType.NOUN)
        {
            // Male is checked from the spanish
            gm = rm.getCheck("_female").check(translated) ? GenderType.FEMALE : GenderType.MALE;

            // Number is checked from the english
            nt = rm.getCheck("_plural").check(original) ? NumberType.PLURAL : NumberType.SINGULAR;
        }





        // Returns a word with those characteristics
        return new Word(original, translated, gm, nt, gt);


    }

    public GrammaticalType getType() { return type_; }
    public GenderType getGender() { return gender_; }
    public NumberType getNumber() { return number_; }

    public void setGender(GenderType gender) { gender_ = gender; }
    public void setNumber(NumberType number) { number_ = number; }

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

    @Override
    public String toString()
    {
        return original_ + " -> " + translated_;
    }
}
