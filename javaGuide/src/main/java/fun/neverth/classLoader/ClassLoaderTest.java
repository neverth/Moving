package fun.neverth.classLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器实现类的加载动作。
 * 类加载器和这个类本身一同确立这个类的唯一性，每个类加载器都有独立的类命名空间。在同一个类加载器加载的情况下才会有两个类相等。
 * 相等包括类的Class对象的equals()方法、isAssignableFrom()方法、isInstance()、instanceof关键字。
 *
 * @author NeverTh
 * @date 2020/6/19 20:43
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {

                    String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";

                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];

                    is.read(b);

                    return defineClass(name, b, 0, b.length);

                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object object = loader.loadClass("fun.neverth.classLoader.ClassLoaderTest").newInstance();

        Class a = object.getClass();
        System.out.println(object.getClass());
        System.out.println(object instanceof fun.neverth.classLoader.ClassLoaderTest);

    }
}
