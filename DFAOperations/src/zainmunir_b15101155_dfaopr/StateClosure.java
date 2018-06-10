/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_dfaopr;

import java.util.ArrayList;

/**
 *
 * @author Zain
 */
public class StateClosure {
    
    private ArrayList<Integer> x = new ArrayList<>();

    public StateClosure(){
        this.x = null;
    }
    
    public StateClosure(ArrayList<Integer> x){
        
        this.x = x;
    }
    
    public ArrayList<Integer> getX() {
        return x;
    }

    public void setX(ArrayList<Integer> x) {
        this.x = x;
    }
    
    public void display(){
        
        if(this.x != null){
            for (int i = 0; i < x.size(); i++) {
                System.out.print(" "+x.get(i)+", ");
            }
            System.out.println();
        }
    }
    
}
