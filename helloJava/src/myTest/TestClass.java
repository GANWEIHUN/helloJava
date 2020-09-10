package myTest;

import myAnnotation.CheckAnnotation;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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
    }

    private void testAnnotation() {
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
        NumClass<Integer> intCls = new NumClass<>();
        intCls.setName(1);
        System.out.println(intCls.getName());
    }

    private void testBitCalculate() {
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
        BigDecimal bigDecimal = new BigDecimal(10);
        BigDecimal bigDecimal2 = new BigDecimal(3);
        BigDecimal div = bigDecimal.divide(bigDecimal2, 10, RoundingMode.HALF_UP);
        System.out.println(String.format("div:%s", div));
    }

    private void testHello() {
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

