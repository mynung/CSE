public class TestMain {
    public static void main(String[] args) {
        MyBST bst = new MyBST();
        bst.insert(new Integer(6));
        bst.insert(new Integer(2));
        bst.insert(new Integer(9));
        bst.insert(new Integer(1));
        bst.insert(new Integer(4));
        bst.insert(new Integer(8));
        bst.insert(new Integer(9));


        //2번 문제
        System.out.println(bst.find(new Integer(8)));
        System.out.println(bst.find(new Integer(3)));
        System.out.println(bst.insert(new Integer(3)));
        System.out.println(bst.insert(new Integer(7)));
        System.out.println(bst.insert(new Integer(9)));
        System.out.println(bst.remove(new Integer(1)));
        System.out.println(bst.remove(new Integer(4)));
        System.out.println(bst.remove(new Integer(6)));
        System.out.println(bst.find(new Integer(3)));
        System.out.println(bst.find(new Integer(6)));
        System.out.println(bst.findAll(new Integer(9)));


        //3번 문제
        bst.InorderTraveral(bst.root());


    }
}
