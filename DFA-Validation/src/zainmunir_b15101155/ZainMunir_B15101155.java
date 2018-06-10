/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155;

import java.util.Scanner;

/**
 *
 * @author Zain
 */
public class ZainMunir_B15101155 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String allowIden = "[a-zA-Z],[0-9],[-]";
        String[] arrayIden = allowIden.split(",");
        int[] finalState = new int[]{
          2  
        };
        int[][] TTIden = new int[][]{
            {2,3,1},
            {2,2,3},
            {2,2,3},
            {3,3,3}   
        };
        int initState = 0;
        DFA identifier = new DFA(arrayIden, initState, finalState, TTIden);

       
       
        
        String allowInt = "[+-],[0-9]";
        String[] arrayInt = allowInt.split(",");
        int[] finalStateInt = new int[]{
          2  
        };
        int[][] TTInt = new int[][]{
            {1,2},
            {3,2},
            {3,2},
            {3,3}   
        };
        int initStateInt = 0;
        DFA integer = new DFA(arrayInt, initStateInt, finalStateInt, TTInt);
        
        
        
        String allowFloat = "[+-],[.],[0-9],[eE]";
        String[] arrayFloat = allowFloat.split(",");
        int[] finalStateFloat = new int[]{
          3, 7
        };
        int[][] TTFloat = new int[][]{
            {1,2,1,6},
            {6,2,1,6},
            {6,6,3,6},
            {6,6,3,4},
            {5,6,7,6},
            {6,6,7,6},
            {6,6,6,6},
            {6,6,7,6}
        };
        int initStateFloat = 0;
        DFA floatCheck = new DFA(arrayFloat, initStateFloat, finalStateFloat, TTFloat);
        
        
        
        
       
        String allowChar = "\\\\,[rbnto],[^'],[']";
        String[] arrayChar = allowChar.split(",");
        int[] finalStateChar = new int[]{
          3 
        };
        int[][] TTChar = new int[][]{
            {5,5,5,1},
            {4,2,2,5},
            {5,5,5,3},
            {5,5,5,5},
            {5,2,5,5},
            {5,5,5,5}
        };
        int initStateChar = 0;
        DFA charCheck = new DFA(arrayChar, initStateChar, finalStateChar, TTChar);
        
        
        
        String allowString = "[^\\\\\"rbtno],[rbtno],[\\\\],[\"]";
        String[] arrayString = allowString.split(",");
        int[] finalStateString = new int[]{
          4  
        };
        int[][] TTString = new int[][]{
            {5,5,5,1},
            {3,3,2,4},
            {5,3,3,3},
            {3,3,2,4},
            {5,5,5,5},
            {5,5,5,5}
        };
        int initStateString = 0;
        DFA stringCheck = new DFA(arrayString, initStateString, finalStateString, TTString);
        
//        identifier.display();
//        integer.display();
//        floatCheck.display();
//        charCheck.display();
//        stringCheck.display();


        
        Scanner sc = new Scanner(System.in);
        String again;
        do{
            System.out.println("ENTER ANY Identifier, Integer, Float, Char or String");
            String input = sc.next();

            if(identifier.validate(input)){
                System.out.println("It is Identifier");
            }else if(integer.validate(input)){
                System.out.println("It is Integer");
            }else if(floatCheck.validate(input)){
                System.out.println("It is Float");
            }else if(charCheck.validate(input)){
                System.out.println("It is Character");
            }else if(stringCheck.validate(input)){
                System.out.println("It is String");
            }else{
                System.out.println("INVALID INPUT");
            }
            System.out.println("Enter another input (Y/N)");
            again = sc.next();
        }while(again.equals("Y") || again.equals("y"));
     
    }
    
}
