//Author's default package - refers to folder structure.
package com.christianfolkesson;

//Import used packages. Swing and AWT are used for window operations. MouseAdapter and MouseEvent are used to track the mouse position.
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

//The class is declared abstract as it has abstract methods. An instance of the class cannot be created.
public abstract class AbstractSandbox extends JPanel {

    //Member variables. "application" contains an instance of the Application. "mousePos" keeps track of the current mouse position.
    //"interpolation" gives an interpolation value for rendering.
    private Application application;
    private Point mousePos;
    private boolean isMousePressed;
    private Set<String> pressedKeys;
    private float interpolation;


    //The constructor for the class. This is called when the object is first created. Member variables are assigned/initiated.
    AbstractSandbox(Application application){
        this.application = application;
        this.mousePos = new Point(0,0);
        this.isMousePressed = false;
        this.pressedKeys = new HashSet<>();

        this.addMouseMotionListener(new CustomMouseListener());
        this.addMouseListener(new CustomMouseListener());
        this.addKeyListener(new CustomKeyboardListener());
        //It's common practice to split the configuration of the window from the object's constructor.
        init();
    }


    //The init() method configures the window. It's called in the constructor.
    private void init(){
        //SetPreferredSize() sets the window's size.
        this.setPreferredSize(new Dimension(Sandbox.STARTING_WIDTH,Sandbox.STARTING_HEIGHT));
        this.setFocusable(true);
    }


    //This method overrides the inherited paintComponent() method from JPanel. It's called whenever the component requires re-rendering.
    protected void paintComponent(Graphics g) {
        //super.paintComponent() should always be called at the start if this method is overwritten to ensure other components are displayed correctly.
        super.paintComponent(g);
        //The Graphics object can be cast to a Graphics2D object for enhanced functionality.
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.WHITE);
        //Calling the abstract render() method. A non-abstract child class would provide an implementation for this method.
        render(g2d, interpolation, new ApplicationInfo(application));
    }


    //A method that will be called whenever the game's logic should be updated.
    public void updateGame(){
        //Calling an abstract update() method. A non-abstract child class would provide an implementation for this method.
        update(new ApplicationInfo(application), new MouseInfo(mousePos, isMousePressed), new KeyboardInfo(pressedKeys));
    }


    //The method is declared abstract since no implementation is given. A non-abstract child class would have to provide an implementation.
    abstract public void render(Graphics2D g2d, float interpolation, ApplicationInfo appInfo);


    //The method is declared abstract since no implementation is given. A non-abstract child class would have to provide an implementation.
    abstract public void update(ApplicationInfo appInfo, MouseInfo mouseInfo, KeyboardInfo keyboardInfo);


    //Getter and Setter methods for various memeber variables. This is done instead of accessing the variables directly to maintain encapsulation.
    public Application getApplication() { return application; }

    public void setApplication(Application application) { this.application = application; }

    public float getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }


    //A private inner class which provides a custom implementation for a mouse listener. Used to track the mouse's position.
    private class CustomMouseListener extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            //Update the outer class' mousePos member variable.
            mousePos = new Point(e.getX(),e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            isMousePressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            isMousePressed = false;
        }
    }

    private class CustomKeyboardListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            pressedKeys.add(Character.toString(e.getKeyChar()));
            System.out.println("Key pressed");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            pressedKeys.remove(Character.toString(e.getKeyChar()));
            System.out.println("Key released");
        }
    }


    //A private inner class used as a data structure for passing information through the update() and render() methods.
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


    //A private inner class used as a data structure for passing information through the update() and render() methods.
    public class MouseInfo{
        private Point mousePos;
        private boolean isPressed;

        MouseInfo(Point mousePos, boolean isPressed){
            this.mousePos = mousePos;
            this.isPressed = isPressed;
        }

        public Integer getXPos(){
            return mousePos.x;
        }

        public Integer getYPos(){
            return mousePos.y;
        }

        public Point getMousePoint(){
            return mousePos;
        }

        public Boolean isPressed() {
            return isPressed;
        }
    }


    //A private inner class used as a data structure for passing information through the update() and render() methods.
    public class KeyboardInfo{
        private Set<String> pressedKeys;

        KeyboardInfo(Set<String> pressedKeys){
            this.pressedKeys = pressedKeys;
        }

        public boolean isKeyPressed(String key){
            return this.pressedKeys.contains(key);
        }

        public Set<String> getPressedKeys(){
            return this.pressedKeys;
        }
    }
}
