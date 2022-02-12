import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> aDQstu1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> aDQsolu1 = new ArrayDequeSolution<>();
        String errorMessage = "";
        String[] operations = {"addFirst(", "addLast(", "removeFirst()", "removeLast()"};

        for (int i = 0; i < 100; i++) {
            int probability = StdRandom.uniform(4);
            int randomNumber = StdRandom.uniform(100);
            if (probability == 0) {
                aDQstu1.addFirst(randomNumber);
                aDQsolu1.addFirst(randomNumber);
                errorMessage += operations[probability] + randomNumber + ")\n";
            } else if (probability == 1) {
                aDQstu1.addLast(randomNumber);
                aDQsolu1.addLast(randomNumber);
                errorMessage += operations[probability] + randomNumber + ")\n";
            } else if (probability == 2) {
                if (aDQstu1 == null) {
                    continue;
                } else {
                    Integer b = aDQstu1.removeFirst();
                    Integer a = aDQsolu1.removeFirst();
                    assertEquals(errorMessage + operations[probability], a, b);
                    errorMessage += operations[probability] + "\n";
                }
            } else {
                if (aDQstu1 == null) {
                    continue;
                } else {
                    Integer a = aDQsolu1.removeLast();
                    Integer b = aDQstu1.removeLast();
                    assertEquals(errorMessage + operations[probability], a, b);
                    errorMessage += operations[probability] + "\n";
                }
            }
        }
    }
}
