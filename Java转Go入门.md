Java转Go入门

1. Go和Java的简单对比

|                             | **Java**                                                     | **Go**                                                       |
| --------------------------- | ------------------------------------------------------------ | :----------------------------------------------------------- |
| **开始**                    | 任意包的psvm()方法                                           | main包的main()方法                                           |
| **导入包**                  | import导入对应包                                             | import导入对应包or import ()导入多个包                       |
| **可见性**                  | private                                                      | 首字母小写                                                   |
| public                      | 首字母大写                                                   |                                                              |
| protected                   |                                                              |                                                              |
| **变量**                    | `int a;``int a = 1;``int a = 1; bool b = false;`             | `var a int``var a int = 1 或者 a := 1``a, b := 1, false`     |
| **常量**                    | final关键字                                                  | const关键字                                                  |
| **函数/方法**               | `*public* *int**[]* function(*int* a, *int* b){``  *return* *new* *int*[]{a, b};``}` | `*func* *Function*(a, b int) (int, int){``  *return* *a, b*``}`函数关键字func形式 => 关键字 函数名 (参数) (返回值) 左大括号左大括号必须与函数定义同行 |
| **零值**                    | string => null其余大致相同                                   | string => ""其余大致相同                                     |
| **类型转换**                | `*int* a = 1;``*double* b = (*double*)a;`                    | `*var* a int = 1``*var* b float64 = float64(a)`              |
| **类型推导**                | 无                                                           | 声明变量没有指定类型时，由右值推导出                         |
| **打印类型**                | Object类中的toString()方法                                   | fmt包*Stringer接口**String()**方法*                          |
| **for**                     | `*for* (*int* i = 0; i < 10; i++) {``}`                      | `for i := 0; i < 10; i++ {``}``// 可以用for配合range关键字遍历数组``for i, v := range 数组{``  // i 为当前下标，v为值的一个副本 ``}` |
| **while**                   | `while(a < b){``}`                                           | `for a < b {``}`                                             |
| **if**                      | `int a = 0;``if(a == 0){``  sout(a); // OK ``} else{``  sout(a); // OK``}``sout(a);   // OK` | ```*if* a := 0; a == 0{``  fmt.Print(a) // OK``}*else*{``  fmt.Print(a) // OK``}``fmt.Print(a)   // ERROR`可以有初始化语句初始化语句作用域 like for循环的初始化语句 |
| **switch**                  | `int a = 0;``switch(a){``  case 0:``    sout(0);``    break;``  default:``    sout(-1);    ` | `*switch* a := 0; a {``*case* 0:{``   fmt.Print(0)``  }``*default*:``  {``   fmt.Print(-1)``  }``}`会自动在每个case后添加breakcase 后可以跟表达式，用于替代if else |
| **finally** **/** **defer** | `*try* {``  sout(1);``}*finally* {``  sout(2);``}`           | `func main() {``  defer fmt.Println("2")``  fmt.Println("1")``}`只是类似，并不相同 |
| **指针**                    | 无                                                           | *类型 // 说明这是一个指针类型&变量 // 或者这个变量的指针*指针 // 或者这个指针代表的值与 C 不同，Go 没有指针运算。 |
| **class** **/****struct**   | `*class* A {``  *int* x;``}``A a = new A();``a.x = 1;``sout(a.x);` | `type A struct {``  x int``}``a := A{1} // **结构体文法(类似Json)：a := A{x: 1}**``fmt.Println(a.x)````p := &a // 获得这个结构体指针``(*p).x和p.x作用相同`可以通过结构体指针隐式间接引用 |
| **array**                   | `*int* a[] = *new* *int*[]{1, 2, 3};`                        | `a := [3]int{1, 2, 3}`注意在中括号存在值，不存在值就不是数组，而是切片 |
| **切片**                    | 无                                                           | **切片并不存储任何数据，它只是描述了底层数组中的一段。****就像是数组的引用**`a := [3]int{1, 2, 3}  // 底层数组``var s []int = a[0: 1] // 切片 描述数组a的[0,1)``// 用 make 创建切片``b := make([]int, 0, 5) // len(b)=0, cap(b)=5``b = append(b, 1, 2, 3) // 一次性添加多个元素`可以忽略上下界，默认为[0, len]切面有长度len和容量caplen：切片包含元素的个数cap：从切片第一个元素，直到其底层数组末尾的个数nil切片的len和cap都为0且没有底层数组切片可以追加元素，但是底层数组不够容纳时**会分配一个更大的数组并将切片指向这个更大的数组** |
| **range**                   | 无                                                           | for 循环的 range 形式可遍历array或者map迭代返回两个值，第一个为元素下标，第二个为元素的副本在for循环时，可以用_忽略这个值`for _, v := range 数组{``  // 忽略下标，v为值的一个副本 ``}` |
| **map**                     | `*Map*<Integer, Integer> m;``m = *new* HashMap<>();``m.put(1, 11);  // 增``sout(m.get(1)); // 查 11``m.put(1, 111);  // 改``m.remove(1);   // 删` | `var m map[int]int``m = make(*map*[int]int)``m[1] = 11     // 增``fmt.Print(m[1])  // 查 11``m[1] = 111    // 改``delete(m, 1)   // 删`可以通过双赋值检测某个键是否存在value, ok := m[key]，如果key存在，则ok为true，否者ok为false且value为对应类型的零值 |

