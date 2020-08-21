package fun.neverth;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * todo
 *
 * @author NeverTh
 * @date 20:33 2020/8/20
 */
public class PropertiesCopyTest {

    static Field[] aFields;
    static Field[] bFields;

    static {
        Field[] aFields = A.class.getDeclaredFields();

        HashMap<String, Field> stringFieldHashMap = new HashMap<>();
        HashMap<String, Field> stringFieldHashMap1 = new HashMap<>();

        for (Field aField : aFields) {
            stringFieldHashMap.put(aField.getName(), aField);
        }



        Field[] bFields = B.class.getDeclaredFields();
    }

    @Data
    class A {
        private Integer a;
        private Float b;
        private Double c;
        private Byte d;
        private String e;
    }

    @Data
    static
    class B {
        private Integer a;
        private Float b;
        private Double c;
        private Byte d;
        private String e;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        for (Field aField : aFields) {

        }
    }
}
