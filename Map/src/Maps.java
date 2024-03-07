import java.util.Arrays;

public class Map {
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

        public Map() {
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
