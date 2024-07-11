import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyPQ {
    Comparator comp;
    int capacity = 0;
    List<MyEntry> list = new ArrayList<>();

    MyPQ(Comparator comp) {
        this.comp = comp;
    }

    MyPQ(int initialCapacity, Comparator comp) {
        this.capacity = initialCapacity;
        this.comp = comp;
    }

    // Implement the following methods
    public int size() {
        return capacity;
    }

    public boolean isEmpty() {
        if(capacity == 0) {
            return true;
        }
        return false;
    }

    public MyEntry insert(Object k, Object v) {
        MyEntry entry = new MyEntry();
        entry.setKey(k);
        entry.setValue(v);
        list.add(entry);
        list.sort(comp);
        capacity++;
        return entry;
    }

    public MyEntry removeMin() {
        MyEntry minEntry = list.get(0); //가장 맨 앞에 있는 요소 반환(키값이 작은 것)
        list.remove(minEntry);
        capacity--;
        return minEntry;
    }

    public MyEntry min() {
        return list.get(0);
    }

}

