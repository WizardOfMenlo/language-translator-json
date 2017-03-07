/**
 * Created by giacomofenzi on 3/7/2017.
 */
public class Formatter
{
    public static String format(Word[] words)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++)
        {
            if (!words[i].getTranslated().equals(""))
            {
                sb.append(words[i].getTranslated());
                sb.append(" ");
            }
        }

        String res = sb.toString().trim();

        return res.substring(0,1).toUpperCase() + res.substring(1);
    }
}
