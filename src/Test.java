import Tests.*;


public class Test {

    public static void main(String[] args) {
        Test_LinkedLists ll = new Test_LinkedLists();
        try {
            ll.testSinglyLinkedList();
        } catch (Exception e) {
            System.out.println("Wrong input value. Quitting...");
            System.out.println(e);
        }

    }

}
