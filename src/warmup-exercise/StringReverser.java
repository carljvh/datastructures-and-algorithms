public class StringReverser {
    private char[] str;

    public StringReverser(String str) {
        this.str = str.toCharArray();
    }

    public String reverseString() {
        char[] strRev = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            strRev[i] = str[str.length - (1 + i)];

        }
        this.str = strRev;
        return getString();
    }

    public String getString() {
        return new String(this.str);
    }

    public static void main(String[] args) {
        String str1 = args[0];
        StringReverser rev = new StringReverser(str1);
        rev.reverseString();
        System.out.println(rev.getString());
    }
}