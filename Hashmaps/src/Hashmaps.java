import jdk.incubator.foreign.MemoryLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hashmaps {
    /*
    Hashsets and Hashmaps
        - O(1) operations (add, removal, search)
        - memory intensive sometimes
        - no rhyme or reason to order
            - no indices in set
    hash tables: access an entry based on key value not location
    - retrieve entry in constant time
    - transform items key value into integer value (hash code) transformed into table index
        i.e. "Cat" = 67510
    - probability of collision: s.hashCode() % table.length() is proportional to how full tree is
        - reduce by making length of table a prime number
        - use quadratic probling(1+ 2^2+ 3^2 +...)
    - avoid using O(n) search
    - goal: uniform random distrubution of values
    - organizing hash tables
        - open addressing: someones in my spot, find the next empty one or next one
            - linear probing used to access item in hash table
                - if index calculated for items key is occuped by item with that key, found item
                - if element contains item with different key, incremembt index by one
                - keep incrementing until find key or null entry
                - pros
                    - only items of same value are examined
                    - store more elements in table than number of slots (indices)
                    - determine if item isn't present and insert at beginning or end
                    - remove an item simply delete it (no replacing)
        - chaining
     - load factor: number of filled cells / table size
        - GREATEST EFFECT ON TABLE PERFORMANCE: lower factor there is smaller chance of collisons
        - performance for search and retrieval is o(1) regardless of table size when no collisons

    accessing item
    index = s.hashCode() % table.length()
    if(table[index] == null) {
        return false;
    }
    else if(table[index] == item) {
        return true;
    }
    else {
        index++;
        while(table[index] == null || table[index] == item) {
            index++;
        }
    }

..............................................................................................,,,,,,,,,,,,,,,,,,,,,,,,,,ju..,    - add elements
    sets
    - removing elements
    - union: either in A or B or both
    - intersection: items in both A and B
    - difference: items in A not in B
    - subset: A is a subset of B if every element of A is in B
    methods
    - containsAll test subset relationship
    - addAll, retainAll, removeAll perform union,intersection,
        difference,respectively

     Map: creates pairs, ket
     d = {}
     d["favorite color"] = "blue";
     d[5] = "red";
     - set of ordered pairs knowns as key and value
     - keys must be unique, values don't
     v.get(Object key)
     v.put(K key, V value)
     isEmpty()
     int size()
     v.remove(Object key)

     */
    private static class Entry<K,V> {
        private K key;
        private V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        private int size = 0;
        public static final double MAX_LOAD = 0.75;
        private Entry<K,V>[] table;
        private int capacity = 10;
        private int numDeleted;
        private static final Entr<K,V> DELTED = new Entry(null,null);

        public Hashmaps() {
            size = 0;
            capacity = 10;
            table = new Entry[capacity];
        }

        private int size(){
            return size();
        }

        public V put(K key, V val){
            if(key == null) {
                return null;
            }
            int index = key.hashCode() % capacity;
            while(true)
            {
                // found empty spot
                if(table[index] == null){
                    table[index] = new Entry<K,V>(key,value);
                    size++;
                    return null;
                }
                //key already in spot, update value
                else if(table[index].key.equals(key)){
                    V old = table[index].value;
                    table[index].value = value;
                    return old;
                }
                // probe for new index
                else{
                    index = (index+1) % capacity;
                }
            }
        }

        public V get(K key){
            int index = key.hashCode() % capacity;
            while (true){
                if(table[index] == null) {
                    return null;
                }
                else if(table[index].key.equals(key)){
                    return table[index].value;
                }
                else{
                    index =(index+1)%capacity;
                }
            }
        }

        public V remove(K key) {
            int index = key.hashCode() % capacity;
            while (true){
                if(table[index] == null || table[index] == DELETED) {
                    return null;
                }
                else if(table[index].key.equals(key)){
                    V old = table[index].value;
                    table[index] = DELETED;
                    size--;
                    numDeleted--;
                    return old;
                }
                else{
                    index =(index+1)%capacity;
                }
            }
        }

        public String toString() {
            return Arrays.toString(table);
        }
    }
}
    public static Map<String, Integer> wordCount(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            if(!map.containsKey(word)){
                map.put(word, 1);
            }
            else {
                int timeSeen = map.get(word);
                map.put(word,timeSeen=1);
            }
        }
    }

    public static <E> boolean containDuplicates(List<E> list){
        Set<E> seen = new HashSet<>();
        //MUST USE FOR EACH LOOP TO ITERATE SET
        for(E item: list){
            if(seen.add(item)){
                return true;
            }
        }
        return false;
    }

    public String toString() {A;
    }

    public static char mostCommonChar(String text) {
        Map<Character, Integer> counts = new HashMap<>();

        for(char c: text.toCharArray()) {
            if(!counts.containsKey(c)){
                counts.put(c,1);
            }
            else {
                int prevCount = counts.get(c);
                counts.put(c, prevCount +1);
            }
        }
        int mostCount = 0;
        char mostLetter = 0;
        for(Character c: counts.keySet()){
            int count = counts.get(c);
            if(count > mostCount) {
                mostCount = count;
                mostLetter = c;
            }
        }
        return mostLetter;
         /*
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i)+0]++;
        }
        int mostCount = 0;
        char mostLetter = 0;
        for(char letter = 0; letter < 128; letter++) {
            int count = counts[letter];
            if(count > mostCount) {
                mostCount = count;
                mostLetter = letter;
            }
        }
        return mostLetter;
    } */
        public static void main(String[] args) {
            System.out.println(mostCommonChar("Helloooooooo"));
        }
    }
