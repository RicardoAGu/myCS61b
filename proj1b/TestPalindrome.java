import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void isPalindrome() {
        assertTrue(palindrome.isPalindrome("qaq"));
        assertFalse(palindrome.isPalindrome("soss"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void isPalindromeOffByOne() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("paq", cc));
        assertTrue(palindrome.isPalindrome("qap", cc));
        assertTrue(palindrome.isPalindrome("dfge", cc));
        assertFalse(palindrome.isPalindrome("amiaoa", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("", cc));
    }

    @Test
    public void isPalindromeOffByN() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("aaf", cc));
        assertTrue(palindrome.isPalindrome("faa", cc));
        assertTrue(palindrome.isPalindrome("djoi", cc));
        assertFalse(palindrome.isPalindrome("adjoia", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("", cc));
    }
}
