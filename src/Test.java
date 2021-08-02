import Tests.*;


public class Test {

    public static void main(String[] args) {
        Test_Trees tt = new Test_Trees();
        try {
            tt.testTwo_FourTree();
        } catch (Exception e) {
            System.out.println("Wrong input value. Quitting...");
            System.out.println(e);
        }
    }

}
