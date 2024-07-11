public class TestMain {
    public static void main(String[] args) {
        MyBinTree tree = new MyBinTree("+");
        MyBinNode root = tree.root();

        MyBinNode a = new MyBinNode("*");
        MyBinNode b = new MyBinNode("*");
        MyBinNode c = new MyBinNode(2);
        MyBinNode d = new MyBinNode("-");
        MyBinNode e = new MyBinNode(3);
        MyBinNode f = new MyBinNode(2);
        MyBinNode g = new MyBinNode(3);
        MyBinNode h = new MyBinNode(1);

        //lev1
        root.setLeft(a);
        root.setRight(b);


        //lev2
        a.setLeft(c);
        a.setRight(d);
        b.setLeft(e);
        b.setRight(f);

        //lev3
        d.setLeft(g);
        d.setRight(h);

        tree.InorderTraveral(root);
        System.out.println("="+ MyBinTree.postOrder(tree, root));
    }
}
