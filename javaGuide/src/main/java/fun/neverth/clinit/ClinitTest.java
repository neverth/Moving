package fun.neverth.clinit;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/19 20:03
 */
public class ClinitTest {
    static {
        i = 0; // 给变量赋值可以正常编译通过
//        System.out.println(i); // 这句编译器会提示“非法向前引用”
    }
    static int i = 1;

    static {
        System.out.println(i);
    }

    public static void main(String[] args) {
        ClinitTest a = new ClinitTest();
    }
}
