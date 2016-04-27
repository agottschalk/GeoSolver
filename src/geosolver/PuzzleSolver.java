/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolver;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class PuzzleSolver {
    //no constructor
    
    public int getSolution(ArrayList <GeoSymbol> original)
    {
        
        ArrayList <GeoSymbol> tempMap=new ArrayList();
        ArrayList <GeoSymbol> chainQueue=new ArrayList();
        
        for(int i=0; i<original.size(); i++)
        {
            //eliminates interference from previous chains
            tempMap.clear();
            chainQueue.clear();
            
            
            //creates a secondary list that we can run geo chains with
            for (GeoSymbol g:original)
                tempMap.add(g.getCopy());
            
            chainQueue.add(tempMap.get(i));
            chainQueue.get(0).setAlive(false);
            
            //checking solutions
            if(this.runChain(tempMap, chainQueue))
                return i;
        }
        
        //if no solution found
        return -1;
    }
    
    public boolean isSolved(ArrayList <GeoSymbol> a)
    {
        //check each tile to see if there is a symbol or a color
        for(GeoSymbol g:a)
        {
            if( g.getAlive() || (g.getOnColor()!=Color.EMPTY) )
                //if there is a symbol or color, map is not solved
                return false;
        }
        
        //this.printData(a);
        
        //otherwise, it is solved
        return true;
    }
    
    public boolean runChain(
        ArrayList <GeoSymbol> temp, ArrayList <GeoSymbol> queue)
    {
        //this.printData(temp);
        //this.printData(queue);
        
        if (this.isSolved(temp))
        {
            return true;
        }
        else if(!queue.isEmpty())
        {
            //makes a copy of the next symbol for reference
            GeoSymbol seed=queue.get(0).getCopy();
            
            if(seed.getOnColor() != Color.EMPTY)    //empty tiles don't affect other empties
            {
                //running color change step
                for(GeoSymbol g:temp)
                {
                    if(g.getOnColor()==seed.getOnColor())
                    {
                        g.setOnColor(seed.getColor());

                        if(g.getAlive())
                        {
                            //any symbols killed are added to chain
                            g.setAlive(false);
                            queue.add(g);
                        }
                    }
                }
            }
            queue.remove(0);
            
            return this.runChain(temp, queue);
        }
        //if empty and not solved
        return false;
    }
    
    
    public void printData(ArrayList <GeoSymbol> arraylist)
    {
        //prints array, for testing only, not part of finished program
        JTextArea outputArea=new JTextArea();
        outputArea.setEditable(false);
        
        //convert geo symbol data into text
        for(GeoSymbol g:arraylist)
        {
            outputArea.append(g.toString());
        }
        
        JScrollPane scrollPane=new JScrollPane(outputArea);
        JPanel panel=new JPanel();
        panel.add(scrollPane);
        
        JFrame frame=new JFrame("Ta-Da!");
        frame.add(panel);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
