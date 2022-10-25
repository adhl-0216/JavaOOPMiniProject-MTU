package GUIs;

import javax.swing.*;
import java.awt.*;

public class BackgroundImageJFrame{
    JFrame frame = new JFrame();
    JButton b1;
    JLabel l1;

    public BackgroundImageJFrame() {

        frame.setSize(400,400);

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel background=new JLabel(new ImageIcon("C:/Users/T00229173/IdeaProjects/JavaOOPMiniProject-MTU/src/GUIs/maxresdefault.jpg"));

        frame.add(background);

        background.setLayout(new FlowLayout());

        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");

        background.add(l1);
        background.add(b1);

        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new BackgroundImageJFrame();
    }
}
