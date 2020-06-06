package fun.neverth;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/5 23:30
 */
public class Part2 {

    /**
     * 567. 字符串的排列 ①
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;

        while (right < s2.length()) {

            char c1 = s2.charAt(right++);

            map.put(c1, map.getOrDefault(c1, 0) - 1);

            while (left < right && map.get(c1) < 0) {

                char c2 = s2.charAt(left++);

                map.put(c2, map.getOrDefault(c2, 0) + 1);

                if(right - left == s1.length()){
                    return true;
                }
            }
        }

        return right - left == s1.length();
    }

    /**
     * 567. 字符串的排列 ②
     */
    public boolean checkInclusion1(String s1, String s2) {
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        for (int i = 0; i < s1.length(); i++){
            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        while(right < s2.length()){
            char c1 = s2.charAt(right++);

            if(needs.containsKey(c1)){
                windows.put(c1, windows.getOrDefault(c1, 0) + 1);

                if(windows.get(c1).compareTo(needs.get(c1)) == 0){
                    count++;
                }
            }

            while(right - left >= s1.length()){

                if(count == needs.size()){
                    return true;
                }

                char c2 = s2.charAt(left++);

                if(needs.containsKey(c2)){
                    if(windows.get(c2).compareTo(needs.get(c2)) == 0){
                        count--;
                    }
                    windows.put(c2, windows.getOrDefault(c2, 0) - 1);

                }
            }
        }
        return false;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     */
    public List<Integer> findAnagrams(String s, String p) {

        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < p.length(); i++){
            needs.put(p.charAt(i), needs.getOrDefault(p.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        while(right < s.length()){

            char c1 = s.charAt(right++);

            if(needs.containsKey(c1)){

                windows.put(c1, windows.getOrDefault(c1, 0) + 1);
                if(needs.get(c1).compareTo(windows.get(c1)) == 0){
                    count++;
                }
            }

            while(count == needs.size()){

                if(right - left == p.length()
//                        && !s.substring(left, right).equals(p) 字母异位词指字母相同，但排列不同的字符串 ？？？
                ){
                    res.add(left);
                }
                char c2 = s.charAt(left++);

                if(needs.containsKey(c2)){
                    if(needs.get(c2).compareTo(windows.get(c2)) == 0){
                        count--;
                    }
                    windows.put(c2, windows.getOrDefault(c2, 0) - 1);
                }
            }
        }

        return res;
    }

    /**
     * 3. 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> windows = new HashMap<>();

        int left = 0, right = 0;
        int maxlen = 0;

        while(right < s.length()){

            char c1 = s.charAt(right++);

            windows.put(c1, windows.getOrDefault(c1, 0) + 1);

            while(windows.get(c1) > 1){

                char c2 = s.charAt(left++);

                windows.put(c2, windows.get(c2) - 1);
            }

            maxlen = Math.max(maxlen, right - left);

        }
        return maxlen;
    }


    /**
     * 146. LRU缓存机制
     */
    static class LRUCache {

        class Node{

            private int key, val;

            private Node prev, next;

            public Node(int k , int v){
                this.key = k;
                this.val = v;
            }
        }

        class DoubleList{

            private Node head, tail;

            private int size;

            public DoubleList() {
                this.head = new Node(0, 0);
                this.tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
            }

            public void addFirst(Node a){
                a.next = head.next;
                a.prev = head;
                a.next.prev = a;
                head.next = a;
                size++;
            }

            public void remove(Node a){
                a.prev.next = a.next;
                a.next.prev = a.prev;
                size--;
            }

            public Node removeLast(){

                if (tail.prev == head){
                    return null;
                }

                Node realTail = tail.prev;

                remove(realTail);

                return realTail;
            }

        }

        private Map<Integer, Node> map;

        private DoubleList doubleList;

        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            doubleList = new DoubleList();

        }

        public int get(int key) {

            if (!map.containsKey(key)){
                return -1;
            }

            int val = map.get(key).val;

            put(key, val);

            return val;
        }

        public void put(int key, int value) {

            Node node = new Node(key, value);

            if (map.containsKey(key)){
                doubleList.remove(map.get(key));

            } else{
                if(capacity == doubleList.size){

                    Node realTail = doubleList.removeLast();
                    map.remove(realTail.key);
                }
            }
            doubleList.addFirst(node);
            map.put(key, node);
        }
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        int[] a = {1, 3, 4, 2, 2};
        String[] b = {"0000"};

        Common.TreeNode n1 = new Common.TreeNode(3);
        Common.TreeNode n2 = new Common.TreeNode(9);
        Common.TreeNode n3 = new Common.TreeNode(20);
        Common.TreeNode n4 = new Common.TreeNode(15);
        Common.TreeNode n5 = new Common.TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.println(part2.lengthOfLongestSubstring("aaaabbbb"));

//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1, 1);
//        System.out.println(lruCache.get(1));

    }
}
