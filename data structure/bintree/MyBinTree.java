import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MyBinTree extends MyTree{

    MyBinNode root;
    MyBinTree(){
        super();
        this.root = null;
    }
    MyBinTree(Object e) {
        super(e);
        this.root = new MyBinNode(e);
        this.root.setChildren(new ArrayList<>());
    }

    public Boolean isEmpty(){
        if(super.size() == 0)
            return true;
        else
            return false;
    }
    public boolean isRoot(MyBinNode v){
        if(v.parent() == null)
            return true;
        else
            return false;
    }

    public boolean isInternal(MyBinNode v){
        if(v.degree() >= 1)
            return true;
        else
            return false;
    }
    public boolean isExternal(MyBinNode v){
        if(v.degree() == 0)
            return true;
        else
            return false;
    }

    public MyBinNode root(){
        return this.root;
    }

    public MyBinNode parent(MyBinNode v){
        return (MyBinNode) v.parent();
    }

    public MyBinNode left(MyBinNode v){
        return v.left();
    }

    public MyBinNode right(MyBinNode v){
        return v.right();
    }

    public boolean hasLeft(MyBinNode v){
        if(v.left() != null)
            return true;
        else
            return false;
    }

    public boolean hasRight(MyBinNode v){
        if(v.right() != null)
            return true;
        else
            return false;
    }

    public MyBinNode addRoot(Object e){
        this.root = new MyBinNode(e);
        return this.root;
    }

    public MyBinNode addNode(Object e){
        return new MyBinNode(e);
    }

    public MyBinNode insertLeft(MyBinNode v, Object e){
        MyBinNode tmp = new MyBinNode(e);
        tmp.setParent(v);
        v.setLeft(tmp);

        return tmp;
    }

    public MyBinNode insertRight(MyBinNode v, Object e){
        MyBinNode tmp = new MyBinNode(e);
        tmp.setParent(v);
        v.setRight(tmp);

        return tmp;
    }

    public Object replace(MyBinNode v, Object e){
        v.setElement(e);

        return e;
    }

    public MyBinNode remove(MyBinNode v) throws TwoChildrenException{
        TwoChildrenException e = new TwoChildrenException();
        if(hasLeft(v) && hasRight(v)){
            throw e;
        }
        MyBinNode parent = (MyBinNode) v.parent();

        //v가 자식이 없다면
        if(isExternal(v)){
            if(parent.left() == v){
                parent.setLeft(null);
            }else{
                parent.setRight(null);
            }
        }

        if(parent.left() == v){
            if(hasLeft(v)){
                parent.setLeft(v.left());
            }else{
                parent.setLeft(v.right());
            }
        }else{
            if(hasLeft(v)){
                parent.setRight(v.left());
            }else{
                parent.setRight(v.right());
            }
        }
        v = null;
        return v;


    }

    public void attach(MyBinNode v, MyBinNode t1, MyBinNode t2) throws NotExternalException{
            NotExternalException e = new NotExternalException();
            if(isInternal(v)){
                throw e;
            }

            v.setLeft(t1);
            v.setRight(t2);
    }

    public class TwoChildrenException extends Exception{
        Exception TwoChildrenException = new Exception("해당 노드는 두 개의 자식이 있습니다");
    }

    public class NotExternalException extends Exception{
        Exception NotExternalException = new Exception("더 이상 자식을 넣을 공간이 없습니다.");

    }

    public void InorderTraveral(MyBinNode root){
        List<Object> list = new ArrayList<>();
        Inorder(root, list);
        for(Object a : list){
            if(a instanceof String){
                System.out.print((String) a);
            }else{
                System.out.print((int)a);
            }
        }
        System.out.println();
    }
    public void Inorder(MyBinNode root, List<Object> list){
        if(isInternal(root)){
            if(hasLeft(root)) {
                list.add("(");
                Inorder(root.left(), list);
            }
            list.add(root.element());
            if(hasRight(root)) {
                Inorder(root.right(), list);
                list.add(")");
            }
        }
    }
    public static int postOrder(MyBinTree tree, MyBinNode node){
        if(tree.isExternal(node))
            return (int)node.element();
        else
        {
            int x = postOrder(tree, node.left());
            int y = postOrder(tree, node.right());

            char op = (char) node.element();
            switch (op){
                case '*': return x * y ;
                case '+': return x + y;
                case '-' : return x - y;
            }
        }
        return 0;
    }
}
