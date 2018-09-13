import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        /*tester = new StaffCalculator(); // Comment me out to test your Calculator*/
        tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    @Test
    public void testAdd() {
        
        int a = tester.add(5, -7);
        int b = tester.add(4, -2);
        int c = tester.add(3, 4);
        int d = tester.add(0, 0);
        int e = tester.add(100, 1000);
        assertEquals(-2, a);
        assertEquals(2, b);
        assertEquals(7, c);
        assertEquals(0, d);
        assertEquals(1100, e);
    }


    @Test
    public void testMultiply() {
        int a = tester.multiply(6, 7);
        int b = tester.multiply(0, 0);
        int c = tester.multiply(-1 , 3);
        int d = tester.multiply(2, 100);
        int e = tester.multiply(0, 6);
        assertEquals(42, a);
        assertEquals(0, b);
        assertEquals(-3, c);
        assertEquals(200, d);
        assertEquals(0, e);
    }
    
    @Test
    public void testSaveequation() {
        assertEquals(null, tester.a);
        tester.saveEquation("1 + 1", 2);
        assertEquals(2, tester.a.result);
        assertEquals("1 + 1", tester.a.equation);

    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}
