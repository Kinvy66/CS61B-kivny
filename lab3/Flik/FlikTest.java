import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int i = 150;
        int j = 160;
        int k = 150;
        assertTrue(Flik.isSameNumber(i, j));
    }
}
