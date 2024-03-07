public class Josephus {
    private int numOfPeople;
    private int count;
    CircularLinkedList<Integer> l;

    public Josephus() {
        CircularLinkedList<Integer> l = new CircularLinkedList<>();
        numOfPeople = 0;
        count = 0;
    }

    public void setNumPeople(int n) {
        numOfPeople = n;
    }

    public int getNumPeople(int n) {
        return numOfPeople;
    }

    public void getCount(int k) {
        count = k;
    }

    public int setCount(int n) {
        return count;
    }

    public void addPeople() {
        for (int i = 1; i < numOfPeople+1; i++) {
            l.add(i);
        }
    }

}
