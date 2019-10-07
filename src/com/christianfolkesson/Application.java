//Author's default package - refers to folder structure.
package com.christianfolkesson;

//import used packages.
import javax.swing.*;
import java.awt.*;

//Declare the Application class. It extends the JFrame class and will act as the main window.
public class Application extends JFrame {

    //The only member variable is a reference to the Sandbox object used to draw graphics.
    private Sandbox sandboxPanel;

    //The constructor runs when the object is first created.
    Application() {
        //the super method is called in order to give the window a title.
        super(Sandbox.title);
        //init() deals with configuring the window.
        init();
        //gameloop() deals with starting and running the game loop.
        gameLoop();
    }

    //The init() method is responsible for configuring the window.
    private void init(){
        //"this" refers to the instance of the object itself.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Create a new Sandbox object and add it to the window.
        sandboxPanel = new Sandbox(this);
        this.add(sandboxPanel, BorderLayout.CENTER);

        //pack() fits the window the size of the components within. setVisible() makes the window visible.
        this.pack();
        this.setVisible(true);
    }

    //The program used a "fixed timestep" game loop. This type of game loop ensures that the length of time between...
    //... update() calls is constant. Making this type of game loop ideal for physics-based games or simulations.
    //Adapted from Eli Delventhal's fixed time step game loop posted on: http://www.java-gaming.org/index.php?topic=24220.0
    private void gameLoop() {
        boolean running = true;
        boolean paused = false;
        final double GAME_HERTZ = Sandbox.TARGET_FPS;
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
                    sandboxPanel.updateGame();
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
