package com.paddy.createContacts;

import javax.swing.*;

public class DemoFrame extends JFrame{
    public DemoFrame(DemoPanel panel)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setTitle("Frame Demo");
        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        DemoPanel panel = new DemoPanel();
        DemoFrame Frame = new DemoFrame(panel);
    }


}
