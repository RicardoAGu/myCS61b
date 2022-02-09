import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    /**
     * Example test that verifies correctness of the Flik.isSameNumber
     * static method.
     */
    @Test
    public void testIsSameNumber() {
        int a = 200;
        int b = 200;
        boolean result = Flik.isSameNumber(a, b);
        assertTrue("Range of integer might be the problem.", result);
    }
}
