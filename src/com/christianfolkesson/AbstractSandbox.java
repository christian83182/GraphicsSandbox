package com.christianfolkesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractSandbox extends JPanel {

    private Application application;
    private Point mousePos;

    AbstractSandbox(Application application){
        this.application = application;
        this.mousePos = new Point(0,0);

        this.addMouseMotionListener(new CustomMouseListener());

        init();
    }

    private void init(){
        this.setPreferredSize(new Dimension(Sandbox.STARTING_WIDTH,Sandbox.STARTING_HEIGHT));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        render(g2d, new ApplicationInfo(application), new MouseInfo(mousePos));
    }

    abstract public void render(Graphics2D g2d, ApplicationInfo appInfo, MouseInfo mouseInfo);

    abstract public void update(Graphics2D g2d, ApplicationInfo appInfo, MouseInfo mouseInfo);

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public class CustomMouseListener extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            mousePos = new Point(e.getX(),e.getY());
        }
    }

    public class ApplicationInfo {
        private Dimension windowDim;

        ApplicationInfo(Application application){
            this.windowDim = new Dimension(application.getWidth(),application.getHeight());
        }

        public double getWidth(){
            return windowDim.getWidth();
        }

        public double getHeight(){
            return windowDim.getHeight();
        }

        public Dimension getDimension(){
            return windowDim;
        }
    }

    public class MouseInfo{
        private Point mousePos;

        MouseInfo(Point mousePos){
            this.mousePos = mousePos;
        }

        public Integer getXPos(){
            return mousePos.x;
        }

        public Integer getYPos(){
            return mousePos.y;
        }
    }
}
