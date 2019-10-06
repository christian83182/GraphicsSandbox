package com.christianfolkesson;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    private Sandbox sandboxPanel;

    Application() {
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

    private void gameLoop() {
        boolean running = true;
        boolean paused = false;
        final double GAME_HERTZ = 60.0;
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    sandboxPanel.updateGame(1);
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
                sandboxPanel.setInterpolation(interpolation);
                sandboxPanel.repaint();
                lastRenderTime = now;

                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();

                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    now = System.nanoTime();
                }
            }
        }
    }
}
