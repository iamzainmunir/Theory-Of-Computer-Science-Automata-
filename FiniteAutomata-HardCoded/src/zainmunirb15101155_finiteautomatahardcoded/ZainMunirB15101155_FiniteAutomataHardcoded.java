/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunirb15101155_finiteautomatahardcoded;

import java.util.Scanner;

/**
 *
 * @author Zain
 */
public class ZainMunirB15101155_FiniteAutomataHardcoded {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] TT = new int[][]{
            {1,3},
            {0,2},
            {3,1},
            {2,0}   
        };
        char[] allowChar = new char[]{
          'a','b'  
        };
        int[] finalState = new int[]{
          0  
        };
        
        Scanner sc = new Scanner(System.in);
        FA myFA = new FA(4,allowChar,0,finalState,TT);
        System.out.println("\n\nENTER YOUR WORD (TO CHECK EVEN-EVEN ONLY)");
           boolean isValid = myFA.validate(sc.next());
           if(isValid)
           {
               System.out.println("Your input is valid");
           }
           else
           {
               System.out.println("Your input is not valid");
           }
        
    }
    
}
