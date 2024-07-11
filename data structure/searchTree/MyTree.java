import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTree {
    private MyNode root;

    MyTree() {root = null;}
    MyTree(Object e){
        root = new MyNode(e);
        root.setChildren(new ArrayList<MyNode>());
    }


    public int size() {
        if(this.root == null){
            return 0;
        }
        int cnt = 1;
        Queue<MyNode> queue = new LinkedList<>();
        for(MyNode a:this.root.children()){
            queue.add(a);
            cnt += 1;
        }
        while(queue.size() != 0){
            MyNode tmp = queue.poll();
            if(tmp.children() != null){
                for(MyNode a : tmp.children()){
                    queue.add(a);
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    public MyNode root(){
        return this.root;
    }

    public ArrayList<MyNode> children(MyNode v){
        return v.children();
    }

    public boolean isExternal(MyNode v){
        if(v.degree() == 0){
            return true;
        }else{
            return false;
        }
    }

    public MyNode addRoot(Object e){
        if(this.root == null){
            MyNode newNode = new MyNode(e);
            newNode.setChildren(new ArrayList<MyNode>());
            this.root = newNode;
            return newNode;
        }else{

            MyNode tmp = this.root(); //현재 root노드 임시 저장.
            this.root = new MyNode(e);
            tmp.setParent(this.root); //새로운 root노드를 예전 root노드의 부모로 설정

            //현재의 root노드의 자식 노드 설정
            ArrayList<MyNode> child = new ArrayList<>();
            child.add(tmp);
            this.root.setChildren(child);

            return this.root;
        }
    }

    public MyNode addNode(Object e){
        if(root == null){
            return addRoot(e);
        }else{
            MyNode newNode = new MyNode(e);
            newNode.setParent(root);  //새로운 노드의 부모 노드(root 노드) 설정
            newNode.setChildren(new ArrayList<MyNode>());
            root.children().add(newNode);
            return newNode;
        }
    }

    public MyNode addChild(MyNode v, Object e){
        MyNode newNode = new MyNode(e);
        newNode.setParent(v);
        newNode.setChildren(new ArrayList<MyNode>());
        v.children().add(newNode);
        return newNode;
    }

    public MyNode addChild(MyNode v, int i, Object e){
        MyNode newNode = new MyNode(e);
        newNode.setParent(v);
        newNode.setChildren(new ArrayList<MyNode>());
        v.children().add(i, newNode);
        return newNode;
    }
    public MyNode setChild(MyNode v, int i, Object e){
        MyNode newNode = new MyNode(e);
        newNode.setParent(v);
        newNode.setChildren(new ArrayList<MyNode>());
        v.children().set(i, newNode);
        return newNode;
    }
    public MyNode removeChild(MyNode v, int i){
        return v.children().remove(i);
    }

    public void printLevel(MyNode root){
        int level = 0;
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            ArrayList<MyNode> tmp = new ArrayList<>();
            for(MyNode a:queue){
                if(a.children() != null)
                    tmp.addAll(a.children());
            }
            String str = "";
            for(int i=0;i<levelSize;i++){
                str += queue.poll().element().toString();
                if(i < levelSize-1)
                    str += ", ";
            }
            System.out.println("[Level "+level+"]");
            System.out.println(str+"\n");
            if(tmp != null){
                for(MyNode a:tmp){
                    queue.add(a);
                }
            }
            level++;
        }
        System.out.println("* Tree size = Total "+this.size()+" Nodes");
    }

    public void preOrder(MyNode root, int level){
        if(root.children() != null){
            for(int i=0;i<level;i++){
                System.out.print("\t");
            }
            System.out.println(root.element());
            int lev = ++level;
            for(MyNode a:root.children()){
                preOrder(a, lev);
            }
        }else{
            for(int i=0;i<level;i++){
                System.out.print("\t");
            }
            System.out.println(root.element());
        }
    }

    public void postorder(MyNode root , Stack<Integer> K){

        if(root.degree() >= 1){
            for(MyNode node: root.children()){
                postorder(node, K);
            }
            int num = 0;
            for(int i=0;i<root.degree();i++){
                num += K.pop();
            }
            System.out.println(root.element()+" = "+ num+"KB");
            K.add(num);

        }else{
            K.add(getNum(root.element().toString()));
        }
    }

    public int getNum(String str){
        Pattern pattern = Pattern.compile("\\((\\d+)K\\)");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            return Integer.parseInt(matcher.group(1));
        }else{
            return -1;

        }
    }
}
