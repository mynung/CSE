import java.util.ArrayList;

public class MyBST extends MyBinTree {

    // 생성자
    public MyBST() {
        super();
    }

    public MyBST(Object e) {
        super(e);
    }

    private MyBinNode nextNode(MyBinNode v) {
        if (v == null)
            return null;
        if (v.hasRight()) {
            MyBinNode curr = v.right();
            while (curr.hasLeft())
                curr = curr.left();
            return curr;
        } else {
            MyBinNode parent = (MyBinNode) v.parent();
            while (parent != null && v == parent.right()) {
                v = parent;
                parent = (MyBinNode) v.parent();
            }
            return parent;
        }
    }

    public MyBinNode find(Object k) {
        MyBinNode curr = root();
        while (curr != null) {
            int cmp = ((Comparable<Object>) k).compareTo(curr.element());
            if (cmp == 0)
                return curr;
            else if (cmp < 0)
                curr = curr.left();
            else
                curr = curr.right();
        }
        return null;
    }

    public ArrayList<MyBinNode> findAll(Object k) {
        ArrayList<MyBinNode> result = new ArrayList<>();
        findAll(root(), k, result);
        return result;
    }

    private void findAll(MyBinNode v, Object k, ArrayList<MyBinNode> result) {
        if (v == null)
            return;
        int cmp = ((Comparable<Object>) k).compareTo(v.element());
        if (cmp == 0) {
            result.add(v);
            findAll(v.left(), k, result);
            findAll(v.right(), k, result);
        } else if (cmp < 0) {
            findAll(v.left(), k, result);
        } else {
            findAll(v.right(), k, result);
        }
    }

    public MyBinNode insert(Object k) {
        MyBinNode newNode = new MyBinNode(k);
        if (root() == null) {
            addRoot(k);
            return root();
        }
        MyBinNode curr = root();
        while (true) {
            int cmp = ((Comparable<Object>) k).compareTo(curr.element());
            if (cmp <= 0) {
                if (!curr.hasLeft()) {
                    curr.setLeft(newNode);
                    return newNode;
                }
                curr = curr.left();
            } else if (cmp > 0) {
                if (!curr.hasRight()) {
                    curr.setRight(newNode);
                    return newNode;
                }
                curr = curr.right();
            } else {
                return curr;
            }
        }
    }

    public MyBinNode remove(Object k) {
        MyBinNode nodeToRemove = find(k);
        if (nodeToRemove != null)
            return remove(nodeToRemove);
        return null;
    }

    public MyBinNode remove(MyBinNode v) {
        if (v.hasLeft() && v.hasRight()) {
            MyBinNode successor = nextNode(v);
            v.setElement(successor.element());
            return remove(successor);
        } else {
            MyBinNode parent = (MyBinNode) v.parent();
            MyBinNode child = v.hasLeft() ? v.left() : v.right();
            if (parent == null) {
                if (child != null) {
                    child.setParent(null);
                    root = child;
                } else {
                    root = null;
                }
            } else {
                if (v == parent.left()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
                if (child != null)
                    child.setParent(parent);
            }
            v.setParent(null);
            return v;
        }
    }



}
