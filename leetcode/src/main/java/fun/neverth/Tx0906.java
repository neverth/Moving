package fun.neverth;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 19:50 2020/9/6
 */
public class Tx0906 {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = Integer.parseInt(scanner.nextLine());
        int[] a1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n2 = Integer.parseInt(scanner.nextLine());
        int[] a2 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Node aa1 = new Node(-1);
        Node t1 = aa1;
        for (int i : a1) {
            t1.next = new Node(i);
            t1 = t1.next;
        }

        Node aa2 = new Node(-1);
        Node t2 = aa2;
        for (int i : a2) {
            t2.next = new Node(i);
            t2 = t2.next;
        }
        t1 = aa1.next;
        t2 = aa2.next;

        Node res = new Node(-1);
        Node rest = res;
        while(t1 != null){
            if(t1.val == t2.val){
                rest.next = new Node(t1.val);
                rest = rest.next;

                t2 = t2.next;
                t1 = t1.next;

            }else if (t1.val < t2.val){
                t2 = t2.next;

            }else{
                t1 = t1.next;
            }
        }
        res = res.next;
        while(res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }

    }
}
