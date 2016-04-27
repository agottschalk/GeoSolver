/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolver;

/**
 *
 * @author Alex
 */
import java.awt.*;
import javax.swing.*;

public class CenteredFrame extends JFrame{
    private Toolkit tk;
    
    public CenteredFrame()
    {
        tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();

        //starts w/ upper left corner in middle of screen
        setLocation(screenSize.width/2, screenSize.height/2);
    }
    
    public CenteredFrame(String title)
    {
        setTitle(title);
        tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        
        //starts w/ upper left corner in middle of screen
        setLocation(screenSize.width/2, screenSize.height/2);
    }
    
    public void center()
    {
        //recenters window
        Dimension frameSize=getSize();
        Dimension screenSize=tk.getScreenSize();    //rechecks screen size every time this method is called

        setLocation( (screenSize.width/2)-(frameSize.width/2), 
                (screenSize.height/2)-(frameSize.height/2) );
    }
}
