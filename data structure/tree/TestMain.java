import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TestMain {
    public static void main(String[] args) {
        /*===============실습 3=========================*/
        MyTree tree = new MyTree();
        MyNode root = tree.addRoot("Computers'R Us");



        MyNode a = tree.addNode("Sales");
        MyNode b = tree.addNode("Manufacturing");
        MyNode c = tree.addNode("R&D");

        MyNode d = tree.addChild(a, "US");
        MyNode e = tree.addChild(a, "International");
        MyNode f = tree.addChild(b, "Laptops");
        MyNode g = tree.addChild(b, "Desktops");

        MyNode h = tree.addChild(e, "Europe");
        MyNode i = tree.addChild(e, "Asia");
        MyNode j = tree.addChild(e, "Canada");

        tree.printLevel(root); //실습 3 결과 출력
        System.out.println();
        /*=========================실습 4-1===========================*/
        MyTree tree2 = new MyTree();

        root = tree2.addRoot("Make Money Fast");

        a = tree2.addNode("1. Motivations");
        b = tree2.addNode("2. Methods");
        c = tree2.addNode("References");

        d = tree2.addChild(a, "1.1 Greed");
        e = tree2.addChild(a, "1.2 Avidity");

        f = tree2.addChild(b, "2.1 Stock Fraud");
        g = tree2.addChild(b, "2.2 Ponzi Scheme");
        h = tree2.addChild(b, "2.3 Bank Robbery");
        tree2.preOrder(root, 0);
        System.out.println();
        /*=========================== 실습 4-2 ====================*/
        MyTree tree3 = new MyTree();

        root = tree3.addRoot("cs16/");

        a = tree3.addNode("homeworks/");
        b = tree3.addNode("programs/");
        c = tree3.addNode("todo.txt(1K)");

        d = tree3.addChild(a, "h1c.doc(3K)");
        e = tree3.addChild(a, "h1nc.dox(2K)");

        f = tree3.addChild(b, "DDR.java(10K)");
        g = tree3.addChild(b, "Stocks.java(25K)");
        h = tree3.addChild(b, "Robot.java(20K)");

        tree3.postorder(root, new Stack<Integer>());
    }
}
