import java.util.ArrayList;

public class Map<Key extends Number, Value> {
    private final ArrayList<Value> values;
    private final ArrayList<Key> keys;

    public Map() {
        values = new ArrayList<>();
        keys = new ArrayList<>();
    }

    public void add(Key key, Value value) {
        values.add(value);
        keys.add(key);
    }

    public Value get(Key key) {
        return values.get(keys.indexOf(key));
    }

    @Override
    public String toString() {
        return values.toString();
    }
}