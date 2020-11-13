package myTest;

import myAnnotation.CheckAnnotation;
import org.junit.Test;
import sun.misc.VM;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestClass {

    @Test
    public void myTest() {

        //hello world
        testHello();
        //大型小数
        testBigFloat();
        //获取安全随机数
        testSecureRandom();
        //位运算
        testBitCalculate();
        //泛型
        testFanXing();
        //对象的hashCode
        testHashCode();
        //栈
        testStack();
        //注解
        testAnnotation();
        //list列表转数组
        testList();
        //创建stream方式一，传入 supplier。对比list存储大量数据占用内存，stream几乎不占用内存，以为你传入的supplier是一个算法，需要用到了数据在去算
        testSupplierStream();
        //stream映射为新的stream
        testMapStream();
        //测试map的key，必须正确重写equals和hashCode
        testMapKey();
        //测试Integer缓存大小
        testIntegerCache();
        //测试单列
        testSingleton();
        //适配器
        testAdapter();
        //组合
        testComposite();
        //装饰器
        testDecorator();
    }

    private void testDecorator() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        //原理：持有核心类/接口，在核心类上面增加附加功能
        TextLabel textLabel = new SpanLabel();
        textLabel.setText("你好");
        //文本加粗装饰
        LabelDecorator nodeDecorator = new BoldDecorator(textLabel);
        System.out.println(textLabel.getText());
        System.out.println(nodeDecorator.getText());
    }

    private void testComposite() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        //原理：功能拆分一个个类，然后组合实现一个大的功能. 类似一棵树部分-整体的层次结构
        ElementNode root = new ElementNode("school");
        root.add(new ElementNode("1班").add(new TextNode("张三")).add(new TextNode("王五")));
        root.add(new ElementNode("2班").add(new TextNode("jack")).add(new TextNode("tomato")).add(new CommentNode("注释")).add(new TextNode("窃格瓦拉")));
        System.out.println(root.toXml());
    }

    private void testAdapter() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        //原理：接口B持有接口A，B里面实现A的接口。A和B都是抽象接口
        Calculator calculator = new Calculator(100);
        //Thread thread = new Thread(calculator);//编译报错
        RunnableAdapter runnableAdapter = new RunnableAdapter(calculator);
        Thread thread = new Thread(runnableAdapter);//Runnable适配器
        thread.start();
    }

    private void testSingleton() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println(Singleton.class);
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }

    private void testIntegerCache() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        //启动参数 -Djava.lang.Integer.IntegerCache.high=256
        String high = VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        System.out.println(String.format("IntegerCache.high：%s", high));
    }

    private void testMapKey() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Integer> integerMap = new HashMap<>();
        String key1 = "a";
        integerMap.put(key1, 123);
        String key2 = new String("a");
        //key1和key2不是同一个对象，但是key2依然能取到key1的value。那是因为map里面是通过key的hashCode确定value索引的。
        System.out.println(String.format("key1=key2比较结果：%s", key1 == key2));
        int value = integerMap.get(key2);
        System.out.println(String.format("value:%s", value));
        System.out.println(String.format("key1.equals(key2)比较结果：%s", key1.equals(key2)));
        System.out.println(String.format("key1HashCode:%s key2HashCode:%s", key1.hashCode(), key2.hashCode()));
    }

    private void testMapStream() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<Integer> integerStream1 = integerStream.map(n -> n * n);
        integerStream1.forEach(System.out::println);
    }

    private <R> R abc(Integer integer) {
        return null;
    }

    private void testSupplierStream() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Stream<Integer> integerStream = Stream.generate(new NumberSupplier());
        integerStream.limit(5).forEach(System.out::println);
    }

    private void testList() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(123);
        list.add(29);
        list.add(8);
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println(String.format("array:%s", array.length));
        Integer[] array2 = new Integer[list.size()];
        list.toArray(array2);
        System.out.println(String.format("array2:%s", array2.length));
    }

    private void testAnnotation() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Student student = new Student();
        student.Gender = "未知生物";
        student.Name = "";
        try {
            boolean result = CheckAnnotation.check(student);
            System.out.println(String.format("检查结果%s", result));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void testStack() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Deque<String> stack2 = new LinkedList<>();
        stack2.push("hello");
        stack2.push("haha");
        stack2.push("world");
        int size = stack2.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack2.pop());
        }
    }

    private void testHashCode() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String a = String.valueOf(0);
        String b = String.valueOf(1);
        String c = "abc黑";
        int ha = c.hashCode();
        String d = "0";
        int hb = d.hashCode();
        TestClass testClass = new TestClass();
        ha = testClass.hashCode();
        TestClass testClass1 = new TestClass();
        hb = testClass1.hashCode();
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        int a1 = 16 & 15;
        int a2 = 15 & 15;
        int a3 = 1 & 15;
        int a4 = 0 & 15;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private void testFanXing() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        //泛型类
        NumClass<Integer> intCls = new NumClass<>();
        intCls.setName(1);
        System.out.println(intCls.getName());
        //？ extends通配符修饰方法参数时
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        readOnlyNumber(list);


    }

    private void readOnlyNumber(List<? extends Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            Integer n = list.get(i);
            //？ extends通配符修饰方法参数时，只能只读方法，不能修改list
            //list.set(0,10);
            System.out.println(n);
        }
    }

    private void testBitCalculate() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        int a = 1 & 0;
        int b = 2 & 2;
        int c = 4 & 4;
        int c1 = 1 & 4;
        int d = 1 | 4;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(c1);
        System.out.println(d);
    }

    private void testSecureRandom() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }

    private void testBigFloat() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        BigDecimal bigDecimal = new BigDecimal(10);
        BigDecimal bigDecimal2 = new BigDecimal(3);
        BigDecimal div = bigDecimal.divide(bigDecimal2, 10, RoundingMode.HALF_UP);
        System.out.println(String.format("div:%s", div));
        //两个BigDecimal比较不能用equals，必须用compareTo
        bigDecimal = new BigDecimal("1.2");
        bigDecimal2 = new BigDecimal("1.20");
        System.out.println(String.format("equals结果：%s", bigDecimal.equals(bigDecimal2)));
        System.out.println(String.format("compareTo结果：%s", bigDecimal.compareTo(bigDecimal2)));
    }

    private void testHello() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String text = "hello";
//        text += " world";
        System.out.printf("text:%s%n", text);
    }

    class NumberSupplier implements Supplier<Integer> {

        private int number;

        @Override
        public Integer get() {
            return ++number;
        }
    }

    private class NumClass<I extends Number> {

        private I name;

        public I getName() {
            return name;
        }

        public void setName(I name) {
            this.name = name;
        }
    }

}

