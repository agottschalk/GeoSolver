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

//import java.awt.*;
//import javax.swing.*;

public class GeoSymbol {
    
    //instance data 
    private int number;
    private boolean alive;
    private Color color;
    private Color onColor;
    private Ability ability;
    private boolean inaccessible;
    
    
    static final String[] symbolColors={"no symbol", "red", "blue", "green", "yellow", 
            "purple", "cyan", "void"};
    static final String[] tileColors={"none", "red", "blue", "green", "yellow", 
            "purple", "cyan"};
    
    
    //buttons and panels
    private ColorSelect menu;
    
    //private JComboBox colorSelect;
    //private JComboBox onColorSelect;
    //private JRadioButton other;
    //private JRadioButton invincible;
    //private JRadioButton noColorChange;
    //private JRadioButton noLifting;
    //private JCheckBox inaccessibleCB;

    //mutators
    public void setAlive(boolean a){alive=a;}
    public void setColor(Color c){color=c;}
    public void setAbility(Ability b){ability=b;}
    public void setInaccessible(boolean i){inaccessible=i;}
    public void setAssociatedMenu(ColorSelect p){menu=p;}
    public void setNumber(int i){number=i;}
    
    //void symbols remove color rather than changing it to "void"
    public void setOnColor(Color o)
        {
            if (o==Color.VOID)
                onColor=Color.EMPTY;
            else
                onColor=o;
        }
    
    //accessors
    public int getNumber(){return number;}
    public boolean getAlive(){return alive;}
    public Color getColor(){return color;}
    public Color getOnColor(){return onColor;}
    public Ability getAbility(){return ability;}
    public boolean getInaccessible(){return inaccessible;}
    public ColorSelect getAssociatedMenu(){return menu;}
    
    public GeoSymbol()
    {
        number=0;
        alive=true;
        color=Color.EMPTY;
        onColor=Color.EMPTY;
        ability=Ability.OTHER;
        inaccessible=false;
    }
    
    public GeoSymbol(int n)
    {
        number=n;
        alive=true;
        color=Color.EMPTY;
        onColor=Color.EMPTY;
        ability=Ability.OTHER;
        inaccessible=false;
    }
    
    public GeoSymbol(int n, boolean l, Color c, Color o, Ability a, boolean i)
    {
        number=n;
        alive=l;
        color=c;
        onColor=o;
        ability=a;
        inaccessible=i;
    }
    
    /*
    public JPanel drawMenu()
    {
        JPanel fullMenu=new JPanel();
        fullMenu.setLayout(new BoxLayout(fullMenu, BoxLayout.Y_AXIS));
        
        fullMenu.setBorder(BorderFactory.createTitledBorder("Geo Symbol #"
                +number));
        
        fullMenu.add(this.drawColorSelect());
        //fullMenu.add(this.drawAbilitySelect());
        //fullMenu.add(this.drawInaccessible());
        
        return fullMenu;
    }
    
    
    private JPanel drawColorSelect()
    {
        JPanel colorSelectPanel=new JPanel();
        colorSelectPanel.setLayout(new BoxLayout(colorSelectPanel, 
                BoxLayout.Y_AXIS));
             
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
        colorSelectPanel.add(colorSubPanel);
        colorSelectPanel.add(onColorSubPanel);
        return colorSelectPanel;
    }
    */
    
    /*
    private JPanel drawAbilitySelect()
    {
        JPanel abilitySelectPanel=new JPanel();
        abilitySelectPanel.setLayout(new BoxLayout(abilitySelectPanel,
                BoxLayout.Y_AXIS));
        abilitySelectPanel.setBorder(
                BorderFactory.createTitledBorder("Ability:"));
        
        //make radio buttons
        ButtonGroup abilityBtGp=new ButtonGroup();
                
        other=new JRadioButton("Other");
        other.setToolTipText("Any other effect.  Remember to use healing effects"
                           + " to destroy 'reverse damage' symbols.");
        other.setSelected(true);
        abilityBtGp.add(other);
        abilitySelectPanel.add(other);
        abilitySelectPanel.setAlignmentX(other.RIGHT_ALIGNMENT);
        
        invincible=new JRadioButton("Invincible");
        invincible.setToolTipText("Symbol cannot be killed by attacks, "
                                + "destroy these with geo chains.");
        abilityBtGp.add(invincible);
        abilitySelectPanel.add(invincible);
        abilitySelectPanel.setAlignmentX(invincible.RIGHT_ALIGNMENT);
        
        noColorChange=new JRadioButton("No color change");
        noColorChange.setToolTipText("Map cannot be solved with these "
                                   + "present.  Destroy them first before "
                                   + "trying to solve.");
        abilityBtGp.add(noColorChange);
        abilitySelectPanel.add(noColorChange);
        abilitySelectPanel.setAlignmentX(noColorChange.RIGHT_ALIGNMENT);
        
        noLifting=new JRadioButton("No Lifting");
        noLifting.setToolTipText("Cannot be rearranged"
                               + " to make the puzzle solvable.");
        abilityBtGp.add(noLifting);
        abilitySelectPanel.add(noLifting);
        abilitySelectPanel.setAlignmentX(noLifting.RIGHT_ALIGNMENT);
     
        return abilitySelectPanel;
    }
    */
    
