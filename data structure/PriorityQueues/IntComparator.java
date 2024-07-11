import java.util.Comparator;

public class IntComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        if((o1 instanceof Entry) && (o2 instanceof Entry)) {
            Integer o1Key = (Integer) ((Entry) o1).getKey();
            Integer o2Key = (Integer) ((Entry) o2).getKey();
            if(o1Key < o2Key){
                return -1;
            }else if(o1Key > o2Key){
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        IntComparator c = new IntComparator();
        MyPQ pq = new MyPQ(c);

        pq.insert(new Integer(30), null);
        pq.insert(new Integer(10), null);
        pq.insert(new Integer(20), null);


        System.out.println((Integer)pq.removeMin().getKey());
        System.out.println((Integer)pq.removeMin().getKey());
        System.out.println((Integer)pq.removeMin().getKey());
    }
}
