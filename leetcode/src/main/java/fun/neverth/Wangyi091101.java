package fun.neverth;

import java.util.*;

/**
 * @author NeverTh
 * @date 14:58 2020/9/12
 */
public class Wangyi091101 {

    public void a() {

    }

    static HashMap<String, Node> map = new HashMap<>();

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }

        static Node create(String val) {
            if (map.get(val) == null) {
                map.put(val, new Node(Integer.parseInt(val)));
            }
            return map.get(val);

        }
    }

    public static int[] x = new int[100005];

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        String s = S.nextLine();
        int l = s.length();
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, ans = 0;
        for (int i = 0; i <= 64; i++) x[i] = -1;
        x[0] = 0;
        for (int i = 0; i < l; i++) {
            char p = s.charAt(i);
            if (p == 'a') a++;
            else if (p == 'b') b++;
            else if (p == 'c') c++;
            else if (p == 'x') d++;
            else if (p == 'y') e++;
            else if (p == 'z') f++;
            a %= 2;
            b %= 2;
            c %= 2;
            d %= 2;
            e %= 2;
            f %= 2;
            int nw = 32 * a + 16 * b + 8 * c + 4 * d + 2 * e + f;
            int tmp = x[nw];
            if (tmp == -1) x[nw] = tmp;
            else ans = Math.max(ans, i - tmp + 1);
        }
        System.out.println(ans);
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
        String str = "abababab";
        char[] chars = str.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('b', 0);
        map.put('c', 0);
        map.put('x', 0);
        map.put('y', 0);
        map.put('z', 0);

        for (char aChar : chars) {
            if (map.get(aChar) != null) {
                map.put(aChar, map.get(aChar) + 1);
            }
        }

        Set<Character> characters = map.keySet();
        ArrayList<Character> arrayList = new ArrayList<>();
        ArrayList<Character> arrayList1 = new ArrayList<>();

        for (Character character : characters) {
            if (map.get(character) != 0 && map.get(character) % 2 == 0) {
                arrayList.add(character);
            } else {
                arrayList1.add(character);
            }
        }


        Stack<Character> stack = new Stack<>();
        int tmp = 0;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if (arrayList.contains(chars[i])) {
                if (!stack.contains(chars[i])) {
                    stack.push(chars[i]);
                    tmp++;
                } else {
                    while (!stack.isEmpty() && stack.peek() != chars[i]) {
                        stack.pop();
                    }
                    if (stack.pop() == chars[i]) {
                        if (tmp != 0) {
                            res = Math.max(res, ++tmp);
                        }
                    }
                }
            } else if (!arrayList1.contains(chars[i])) {
                if (stack.isEmpty()) {
                    res = Math.max(res, tmp++);
                }
            } else {
                if (!stack.isEmpty()) {
                    tmp = 0;
                }
            }
        }
        System.out.println(res);
    }

    public void aa() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] s1 = s.split(" ");
            Node node = Node.create(s1[0]);
            if (s1[1].equals("left")) {
                node.left = Node.create(s1[2]);
            } else {
                node.right = Node.create(s1[2]);
            }
        }
        Node node = map.get("1");
        re(node);
        System.out.println(res / 2);
    }

    static int res = 0;

    static public void re(Node node) {
//        if (node.left == null || node.right == null){
//            return;
//        }
//        if (node.left.left == null && node.left.right == null
//                && node.right.left == null && node.right.right == null){
//            res++;
//        }
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res++;
        }

        re(node.left);
        re(node.right);
    }
}