    /*
    private JPanel drawInaccessible()
    {
        JPanel inaccessiblePanel=new JPanel();
        inaccessibleCB = new JCheckBox("Inaccessible");
        inaccessibleCB.setToolTipText("it's too far away or you're just "
                + "too lazy.");
        inaccessiblePanel.add(inaccessibleCB);
        inaccessiblePanel.setAlignmentX(inaccessibleCB.RIGHT_ALIGNMENT);
        
        return inaccessiblePanel;
    }
    */
    
    public void reset(){
        //reset instance data
        //sets values to same values as constructor
        alive=true;
        color=Color.VOID;
        onColor=Color.VOID;
        ability=Ability.OTHER;
        inaccessible=false;
        
        //reset menu options
        menu.reset();
        
        //other.setSelected(true);
        //invincible.setSelected(false);
        //noColorChange.setSelected(false);
        //noLifting.setSelected(false);
        //inaccessibleCB.setSelected(false);
    }
    
    
    
    public void getSelection()
    {
        alive=true;
        
        Color [] selection=new Color[2];
        selection=menu.getSelection();
        
        //[0] is color, [1] is on color
        if(selection[0]==Color.EMPTY)
        {
            alive=false;
        }
        
        color=selection[0];
        onColor=selection[1];
        
        /*
        //check state of Ability radio buttons
        if(other.isSelected())
            ability=Ability.OTHER;
        else if(invincible.isSelected())
            ability=Ability.INVINCIBLE;
        else if(noColorChange.isSelected())
            ability=Ability.NO_COLOR_CHANGE;
        else if(noLifting.isSelected())
            ability=Ability.NO_LIFTING;
        //else error message
        
        //Inaccessibility, the easy one
        inaccessible=inaccessibleCB.isSelected();  
                */
    }
    
    public GeoSymbol getCopy()
    {
        GeoSymbol gs=new GeoSymbol(number, alive, color, 
                onColor, ability, inaccessible);
        return gs;
    }
    
    @Override public String toString()
    {
        String s="\n\nGeo Symbol #";
        s+=number;
        
        
        if(alive)
            s+="\nalive";
        else if(alive==false)
            s+="\ndead";
        else
            s+="\nnull";
        
        if(color!=null)
        {
            switch (color)
            {
                case EMPTY: s+="\nno symbol";
                    break;
                case RED: s+="\nred";
                    break;
                case BLUE: s+="\nblue";
                    break;
                case GREEN: s+="\ngreen";
                    break;
                case YELLOW: s+="\nyellow";
                    break;
                case PURPLE: s+="\npurple";
                    break;
                case CYAN: s+="\ncyan";
                    break;
                case VOID: s+="\nvoid";
                    break;
            }
        }else
            s+="\nnull";
        
        
        if(onColor!=null)
        {
            switch (onColor)
            {
                case EMPTY: s+="\nblank tile";
                    break;
                case RED: s+="\non red";
                    break;
                case BLUE: s+="\non blue";
                    break;
                case GREEN: s+="\non green";
                    break;
                case YELLOW: s+="\non yellow";
                    break;
                case PURPLE: s+="\non purple";
                    break;
                case CYAN: s+="\non cyan";
                    break;
            }
        }else
            s+="\nnull";
        
        
        if(ability != null)
        {
            switch (ability)
            {
                case OTHER: s+="\nother";
                    break;
                case INVINCIBLE: s+="\ninvincible";
                    break;
                case NO_COLOR_CHANGE: s+="\nno color change";
                    break;
                case NO_LIFTING: s+="\nno lifting";
                    break;
            }
        }else
            s+="\nnull";
            
        
        if (inaccessible)
            s+="\ninaccessible";
        else
            s+="\naccessible";

        
        return s;
    }
}
