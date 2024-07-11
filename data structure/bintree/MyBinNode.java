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
        v.setParent(this);
        this.children().add(0, v);



    }

    public void setRight(MyBinNode v){
        v.setParent(this);
        this.children().add(1, v);
    }
}
