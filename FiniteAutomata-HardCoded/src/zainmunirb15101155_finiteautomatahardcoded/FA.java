package zainmunirb15101155_finiteautomatahardcoded;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Zain
 */
public class FA {
    
     
    int numStates;
    char[] inputChar;
    int IS;
    int[] FS;
    int[][] transitionTable;
    
    public FA(int state, char[] inChar, int initState, int[] finalState, int[][]TT){
        this.numStates = state;
        this.inputChar = inChar;
        this.IS = initState;
        this.FS = finalState;
        System.out.println();
        this.transitionTable = TT;//new int [state][inChar.length];
    }
    
    public boolean validate(String input){
        boolean check = false;
        int CS = this.IS;
        char[] ch = input.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            CS = transition(CS,ch[i]);
            //System.out.println(CS);
        }
        for (int i = 0; i < FS.length; i++) {
            if(FS[i] == CS)
            {
                check = true;
            }
            else
            {
                check = false;
            }
        }
        
        return check;
    }
    
    private int transition(int CS, char ch){
        int position = -1;
        for (int i = 0; i < this.inputChar.length; i++) {
           if(ch == this.inputChar[i])
           {
               position = i;
               break;
           }
        }
            if(position == -1)
            {
                  System.out.println("Invalid input");
                    return this.transitionTable[CS][0];
            }

            return this.transitionTable[CS][position];
        
    }
    public void display()
    {
        System.out.println("States :" + this.numStates);
        System.out.println("initial state "+this.IS);
        System.out.print("input char : ");
        for (int i = 0; i < inputChar.length; i++) {
            System.out.print(inputChar[i]);
        }
        System.out.println("");
        System.out.print("final state: ");
        for (int i = 0; i < FS.length; i++) {
            System.out.print(FS[i]);
        }
        System.out.println("");
        
    }
}
