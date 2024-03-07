public class Runner {
    public static void main(String[] args) {
        Josephus l = new Josephus();

        l.setNumPeople(5);
        l.addPeople();
        l.setCount(2);
    }
}
