import java.util.ArrayList;

public class MyNode {
    private Object element;
    private MyNode parent;
    private ArrayList<MyNode> children;
    MyNode(){
        element = null;
        parent = null;
        children = null;
    }

    MyNode(Object e){
        element = e;
        parent = null;
        children = null;
    }


    public Object element(){
        return this.element;
    }

    public MyNode parent(){
        return this.parent;
    }

    public ArrayList<MyNode> children(){
        return this.children;
    }

    public int degree(){
        return this.children.size();
    }

    public void setElement(Object e){
        this.element = e;
    }

    public void setParent(MyNode p){
        this.parent = p;
    }

    public void setChildren(ArrayList<MyNode> c){
        this.children = c;
        for(MyNode a:c){
            a.setParent(this);
        }
    }
}
