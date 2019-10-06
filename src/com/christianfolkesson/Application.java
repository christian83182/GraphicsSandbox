package com.christianfolkesson;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    Sandbox sandboxPanel;

    Application() throws InterruptedException {
        super(Sandbox.title);
        init();
        gameLoop();
    }

    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        sandboxPanel = new Sandbox(this);
        this.add(sandboxPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private void gameLoop() throws InterruptedException {
        while(true){
            sandboxPanel.paintImmediately(this.getBounds());
            Thread.sleep(16);
        }
    }
}
