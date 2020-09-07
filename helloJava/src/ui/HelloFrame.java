package ui;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HelloFrame extends JFrame {
    JLabel label;
    JButton button1;
    int clicks = 0;

    public HelloFrame() {
        setTitle("动作事件监听器示例");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
//        JPanel contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPane.setLayout(new BorderLayout(0, 0));
//        setContentPane(contentPane);
//        label = new JLabel(" ");
//        label.setFont(new Font("楷体", Font.BOLD, 16));    //修改字体样式
//        contentPane.add(label, BorderLayout.SOUTH);
//        String tex = "我是普通按钮";
//        button1 = new JButton(tex);    //创建JButton对
//        button1.setFont(new Font("黑体", Font.BOLD, 16));    //修改字体样式
//        button1.addActionListener(e -> label.setText"按钮被单击了 " + (clicks++) + " 次"));
//        contentPane.add(button1);
//        Date date = new Date();
//        date.getYear();
//        JPanel panel = (JPanel) getContentPane();
//        panel.setOpaque(false);
//        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(panel);
//        MyContent myContent = new MyContent();
//        myContent.setSize(400, 200);
//        getLayeredPane().add(myContent, 0);
//        MyContent myContent = new MyContent();
//        setContentPane(myContent);
//        MyTitleBar myTitleBar = new MyTitleBar();
//        setMenuBar(myTitleBar);

        setUndecorated(true);
        MyDesktop myDesktop = new MyDesktop();
        myDesktop.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int a = 0;
                super.componentResized(e);
            }
        });
        setContentPane(myDesktop);

    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }


    public static void main(String[] args) {
        HelloFrame frame = new HelloFrame();
        frame.setVisible(true);
    }

    @Test
    public void myTest() {

    }
}