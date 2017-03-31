package derek.codewar;

/**
 * There is a queue for the self-checkout tills at the supermarket. Your task is write a function to calculate the total time required for all the customers to check out!
 * <p>
 * The function has two input variables:
 * <p>
 * customers: an array (list in python) of positive integers representing the queue. Each integer represents a customer, and its value is the amount of time they require to check out.
 * n: a positive integer, the number of checkout tills.
 * <p>
 * The function should return an integer, the total time required.
 * <p>
 * EDIT: A lot of people have been confused in the comments. To try to prevent any more confusion:
 * <p>
 * There is only ONE queue, and
 * The order of the queue NEVER changes, and
 * Assume that the front person in the queue (i.e. the first element in the array/list) proceeds to a till as soon as it becomes free.
 * The diagram on the wiki page I linked to at the bottom of the description may be useful.
 * <p>
 * So, for example:
 * <p>
 * queueTime([5,3,4], 1)
 * // should return 12
 * // because when n=1, the total time is just the sum of the times
 * <p>
 * queueTime([10,2,3,3], 2)
 * // should return 10
 * // because here n=2 and the 2nd, 3rd, and 4th people in the
 * // queue finish before the 1st person has finished.
 * <p>
 * queueTime([2,3,10], 2)
 * // should return 12
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SupermarketQueue {

    public static int solveSuperMarketQueue(int[] customers, int n) {
        List<Integer> tills = new ArrayList<>();
        if (customers.length == 0) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            tills.add(i, 0);
        }

        for (Integer customer : customers) {
            Collections.sort(tills);
            tills.set(0, tills.get(0) + customer);
        }
        Collections.sort(tills);
        return tills.get(tills.size() - 1);
    }

    @Test
    public void testNormalCondition() {
        assertEquals(9, SupermarketQueue.solveSuperMarketQueue(new int[]{2, 2, 3, 3, 4, 4}, 2));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, SupermarketQueue.solveSuperMarketQueue(new int[]{}, 1));
    }

    @Test
    public void testSingleTillManyCustomers() {
        assertEquals(7, SupermarketQueue.solveSuperMarketQueue(new int[]{2, 4, 3, 3, 7, 3}, 5));
    }

}
