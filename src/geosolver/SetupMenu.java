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
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SetupMenu {
    
    //instance data
    private ArrayList <GeoSymbol> SymHolder;
    private ArrayList <GeoPanel> PanelHolder;
    
    private CenteredFrame frame;
    private JMenuBar menuBar;
    private JPanel displayPanel;
    private JPanel geoMenus;
    private JPanel bottomPanel;
    
    
    
    
    
    public SetupMenu()
    {
        SymHolder=new ArrayList();
        PanelHolder=new ArrayList();
        frame=new CenteredFrame("GeoSolver");
    }
    
    
    
    
    
    //***************************
    //*****setting things up*****
    //***************************
    
    public void makeNewSetup()
    {
        int n=getStartingSetup();
        SymHolder.clear();

        for(int i=0; i<n; i++)
        {
            SymHolder.add(new GeoSymbol(i+1));
        }
        
        makeGeoMenus(SymHolder);
        
        draw();
    }
            
    public int getStartingSetup()
    {
        String startingNum=null;
        int n=0;
        
        while(startingNum==null)
        {
            startingNum=(String)JOptionPane.showInputDialog(frame,
                    "How many symbols?", "3");
            if(startingNum!=null)
            {
                try{
                    n=Integer.parseInt(startingNum);    //sets n as number of starting symbols
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(frame, "Please enter a number");
                    startingNum=null;
                }
            }else{
                JOptionPane.showMessageDialog(frame, "Please enter a number");
                startingNum=null;
            }
        }
        
        return n;
    }
    
    
    
    
    
    
    
    
    
    //***********************
    //*****drawing GUI*******
    //***********************
    
    public void draw()
    {
        if (displayPanel != null)  //redraw menu
        {
            frame.revalidate();
            frame.pack();
            frame.center();
            frame.repaint();
        }
        else    //initial drawing of menu
        {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            makeMenuBar();
            frame.setJMenuBar(menuBar);

            displayPanel=new JPanel(new BorderLayout());
            
            //geo menus are already made in 'new' method
            displayPanel.add(geoMenus, BorderLayout.CENTER);

            makeBottomPanel();
            displayPanel.add(bottomPanel, BorderLayout.SOUTH);
            
            
            //return displayPanel;
            frame.add(displayPanel);
            frame.pack();
            frame.center();
            frame.setVisible(true);
        }
    }
    
    public void makeMenuBar()
    {
        menuBar=new JMenuBar();
        JMenu menu=new JMenu("Menu");
        
        //creating items to go in menu
        JMenuItem menuGo=new JMenuItem("Find Solution");
        menuGo.addActionListener(new GoListener());
        JMenuItem menuNew=new JMenuItem("New Map");
        menuNew.addActionListener(new NewListener());
        JMenuItem menuAdd=new JMenuItem("Add Symbol");
        menuAdd.addActionListener(new PlusListener());
        JMenuItem menuReset=new JMenuItem("Reset Symbols");
        menuReset.addActionListener(new ResetListener());
        JMenuItem menuExit=new JMenuItem("Exit");
        menuExit.addActionListener(new ExitListener());
        
        //add them to the menu
        menu.add(menuGo);
        menu.addSeparator();
        menu.add(menuNew);
        menu.add(menuAdd);
        menu.add(menuReset);
        menu.addSeparator();
        menu.add(menuExit);
        menuBar.add(menu);
    }
    
    
    
    
    public void makeGeoMenus(ArrayList<GeoSymbol> geoList)
    {
        if (geoMenus == null)
        {
            geoMenus=new JPanel();
        }
        
        geoMenus.removeAll();
        PanelHolder.clear();
        
        for(GeoSymbol g:geoList)
        {
            GeoPanel p=new GeoPanel(g);
            geoMenus.add(p);
            PanelHolder.add(p);
        }
    }
    
    public void makeBottomPanel()
    {
        if (bottomPanel == null)
        {
            bottomPanel=new JPanel();
        }
        
        bottomPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        
        JButton plus=new JButton("Add Symbol");
        plus.addActionListener(new PlusListener());
        bottomPanel.add(plus);
        
        JButton reset=new JButton("Reset All");
        reset.addActionListener(new ResetListener());
        bottomPanel.add(reset);
        
        JButton go=new JButton("Go!");
        go.addActionListener(new GoListener());
        bottomPanel.add(go);
    }
    
    
    
    
    
    
    //****************************
    //****GeoPanel inner class****
    //****************************
    
    public class GeoPanel extends JPanel
    {
        private final ColorSelect menu;
        private final JPanel buttonPanel;
        private final GeoSymbol associatedSymbol;
        
        public int getNum() {return associatedSymbol.getNumber();}
        public void setNum(int i) {associatedSymbol.setNumber(i);}
        
        public GeoPanel(GeoSymbol g)
        {
            associatedSymbol=g;
            setBorder(BorderFactory.createTitledBorder("Geo Symbol #"
                +associatedSymbol.getNumber()));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            menu=new ColorSelect();
            this.add(menu);
            
            buttonPanel=new JPanel();
            JButton removeButton=new JButton("Remove");
            removeButton.addActionListener(new RemoveListener(this, g));
            buttonPanel.add(removeButton);
            
            this.add(buttonPanel);
            
            g.setAssociatedMenu(menu);
        }
        
        public Color [] getSelection()
        {
            return menu.getSelection();
        }
        
        public void redrawBorder()
        {
            setBorder( BorderFactory.createTitledBorder("Geo Symbol #"
                +associatedSymbol.getNumber()) );
            //repaint();
        }
    }
    
    
    
    //*****************************************
    //*************action listeners************
    //*****************************************
    
    
    private class ResetListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent e)
        {
            for(GeoSymbol g:SymHolder)
                g.reset();
        }
    }
    
    private class GoListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent e)
        {
            //convert menu selections into geosymbol instance data
            for(GeoSymbol g:SymHolder)
            {
                g.getSelection();
            }
            
            //create solver object
            PuzzleSolver solver=new PuzzleSolver();
            
            int solution=solver.getSolution(SymHolder);   //solve
            if(solution==-1)
            {
                //display not solved
                JOptionPane.showMessageDialog(frame, "no solution");
            }else{
                //display solution
                JOptionPane.showMessageDialog(frame, "Solution is #"
                        +(solution+1));
            }
        }
    }
    
    
    private class PlusListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent e)
        {
            if(SymHolder.size() <= 8)
            {
                SymHolder.add(new GeoSymbol(SymHolder.size()+1));   
                //symbols are numbered as their index+1
                
                GeoPanel p=new GeoPanel(SymHolder.get(SymHolder.size()-1));
                geoMenus.add(p);
                PanelHolder.add(p);
                SetupMenu.this.draw();
            }
            
        }
    }
    
    private class RemoveListener implements ActionListener
    {
        private final GeoSymbol associatedSymbol;
        private final GeoPanel associatedPanel;
        
        public RemoveListener(GeoPanel p, GeoSymbol g)
        {
            //links button to its associated geosymbol and menu panel
            associatedSymbol=g;
            associatedPanel=p;
        }
        
        @Override public void actionPerformed(ActionEvent e)
        {
            //removes geo symbol
            SymHolder.remove(associatedSymbol);
            
            //removes menu panel
            geoMenus.remove(associatedPanel);
            
            //resets numbers
            for(GeoSymbol g:SymHolder)
            {
                g.setNumber(SymHolder.indexOf(g)+1);
            }
            for(GeoPanel p:PanelHolder)
            {
                p.redrawBorder();
            }
            geoMenus.revalidate();
            SetupMenu.this.draw();
        }
    }
    
    private class NewListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent e)
        {
            makeNewSetup();
        }
    }
    
    private class ExitListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    
}
