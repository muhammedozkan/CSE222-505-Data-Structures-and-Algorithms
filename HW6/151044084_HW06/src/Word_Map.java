import java.util.*;

public class Word_Map implements Map, Iterable {

    final static int INITCAP = 10;  //initial capacity
    private int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private int size;
    private Node table[];
    private Set<String> wordID;

    public Set<String> getWordID() {
        return wordID;
    }

    public Word_Map() {
        this.table = new Node[INITCAP];
        this.size = 0;
        wordID = new LinkedHashSet<String>();
    }

    public Word_Map(int size) {
        this.table = new Node[size];
        this.CURRCAP = size;
        this.size = 0;
        wordID = new LinkedHashSet<String>();
    }

    @Override
    public Iterator iterator() {
        return new MapIterator(wordID, table);
    }

    static class Node {
        // complete this class according to the given structure in homework definition

        public String wordName;

        public File_Map file;

        public Node(String wordName, File_Map file) {
            this.wordName = wordName;
            this.file = file;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "wordName='" + wordName + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    public Node[] getTable() {
        return table;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static int hash(String key) {

        int code = key.hashCode();
        if (code < 0)
            return code * -1;
        else
            return code;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        int i;

        for (i = hash((String) key) % CURRCAP; table[i] != null; i++) {
            if (table[i].wordName.equals(key)) {
                return true;
            }
            if (i == CURRCAP - 1)
                i = -1;
        }
        return false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        int i;

        for (i = 0; i < CURRCAP; i++) {
            if (table[i].file.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {

        int i;

        for (i = hash((String) key) % CURRCAP; table[i] != null; i++) {
            if (table[i].wordName.equals(key)) {
                return table[i].file;
            }
        }

        return null;
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {

        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        // double table size if 75% full
        if (size >= LOADFACT * CURRCAP)
            resize(CURRCAP * 2);

        int i;
        boolean flag = true;
        for (i = hash((String) key) % CURRCAP; flag && table[i] != null; i++) {
            if (table[i].wordName.equals(key)) {

                flag = false;
            }
            if (i == CURRCAP - 1)
                i = -1;
        }
        if (flag) {
            Node temp = new Node((String) key, (File_Map) value);
            table[i] = temp;
            wordID.add(temp.wordName);//add keyset
        }

        size++;

        return key;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        Word_Map temp = new Word_Map(capacity);
        for (int i = 0; i < CURRCAP; i++) {
            if (table[i] != null) {
                temp.put(table[i].wordName, table[i].file);
            }
        }
        table = temp.getTable();
        CURRCAP = capacity;
    }

    @Override
    public void putAll(Map m) {
        for (Object a : m.values()) {
            put(m.keySet(), a);
        }
    }

    @Override
    public void clear() {
        this.table = new Node[INITCAP];
        this.CURRCAP = INITCAP;
        this.size = 0;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        return wordID;
    }


    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Set<String> fileValue = new LinkedHashSet<String>();

        for (String a : wordID) {//use keyset structure
            fileValue.add((String) get(a));
        }

        return fileValue;
    }

    @Override
    /*You do not need to implement remove function
     * */
    public Object remove(Object key) {
        return null;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }

    static class MapIterator implements Iterator {

        private String[] current;
        private Node word[];
        private int pointer;


        public MapIterator(Set<String> keySet, Node[] table) {
            current = new String[keySet.size()];
            int i = 0;
            for (String str : keySet) {
                current[i] = str;
                i++;
            }
            word = table;
            pointer = 0;
        }

        public boolean hasNext() {
            return pointer < current.length;
        }

        public Node next() {

            int i;

            for (i = hash((String) current[pointer]) % word.length; word[i] != null; i++) {
                if (word[i].wordName.equals(current[pointer])) {
                    pointer++;
                    return word[i];
                }
            }
            return null;
        }

        /**
         * Implement if needed
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
