package org.ro.tuc.pt;

import org.ro.tuc.pt.controller.Controller;
import org.ro.tuc.pt.view.View;

import java.awt.*;
public class App 
{
    public static void main( String[] args )
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        View myView= new View("Polynomial Calculator", ((int) screenSize.getWidth()/3), ((int) screenSize.getHeight()/5),1000, 700);
        myView.setVisible(true);
        Controller controller = new Controller(myView);
    }
}
