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

public class ColorSelect extends JPanel {
    JComboBox colorSelect;
    JComboBox onColorSelect;
    
    final String[] symbolColors={"no symbol", "red", "blue", "green", "yellow", 
            "purple", "cyan", "void"};
    final String[] tileColors={"none", "red", "blue", "green", "yellow", 
            "purple", "cyan"};

        
    public ColorSelect()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //"color"
        JPanel colorSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel colorLab=new JLabel("Color: ");
        colorSubPanel.add(colorLab);
        colorSelect=new JComboBox(symbolColors);
        colorSubPanel.add(colorSelect);
        colorSubPanel.setToolTipText("The geo symbol's color.");

        //"on color"
        JPanel onColorSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel onColorLab=new JLabel("On Color: ");
        onColorSubPanel.add(onColorLab);
        onColorSelect=new JComboBox(tileColors);
        onColorSubPanel.add(onColorSelect);
        onColorSubPanel.setToolTipText("The color of tile the geo symbol "
                + "is sitting on.");

        //putting it all together
        this.add(colorSubPanel);
        this.add(onColorSubPanel);
    }
    
    public Color[] getSelection()
    {
        Color [] selection=new Color[2];    //[0] is color, [1] is on color
        
        switch(symbolColors[colorSelect.getSelectedIndex()])
        {
            case "no symbol":
                selection[0]=Color.EMPTY;
                break;
            case "red":
                selection[0]=Color.RED;
                break;
            case "blue":
                selection[0]=Color.BLUE;
                break;
            case "green":
                selection[0]=Color.GREEN;
                break;
            case "yellow":
                selection[0]=Color.YELLOW;
                break;
            case "purple":
                selection[0]=Color.PURPLE;
                break;
            case "cyan":
                selection[0]=Color.CYAN;
                break;
            case "void":
                selection[0]=Color.VOID;
                break;
        }
        
        switch(tileColors[onColorSelect.getSelectedIndex()])
        {
            case "none":
                selection[1]=Color.EMPTY;
                break;
            case "red":
                selection[1]=Color.RED;
                break;
            case "blue":
                selection[1]=Color.BLUE;
                break;
            case "green":
                selection[1]=Color.GREEN;
                break;
            case "yellow":
                selection[1]=Color.YELLOW;
                break;
            case "purple":
                selection[1]=Color.PURPLE;
                break;
            case "cyan":
                selection[1]=Color.CYAN;
                break;
        }
        
        return selection;
    }
    
    public void reset()
    {
        colorSelect.setSelectedItem("no symbol");
        onColorSelect.setSelectedItem("none");
    }
    
    //renderer for combo box icons
    public class ComboBoxRenderer extends JLabel implements ListCellRenderer {
    

        public ComboBoxRenderer()
        {

        }





        @Override public Component getListCellRendererComponent(JList list, 
                Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            int selectedIndex=((Integer)value).intValue();


            return this;
        }
}
}
