import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> uniqueList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
            list.add(i);
            uniqueList.add(i);
        }

        // 2.1 Uniqueness
        // returns true if every value is unique, false if there are duplicates
        System.out.println(list);
        System.out.println("This List is Unique: " + isUnique(list));
            // returns false (duplicates of every value)
        System.out.println(uniqueList);
        System.out.println("This List is Unique: " + isUnique(uniqueList));
            // returns true (no duplicate values)
        System.out.println();

        // 2.2 All Multiples
        List<Integer> multiList = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            multiList.add(i);
        }
        System.out.println(multiList);
            //prints original list
        // returns a list of values that are a multiple of the specified value
        System.out.println("List without values that are multiples of " + 5 + ": " + allMultiples(multiList, 5));
            // returns list of every value that is a multiple of 5
        System.out.println();

        // 2.3 All Strings of Size
        List<String> stringList = new ArrayList<>();
        stringList.add("cat");
        stringList.add("dog");
        stringList.add("lizard");
        stringList.add("bunny");
        stringList.add("owl");
        stringList.add("snake");
        stringList.add("rat");
        System.out.println(stringList);
            // prints original list
        // returns a list of all strings with the specified length (len)
        System.out.println( allStringsofSize(stringList, 3));
            // returns a list of strings with the length of 3 characters
        System.out.println();

        // 2.4 isPermutation
        List<Integer> oneP = new ArrayList<>();
        List<Integer> twoP = new ArrayList<>();
        twoP.add(3);
        for (int i = 1; i < 3; i++) {
            oneP.add(i);
            twoP.add(i);
        }
        oneP.add(3);
        System.out.println(oneP);
        System.out.println(twoP);
        System.out.println("These two lists are permutations: " + isPermutation(oneP,twoP));
        System.out.println();

        // 2.5 String To List of Words
        // returns a list of words by separating/converting a string
        System.out.println("\"Hello, world!\" looks like this in a list: " + stringToListOfWords("Hello, world!"));
            // returns list of separated words without punctuation
        System.out.println();

        // 2.6 Remove All Instances
        System.out.println(list);
            //prints original list
        // finds a list of values without the specified item/instance
        System.out.println("The list without an instance of 5: " + removeAllInstances(list, 5));
            //prints list without any instances of the item

        /*
        Class Notes
        int a = 8;
        int b = 6;
        a.compareTo(b);
            compareTo(): returns a negative, zero, or positive number
                if a is smaller than b, return negative (-1)
                if a = b, returns 0
                if a is greater than b, return positive (1)

         String x = "apple";
         String y = "lemon";
         x.compareTo(y);
            compareTo(): compares which one comes first in the alphabet
                if x comes before y in ABC, return negative number
                if x and y are the same, return 0
                if x comes after y in ABC, return positive number
         */
    }

    // compares every value in the lists, if they do not have any identical values (each list has different values), returns true
    public static <E> boolean isUnique(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                // if there is an identical value (duplicates), return false
                if ((i != j) && (list.get(i).equals(list.get(j)))) {
                    return false;
                }
            }
        }
        // if no duplicates were found, the list is unique
        return true;
    }

    // checks every value in list
    // if that value is divisible by x (specified value), adds the value to a new list
    // returns new list full of values divisible by x
    public static List<Integer> allMultiples(List<Integer> list, int x) {
        List<Integer> multiples = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            // if the value is divisible by the specified value (x), add to new List
            if (list.get(i) % x == 0) {
                multiples.add(list.get(i));
            }
        }
        return multiples;
    }

    // checks every value in the list
    // if the value has the same length has len (specified value), its added to a new list
    // returns new list of Strings with the specified length
    public static List<String> allStringsofSize(List<String> list, int len) {
        List<String> allStrings = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            // if string in list has the specified length (len), add to new List
            if (list.get(i).length() == len) {
                allStrings.add(list.get(i));
            }
        }
        return allStrings;
    }

    public static <E extends Comparable<E>> boolean isPermutation(List<E> listA, List<E> listB) {
        Collections.sort(listA);
        Collections.sort(listB);

        if (listA.size() != listB.size()) {
            return false;
        }
        for (int i = 0; i < listA.size(); i++) {
            for (int j = 0; j < listB.size(); j++) {
                if (listA.get(i).equals(listB.get(j))) {
                    return true;
                }
                else {
                    i = listA.size();
                    return false;
                }
            }
        }
        return false;
    }

    // longer method (not used Collections())
    public static <E> boolean isPermutationII(List<E> listA, List<E> listB) {
        if (listA.size() != listB.size()) {
            return false;
        }
        for(E item : listA) {
            int countA = 0;
            int countB = 0;
            /* reminder:
            array: .length
            String: .length()
            list/collection: .size()    */
            for (int i = 0; i < listA.size(); i++) {
                if(listA.get(i).equals(item)) {
                    countA++;
                }
            }
            for (int i = 0; i < listB.size(); i++) {
                if(listB.get(i).equals(item)) {
                    countB++;
                }
            }
            if(countA != countB) {
                return false;
            }
        }
        return true;
    }

    // splits a string of different words based on spaces and returns a list of the separated strings
    public static List<String> stringToListOfWords(String word) {
        // splits string based on whitespaces
        String[] separatedWords = word.split("\\s+");
        // creates new list
        List<String> stringToListOfWords = new ArrayList<>();

        // removes punctuation/special characters from strings and adds to new list
        for(String s : separatedWords){
            // https://www.digitalocean.com/community/tutorials/java-remove-character-string
                // explained how to use regex and the replaceAll() function
            stringToListOfWords.add(s.replaceAll("[-?.!,+@#$%^&*:;]", ""));
        }

        return stringToListOfWords;
    }

    // checks all values in list, if they match the item, item gets removed
    public static <E> List removeAllInstances(List<E> list, E item) {
         for (int i = 0; i < list.size(); i++) {
             // if Object in list matches specified object, remove from list
            while (list.get(i).equals(item)) {
                list.remove(i);
            }
        }
        return list;
    }

}
