/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_nfa;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zain
 */
public class NFA {
    
    int nStates;
    int IS;
    int[] FS;

    Stack<Integer> Sts;
    Stack<Integer> Trans;
    ArrayList<ArrayList<TR>> TT;

    
    public NFA(ArrayList<ArrayList<TR>> TT, int IS,int [] FS){
    
        this.IS = IS;
        this.FS = FS;
        this.TT = TT;
        Sts = new Stack<>();
        Trans = new Stack<>();
    }
    
    
    public boolean validate(String input){
        
        Sts.clear();
        Trans.clear();
        int CS = IS;
        int prevSt;
        int transitions = 0;
        boolean found = false;//, outerloop = true;
        int i = 0;
        
        while(true){
            
            while(i < input.length()){
                prevSt = CS;
//                System.out.println("zzz: "+transitions);
//                System.out.println("ay : "+i);
                
                CS = transition(CS , input.charAt(i), transitions);
//                System.out.println("CS : "+CS);
                
                if(CS == -1){
                    
                    if(Sts.isEmpty()){
                        i--;
                        found = false;
                        break;
                    }
                    else{
                        i--;
                        CS = Sts.pop();
                        transitions = Trans.pop();
                    }
                    
                    
                }
                else{
                    
                    i++;
                    Sts.push(prevSt);

                }
                
            }
            
            for (int j = 0; j < FS.length; j++) {
                if(FS[j] == CS){
                    found = true;
                    break;
                }
                
            }     
            
            if(found){
                return true;
            }
            
            else{
//                System.out.println("hi :"+i);
                if(!Sts.isEmpty()){
                    i--;
                    CS = Sts.pop();
                    transitions = Trans.pop();
                }else{
                    return false;
                }
            }

                
        }

       
    }
    
    public int transition(int CS, char input, int transitions){
        
        int nextSt = -1;
        
        for (int i = transitions; i < TT.get(CS).size(); i++) {
            
            Pattern p = Pattern.compile(TT.get(CS).get(i).getCh());
            Matcher m=p.matcher(String.valueOf(input));
            
            if(m.matches()){
                
                Trans.push(i + 1);
                nextSt = TT.get(CS).get(i).getState();
                
            }
            
        }
        
        return nextSt;
    }
    
    
    public void display(){
        
        for (int i = 0; i < TT.size(); i++) {
            
            System.out.print("\n"+i+" : ");
            
            for (int j = 0; j < TT.get(i).size(); j++) {
                
                TT.get(i).get(j).display();
                
            }
            System.out.println();
        }
    }
    
}
