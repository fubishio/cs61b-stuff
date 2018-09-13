public class TestNBody{
	public static void main(String[] args){
		TestNBody();
	}
	private static void checkEquals(double d1, double d2, String label) {
        if (d1 == d2) {
            System.out.println("PASS: " + label + ": " + d1 + " equal to " + d2);
        } else {
            System.out.println("FAIL: " + label + ": " + d1 + " not equal to " + d2);
        }
    }
    private static void TestNBody() {
        System.out.println("Checking NBody...");

        

        checkEquals(round(NBody.T, 2), 133.4, "setNetForce()");
        checkEquals(round(NBody.dt.yNetForce, 2), 0.0, "setNetForce()");
    }
}