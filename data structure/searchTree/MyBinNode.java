import java.util.ArrayList;

public class MyBinNode extends MyNode{

    MyBinNode(){
        super();
        super.setChildren(new ArrayList<>());
        super.children().add(null);
        super.children().add(null);

    }

    MyBinNode(Object e){
        super(e);
        super.setChildren(new ArrayList<>());
        super.children().add(null);
        super.children().add(null);
    }

    public MyBinNode left(){
        return (MyBinNode) super.children().get(0);

    }

    public MyBinNode right(){
        return (MyBinNode) super.children().get(1);
    }

    public void setLeft(MyBinNode v){
        if(v != null)
            v.setParent(this);
        this.children().set(0, v);




    }

    public void setRight(MyBinNode v){
        if(v != null)
            v.setParent(this);
        this.children().set(1, v);
    }

    public boolean hasLeft() {
        return this.left() != null;
    }

    public boolean hasRight() {
        return this.right() != null;
    }

    @Override
    public String toString() {
        return element().toString(); // 노드의 요소를 문자열로 반환
    }
}