1. Go语法详述

类似JS的函数

函数也是值。它们可以像其它值一样传递，可以用作函数的参数或返回值。

把函数当成值传递

```
*package* main
*import* "fmt"
// 定义一个参数接收函数·
*func* *A*(fn *func*(int) int, x int) int {
  // 调用这个传进来的函数
  *return* fn(x) * 10
}
*func* main() {
  // 定义一个函数并赋值给变量
  b := *func*(x int) int {
   *return* x * 10
  }
  fmt.Println(A(b, 10)) // 1000
}
```

闭包函数

闭包是指有权访问另一个函数作用域中的变量的函数，因此就只能在一个函数的内部。

那使用闭包函数的场景是什么呢？TODO

```
*package* main
*import* "fmt"
*// 斐波纳契闭包*
*func* fibonacci() *func*() int {
  *// 斐波纳契数列的前两个值0 1*
  a, b := 0, 1
  *return* *func*() int{
   tmp := a
   a, b = b, a + b
   *return* tmp
  }
}
*func* main() {
  f, f1 := fibonacci(), fibonacci()
  *for* i := 0; i < 10; i++ {
   fmt.Printf("%v ", f()) // 0 1 1 2 3 5 8 13 21 34
  }
  *for* i := 0; i < 10; i++ {
   fmt.Printf("%v ", f1()) // 0 1 1 2 3 5 8 13 21 34
  }
}
```

类似Java class的自定义类型

Go中没有类。不过可以给type关键字定义的类型定义方法，例如type s struct、type i int，就可以给类型s和i添加方法，方法就是一类带特殊的**接收者**参数的函数，方法接收者在它自己的参数列表内，位于 func 关键字和方法名之间。

```
*package* main
*import* (
  "fmt"
)
*// 定义一个"类"*
*type* Struct *struct* {
  *// "静态变量"*
  X, Y int
}
*// 定义属于Struct"类"的方法*
*func* (s Struct) sum(z int) int {
  *return* s.X + s.Y + z
}
*// 定义一个自定义类型*
*type* I int
*// 定义属于I类型的方法*
*func* (i I) sum(z int) int {
  *return* int(i) + z
}
*func* main() {
  s := Struct{3, 4}
  i := I(10)
  fmt.Println(s.sum(5)) *// 12*
  fmt.Println(i.sum(5)) *// 15*
}
```

- 方法只是个带接收者参数的函数，跟正常的函数一样。
- 接收者的类型定义和方法声明必须在同一包内；不能为内建类型(int, float64..)声明方法

Java Object类中的toString()方法被替换为GO fmt包中的*Stringer接口，通过实现Stringer接口的String()方法即可实现跟*toString()一样的效果。

fmt包中的很多接口（例如fmt.Print、Println等）都会调用对象（类型）的*String()方法获得打印这个对象时的需要打印的值。*

```
例如调用fmt.Print方法打印时，在fmt包的print.go文件handleMethods方法中，如果类型实现了Stringer接口的话会调用其String()方法返回的值打印出来。
```