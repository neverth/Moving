package fun.neverth;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 19:00 2020/9/13
 */
public class Didi0913 {
    public void a() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String string = scanner.nextLine();
//        int n = 2;
//        String string = "gogogoout";
        char[] chars = string.toCharArray();
        int left = 0, right = string.length() >= n ? n - 1 : string.length() - 1;
        while (true) {
            int a = left, b = right;
            while (a < b) {
                char tmp = chars[a];
                chars[a] = chars[b];
                chars[b] = tmp;
                a++;
                b--;
            }
            left += n;
            if (right + n < string.length()) {
                right += n;
            } else {
                right = string.length() - 1;
            }

            if (left >= string.length()) {
                break;
            }
        }
        System.out.println(chars);
    }

    static class Node{
        public int val;
        public HashMap<Node, Integer> next = new HashMap<>();
        public Node(int val) {
            this.val = val;
        }
        static Node create(int val){
            if (map.get(val) == null){
                map.put(val, new Node(val));
            }
            return map.get(val);
        }
    }
    static HashMap<Integer, Node> map;
    static ArrayList<Node> visited;
    static int k;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            k = scanner.nextInt();
            int[][] arr = new int[m][3];
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < 3; l++) {
                    arr[j][l] = scanner.nextInt();
                }
            }
            int bb = 0;
            for (int i1 = 0; i1 < arr.length; i1++) {
                if (arr[i1][2] > k){
                    bb++;
                }
            }
            int[][] arr1 = new int[n - bb][3];

            int aaaa = 0;
            for (int j = 0; j < m; j++) {
                if (arr[j][2] > k){
                    continue;
                }
                for (int l = 0; l < 3; l++) {
                    arr1[aaaa][l] = arr[j][l];
                }
                aaaa++;
            }
            arr = arr1;
            if (arr.length == 0){
                System.out.println("No");
            }
            map = new HashMap<>();
            visited = new ArrayList<>();
            for (int[] value : arr) {
                Node node1 = Node.create(value[0]);
                Node node2 = Node.create(value[1]);
                node1.next.put(node2, value[2]);
            }
            dfs(map.get(1));
            if (visited.size() == n){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }
    static public void dfs(Node node){
        if (visited.contains(node)){
            return;
        }else{
            visited.add(node);
        }
        if (node.next.size() == 0){
            return;
        }
        for (Node node1 : node.next.keySet()) {
            dfs(node1);
        }
    }
}
