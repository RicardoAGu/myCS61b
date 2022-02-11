public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque myWord = new LinkedListDeque();
        char a = word.charAt(0);
        for (int i = 0; i < word.length(); i++) {
            a = word.charAt(word.length() - 1 - i);
            myWord.addFirst(a);
        }
        return myWord;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            if (word.charAt(0) == word.charAt(word.length() - 1)) {
                if (word.length() == 2) {
                    return true;
                } else {
                    word = word.substring(1, word.length() - 1);
                    return isPalindrome(word);
                }
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
                if (word.length() == 2) {
                    return true;
                } else {
                    word = word.substring(1, word.length() - 2);
                    return isPalindrome(word, cc);
                }
            } else {
                return false;
            }
        }
    }
}
