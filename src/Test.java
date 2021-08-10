import Tests.*;


public class Test {

    public static void main(String[] args) {
        Test_Graphs ts = new Test_Graphs();
        try {
            ts.testWeightedGraph();
        } catch (Exception e) {
            System.out.println("Wrong input value. Quitting...");
            System.out.println(e);
        }
    }

}
