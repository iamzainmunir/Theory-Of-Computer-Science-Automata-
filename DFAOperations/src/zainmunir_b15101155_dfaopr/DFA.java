/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_dfaopr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zain
 */
public class DFA {
    
    int numStates;
    String[] inputChar;
    int IS;
    int[] FS;
    int[][] TT;
    
    public DFA(int state, String[] inChar, int initState, int[] finalState, int[][]TT){
        this.numStates = state;
        this.inputChar = inChar;
        this.IS = initState;
        this.FS = finalState;
        this.TT = TT;//new int [state][inChar.length];
    }

    
    public boolean validate(String input){
    
        int CS=IS;

        for(int i=0; i<input.length(); i++){
        
            if(CS==-1)
                return false;
            CS=transition(CS, input.charAt(i));
       
        }
        
        for (int i = 0; i < FS.length; i++) 
            if(FS[i]==CS)
                return true;
        return false;
        
    }

    public int transition(int CS,char input){
    
        boolean found=false;
        int position=-1;
        Pattern p;
        Matcher m;
        for (int i = 0; i < this.inputChar.length; i++) {
            p=Pattern.compile("["+this.inputChar[i]+"]");
            m=p.matcher(String.valueOf(input));
            if(m.matches()){
                position=i;
                found=true;
                break;
            }  
        }
        
        if(!found)
            return position;
        else
            return TT[CS][position];
    
    }

    
    public void display()
    {
        System.out.println("\n\n***** DFA ******");
        for (int i = 0; i < TT.length; i++) {
            System.out.print("\n"+i+" | ");
            
            for (int j = 0; j < TT[i].length; j++) {
                System.out.print(" "+TT[i][j]);
            }
        }
        System.out.println();
     //   System.out.println("\tYOUR INPUT");
        System.out.println("States :" + this.numStates);
        System.out.println("initial state "+this.IS);
        System.out.print("input char : ");
        for (int i = 0; i < inputChar.length; i++) {
            System.out.print(inputChar[i]);
        }
        System.out.println("");
        System.out.print("final state: ");
        for (int i = 0; i < FS.length; i++) {
            System.out.print(FS[i]+",");
        }
        System.out.println("");
        
    }
    
}
