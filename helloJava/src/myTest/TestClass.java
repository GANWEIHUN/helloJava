package myTest;

import myAnnotation.CheckAnnotation;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
    }

    private void testHello() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String text = "hello";
//        text += " world";
        System.out.printf("text:%s%n", text);
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

