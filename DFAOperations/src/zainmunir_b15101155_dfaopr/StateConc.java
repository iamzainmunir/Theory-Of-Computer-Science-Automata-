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
public class StateConc {
    
    private int x;
    private ArrayList<Integer> y = new ArrayList<>();

    public StateConc(){
        
    }
    public StateConc(int x, ArrayList<Integer> y){
        this.x = x;
        this.y = y;
        
    }
    
    
    public boolean Comparator(StateConc State) {

//        if(State.y == null ){
//            System.out.println("Y in null");
//            if(this.x == State.x){
//                return true;
//            }
//            
//            else{
//                return false;
//            }
//        }
//        
//        else{
            System.out.println("Comparator:"+State.x+","+this.x);
            if (this.x == State.x && this.y.size() == State.y.size()) {

            for (int i = 0; i < this.y.size(); i++) {
                if (this.y.get(i) != State.y.get(i)) {
                    return false;
                }
            }

            return true;

           } else {
               return false;
          }
//        }
       
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public ArrayList<Integer> getY() {
        return y;
    }

    public void setY(ArrayList<Integer> y) {
        this.y = y;
    }
    
    public void display(){
        
        if(this.y == null){
            System.out.println(this.x);
        }
        
        else{
            System.out.print(x);
            for (int i = 0; i < this.y.size(); i++) {
                System.out.print(", "+y.get(i)+" ");
            }
            System.out.println();
            
        }
        
    }
    
}
