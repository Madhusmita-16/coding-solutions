import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class RandomizedCollection {

    private final ArrayList<Integer> list;
    private final Map<Integer, Set<Integer>> map;
    private final Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {

        boolean isNew = !map.containsKey(val);

        // Add value at the end
        list.add(val);

        // Store its index
        map.computeIfAbsent(val, k -> new HashSet<>())
           .add(list.size() - 1);

        return isNew;
    }

    public boolean remove(int val) {

        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        // Get any index of val
        Set<Integer> indices = map.get(val);
        int index = indices.iterator().next();

        // Last element in the list
        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        // Remove the selected index from val's set
        indices.remove(index);

        // Move last element to the removed position
        if (index != lastIndex) {

            list.set(index, lastValue);

            // Update lastValue's indices
            Set<Integer> lastIndices = map.get(lastValue);
            lastIndices.remove(lastIndex);
            lastIndices.add(index);
        }

        // Remove the last element
        list.remove(lastIndex);

        // Remove empty set from map
        if (indices.isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {

        int index = random.nextInt(list.size());

        return list.get(index);
    }
}