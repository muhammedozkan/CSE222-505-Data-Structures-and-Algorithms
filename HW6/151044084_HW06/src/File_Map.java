import java.util.*;

public class File_Map implements Map {
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
    ArrayList<String> fnames;
    ArrayList<List<Integer>> occurances;

    public File_Map() {
        fnames = new ArrayList<String>();
        occurances = new ArrayList<List<Integer>>();
    }

    @Override
    public int size() {
        return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return fnames.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return occurances.contains(value);
    }

    @Override
    public Object get(Object key) {

        return occurances.get(fnames.indexOf(key));
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        if (fnames.contains(key)) {//if it exits,only add  the value
            occurances.get(fnames.indexOf(key)).add((Integer) value);
        } else {//add key and value
            fnames.add((String) key);
            occurances.add((List<Integer>) value);
        }
        return null;
    }

    @Override
    public String toString() {
        return "File_Map{" +
                "fnames=" + fnames +
                ", occurances=" + occurances +
                '}';
    }

    @Override
    public Object remove(Object key) {

        occurances.remove(fnames.indexOf(key));//remove arraylist
        return fnames.remove(key);//remove arraylist and return
    }

    @Override
    public void putAll(Map m) {

        fnames.addAll(m.keySet());
        occurances.addAll(m.entrySet());

    }

    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }

    @Override
    public Set keySet() {
        return Set.copyOf(fnames);
    }

    @Override
    public Collection values() {
        return occurances;
    }

    /*You do not need to implement entrySet function
     * */
    @Override
    public Set<Entry> entrySet() {

        return null;
    }
}