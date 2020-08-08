package fun.neverth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Part5 {
    private static HashMap<Integer, Boolean> dic = new HashMap<>();

    public static boolean funa(int n) {
        ArrayList<Integer> integers = new ArrayList<>();
        while (n != 0) {
            integers.add(n % 10);
            n = n / 10;
        }
        if (integers.size() == 1) {
            return integers.get(0) == 7;
        }

        int size = integers.size();
        int i = size - 1;
        while (size > 0) {

            integers.add(Math.abs(integers.remove(i) - integers.get(i - 1)));
            i--;
            if (i == 0) {
                integers.remove(0);
                size = integers.size();
                i = size - 1;

                if (size == 1) {
                    return integers.get(0) == 7;
                }
            }
        }
        return false;
    }

    public static class ThreadPrinter implements Runnable {

        private String name;
        private Object prev;
        private Object self;

        public ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.print(name);
                        count--;
                        self.notifyAll();
                    }
                    try {
                        if (count == 0) {

                            prev.notifyAll();
                        } else {

                            prev.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 牛客网 序列交换 网易 https://www.nowcoder.com/profile/730427842/codeBookDetail?submissionId=84132355
     */
    public void printAbc() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int count = 0;
        for (int el : array) {
            if (el % 2 == 0) {
                count++;
            }
        }

        if (count != 0 && count != n) {
            Arrays.sort(array);
        }

        for (int el : array) {
            System.out.print(el + " ");
        }
    }

    public void insertSort(int[] arr) {

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            int temp = arr[i];

            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            if (j != i) {
                arr[j] = temp;
            }

        }

    }

    public static void main(String[] args) {
        ArrayList<String> l1 = new ArrayList<String>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        l1.add("1");
        l2.add(1);
        System.out.println(l1.get(0).getClass());
        System.out.println(l2.get(0).getClass());
        System.out.println(l1.getClass() == l2.getClass());
    }
}
