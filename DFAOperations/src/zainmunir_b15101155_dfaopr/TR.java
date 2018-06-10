/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_dfaopr;

/**
 *
 * @author Zain
 */
public class TR {
    
    int state;
    String ch;
    
    public TR(int st, String ch){
        this.state = st;
        this.ch = ch;
    }
    public TR(){
        
    }
    public void display(){
        System.out.print("{"+this.state+" , "+this.ch+"} ");
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }
    
}
