package FirstUnique;

import java.util.HashMap;

public class FirstUnique
{

    public static void main(String... args)
    {
        if (!firstUnique("aabbcc").equals(""))
            System.err.println("Error");
        if (!firstUnique("").equals(""))
            System.err.println("Error");
        if (!firstUnique("a").equals("a"))
            System.err.println("Error");
        if (!firstUnique("aaa").equals(""))
            System.err.println("Error");
        if (!firstUnique("abcdabcde").equals("e"))
            System.err.println("Error");
        if (!firstUnique("cdbacd").equals("b"))
            System.err.println("Error");
        if (!firstUnique("abcd").equals("a"))
            System.err.println("Error");
        if (!firstUnique("Aabcdbcd").equals("A"))
            System.err.println("Error");
    }

    private static String firstUnique(final String s)
    {
        if (s.isBlank())
        {
            return "";
        }

        final var count = new HashMap<Character, Integer>();

        for (char c : s.toCharArray())
        {
            if (count.containsKey(c))
            {
                count.put(c, count.get(c) + 1);
            }
            else
            {
                count.put(c, 1);
            }
        }

        for (char c : s.toCharArray())
        {
            if (count.get(c) == 1)
            {
                return String.valueOf(c);
            }
        }

        return "";
    }
}
