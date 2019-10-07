# GraphicsSandbox

A simple Java template program for playing around with graphics. The program takes care of many of the challenging setup steps required to get a program like this running properly.A 'fixed timestep' gameloop is used to that physics-based simulations can be run "accurately". The program also deals with keyboard and mouse input. 

Should you want to use this template. Simply clone the repo, open the "Sandbox" class, and start writing code in the update() and render() methods. The program will call update() whenever the game's internal model needs updating. Because of the gameloop used there should be a constant amount of time between each update. The render() method will be called whenever the game needs rendering. An "interpolation" value is given incase the developer wants to interpolate the graphics rendered. 

Various constants are also given in the "Sandbox" class. These control the starting window size, window title, and target frame rate. In addition, custom "ApplicationInfo", "MouseInfo", and "KeyboardInfo" objects are given as parameters to the update() and render() method for the developer to use. These contain information about the window, mouse, and keyboard respectively. The methods are relatively intuative but feel free to take a look at the "AbstractSandbox" class to see all methods. 

All the template code has been thoroughly commented. This template can be pretty restrictive depeding on what's needed, the developer shouln't feel discouraged from heavily adapting the code should they require it. 
