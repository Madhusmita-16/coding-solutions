import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomizedSet {

    private final ArrayList<Integer> list;
    private final Map<Integer, Integer> map;
    private final Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {

        if (map.containsKey(val)) {
            return false;
        }

        // Store value and its index
        map.put(val, list.size());
        list.add(val);

        return true;
    }

    public boolean remove(int val) {

        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);

        // Get the last element
        int lastValue = list.get(list.size() - 1);

        // Move last element into the removed element's position
        list.set(index, lastValue);
        map.put(lastValue, index);

        // Remove last position
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {

        int index = random.nextInt(list.size());

        return list.get(index);
    }
}