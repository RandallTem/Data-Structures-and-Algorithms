import Tests.*;


public class Test {

    public static void main(String[] args) {
        Test_Trees ts = new Test_Trees();
        try {
            ts.testHeap();
        } catch (Exception e) {
            System.out.println("Wrong input value. Quitting...");
            System.out.println(e);
        }
    }

}
