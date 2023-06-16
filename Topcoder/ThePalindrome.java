public class ThePalindrome {
    public boolean discriminatePalindrome(String string) {
        boolean palindrome = true;

        for (int index = 0; index < string.length() / 2; index++) {
            if (string.charAt(index) != string.charAt(string.length() - index - 1)) {
                palindrome = false;

                break;
            }
        }

        return palindrome;
    }

    public int find(String s) {
        StringBuilder string = new StringBuilder(s);

        if (discriminatePalindrome(string.toString())) {
            return string.length();
        }

        for (int index = 0; index < s.length(); index++) {
            string.insert(string.length() - index, s.charAt(index));

            if (discriminatePalindrome(string.toString())) {
                return string.length();
            }
        }

        return string.length();
    }
}