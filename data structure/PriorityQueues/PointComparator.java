import java.awt.*;
import java.util.Comparator;

public class PointComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if((o1 instanceof Entry) && (o2 instanceof Entry)){
            Point o1Key = (Point) ((Entry) o1).getKey();
            Point o2Key = (Point) ((Entry) o2).getKey();
            double distance1 = Math.sqrt(Math.pow(o1Key.x, 2) + Math.pow(o1Key.y, 2));
            double distance2 = Math.sqrt(Math.pow(o2Key.x, 2) + Math.pow(o2Key.y, 2));
            if(distance1 < distance2){
                return -1;
            }else if(distance1 > distance2){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        PointComparator comp = new PointComparator();
        MyPQ pq = new MyPQ(comp);

        pq.insert(new Point(5, 4), "a");
        pq.insert(new Point(2, 7), "b");
        pq.insert(new Point(9, 5), "c");
        pq.insert(new Point(3, 1), "d");
        pq.insert(new Point(7, 2), "e");
        pq.insert(new Point(9, 7), "f");
        pq.insert(new Point(1, 4), "g");
        pq.insert(new Point(4, 3), "h");
        pq.insert(new Point(8, 2), "i");
        pq.insert(new Point(4, 8), "j");

        System.out.println("[가까운 순서]");
        while (!pq.isEmpty()){
            Point key = (Point) pq.min().getKey();
            Object value = pq.removeMin().getValue();
            System.out.println(value+"("+(key.x)+","+(key.y)+")");
        }


        System.out.println("\n\n"+"[멀리 있는 순서]");

        PointCompoarator2 comp2 = new PointCompoarator2();
        MyPQ pq2 = new MyPQ(comp2);

        pq2.insert(new Point(5, 4), "a");
        pq2.insert(new Point(2, 7), "b");
        pq2.insert(new Point(9, 5), "c");
        pq2.insert(new Point(3, 1), "d");
        pq2.insert(new Point(7, 2), "e");
        pq2.insert(new Point(9, 7), "f");
        pq2.insert(new Point(1, 4), "g");
        pq2.insert(new Point(4, 3), "h");
        pq2.insert(new Point(8, 2), "i");
        pq2.insert(new Point(4, 8), "j");

        while (!pq2.isEmpty()){
            Point key = (Point) pq2.min().getKey();
            Object value = pq2.removeMin().getValue();
            System.out.println(value+"("+(key.x)+","+(key.y)+")");
        }
    }
}

class PointCompoarator2 implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        if((o1 instanceof Entry) && (o2 instanceof Entry)){
            Point o1Key = (Point) ((Entry) o1).getKey();
            Point o2Key = (Point) ((Entry) o2).getKey();
            double distance1 = Math.sqrt(Math.pow(o1Key.x, 2) + Math.pow(o1Key.y, 2));
            double distance2 = Math.sqrt(Math.pow(o2Key.x, 2) + Math.pow(o2Key.y, 2));
            if(distance1 < distance2){
                return 1;
            }else if(distance1 > distance2){
                return -1;
            }else{
                return 0;
            }
        }
        return 0;
    }
}