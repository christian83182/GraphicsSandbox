package com.christianfolkesson;

import java.awt.*;

public class Sandbox extends AbstractSandbox{

    public static final Integer STARTING_WIDTH = 1000;
    public static final Integer STARTING_HEIGHT = 1000;
    public static final String title = "Graphics Sandbox";

    Sandbox(Application application){
        super(application);
    }

    Point ballPos = new Point(0,0);

    public void render(Graphics2D g2d, ApplicationInfo appInfo, MouseInfo mouseInfo) {
        //render logic
    }

    public void update(Double delta, ApplicationInfo appInfo, MouseInfo mouseInfo) {
        //update logic
    }
}
