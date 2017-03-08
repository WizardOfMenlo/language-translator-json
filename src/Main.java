import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main
{
    public static void main(String[] args)
    {

        // Check for the correct number of args
        if (args.length != 2)
        {
            System.out.println("Usage: java Main inputDictionary inputLanguageRules");
            return;
        }

        // Gets the path
        String dictionaryPath = args[0];
        String rulePath = args[1];

        // Initialise a reader for the JSON rules
        Reader read;
        try
        {
            read = new FileReader(rulePath);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File " + rulePath + "not found");
            return;
        }

        // Gets the dictionary (final because used in lambda)
        final Dictionary dictionary = new Dictionary(dictionaryPath);

        // RuleManager from the Json
        Gson gs = new Gson();
        RuleManager rm = gs.fromJson(read, RuleManager.class);

        // Checks for validity
        if (!rm.valid()) { System.out.println("Invalid Json file"); return; }

        // While the user input is not equal to q
        String s;
        while (!(s = getUserInput()).equals("q"))
        {
            // Create some tokens stuff
            String[] arr = Tokenizer.tokenize(s);
            // Get the words from the dictionary (only nouns will have both correct gender and number)
            Word[] words = Stream.of(arr).map(w -> dictionary.getWord(w, rm)).toArray(Word[]::new);
            // Apply all the rules
            Word[] finalRes = rm.applyAllRules(words);

            System.out.println(Formatter.format(finalRes));
        }
    }

    public static String getUserInput()
    {
        System.out.println("Enter text to Translate");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
