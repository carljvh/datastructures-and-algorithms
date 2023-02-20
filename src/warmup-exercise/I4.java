public class I4 {
    // This class is used to check if a word is a palindrome
    private static Character[] string2CharArray(String str) {
        Character[] chars = new Character[str.length()];
        for (int i = 0; i < str.length(); i++)
            chars[i] = str.charAt(i);
        return chars;
    }

    /**
     * Removes all characters that aren't letters, and converts the remaining
     * letters to upper case in a string
     * 
     * @param str string to be formatted
     * @return a formatted string containing only uppercase letters
     */
    private static String flattenString(String str) {
        Character[] chars = string2CharArray(str);
        StringBuilder sb = new StringBuilder(chars.length);
        for (Character c : chars) {
            if (Character.isAlphabetic(c)) {
                sb.append(Character.toUpperCase(c));
            } else
                continue;
        }

        return sb.toString();
    }

    /**
     * This is I3 if you remove flattenString. Checks to see if a string is a
     * palindrome.
     * 
     * @param str string to be checked
     * @return true if string is a palindrome, else false
     */
    public static boolean isPalindrome(String str) {
        String flatStr = flattenString(str);
        StringReverser rev = new StringReverser(flatStr);
        rev.reverseString();
        return rev.getString().equals(flatStr);
    }

    public static void main(String[] args) {
        String pal = "kivik aa kiviK";
        System.out.println(isPalindrome(pal));
    }
}