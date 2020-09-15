package fun.neverth;

import java.util.*;

/**
 * @author NeverTh
 * @date 19:25 2020/9/15
 */
public class Xiaomi0915 {
    public void a(){

    }

    public void b(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        for (char aChar : chars) {
            if (set.add(aChar)){
                sb.append(aChar);
            }
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        while (scanner.hasNextLine()){
            String str = scanner.nextLine().trim();

            // 匹配空字符
            if (str.equals("")){
                System.out.println("true");
                continue;
            }

            if (!map.containsKey(str.charAt(0))) {
                System.out.println("false");
                continue;
            }

            LinkedList<Character> stack = new LinkedList<>();
            try {
                for (Character c : str.toCharArray()) {
                    if (map.containsKey(c)) {
                        stack.push(c);
                    }
                    else if (map.get(stack.pop()) != c) {
                        throw new Exception("");
                    }
                }
            } catch (Exception e) {
                System.out.println("false");
            }
            if (stack.size() == 0){
                System.out.println("true");
            }
        }
    }
}
