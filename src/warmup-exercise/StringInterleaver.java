
public class StringInterleaver {
    // Write a method that interleaves two strings: it should take one character
    // from the first string, then one from the second string, another from the
    // first string and so on. Once one string has no characters left it should
    // carry on with the other string. For example, interleaving the strings "anna"
    // and "patrik" should give the result "apnantarik" (or "paantnraik"). Use
    // s.length() to find the length of a string and s.charAt(i) to find the
    // character at index i.

    /**
     * This method calculates which string is longest and puts the strings in an
     * ordered string array
     * 
     * @param str1 the first string
     * @param str2 the second string
     * @return if str2 is longest, it's the first element in strs, else it's the
     *         second
     */
    public static String[] longestString(String str1, String str2) {
        if (str1.length() < str2.length()) {
            String[] strs = { str2, str1 };
            return strs;
        }
        String[] strs = { str1, str2 };
        return strs;
    }

    /**
     * This method interleaves the characters of two strings to one string
     * 
     * @param str1 the first string
     * @param str2 the second string
     * @return the new interleaved string
     */

    public static String interleave(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        String[] strs = longestString(str1, str2);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strs[0].length(); i++) {
            if (i >= strs[1].length())
                sb.append(strs[0].charAt(i));
            else {
                sb.append(str1.charAt(i));
                sb.append(str2.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean runtest() {
        if (!interleave("abc", "defghi").equals("adbecfghi"))
            return false;
        if (!interleave("patrik", "anna").equals("paantnraik"))
            return false;
        if (!interleave("anna", "patrik").equals("apnantarik"))
            return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(runtest());
    }

}