//Author's default package - refers to folder structure.
package com.christianfolkesson;

//import all classes from the java.awt package. Required for drawing on the screen.
import java.awt.*;

//Declare the class. AbstractSandbox is a parent class which takes care of calling the render() and update() methods.
public class Sandbox extends AbstractSandbox{

    //Declare several publicly accessible constants used to create the window and control the game loop.
    public static final Integer STARTING_WIDTH = 1000;
    public static final Integer STARTING_HEIGHT = 1000;
    public static final String title = "Graphics Sandbox";
    public static final Double TARGET_FPS = 60.0;

    //The object's constructor. Any code in this method will run when the object is created at the start of the program.
    //This is where member variables should be instantiated.
    Sandbox(Application application){
        //The super method calls the constructor of the parent class (AbstractSandbox).
        super(application);
    }

    //The render method will be called whenever a new frame needs to be drawn.
    public void render(Graphics2D g2d, float interpolation, ApplicationInfo applicationInfo) {
        //render logic
    }

    //The update() method will be called whenever the program's logic should be updated.
    public void update(ApplicationInfo appInfo, MouseInfo mouseInfo, KeyboardInfo keyboardInfo) {
        //update logic
    }
}
