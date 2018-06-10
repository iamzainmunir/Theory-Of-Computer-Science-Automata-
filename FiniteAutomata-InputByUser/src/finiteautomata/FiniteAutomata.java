/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finiteautomata;

import java.util.Scanner;

/**
 *
 * @author Zain
 */
public class FiniteAutomata {


    public static void main(String[] args) {
     
        
        boolean noError = true;
        int numOfStates, initialState = 0;
        char[] icc;
        int[] finalStates = null;
        
        Scanner sc = new Scanner(System.in);
       
        System.out.println("Enter number of states");
        numOfStates = sc.nextInt();
        
        System.out.println("Input allowed characters");
        String inputAllowedCharacter = sc.next();
        String[] tempS = inputAllowedCharacter.split(",");
        icc = new char[tempS.length];
        
        for (int i = 0; i < tempS.length; i++) {
            
               icc[i] = tempS[i].charAt(0);
           if(!Character.isLetter(icc[i])){
               System.out.println("Invalid input");
               noError = false;
               break;
           }
            
        }
       

        if(noError){
              System.out.println("Enter initial state");
              initialState = sc.nextInt();
             if(initialState > numOfStates || initialState < 0)
              {
                  System.out.println("Invalid input");
                 //System.out.println("Initial state cannot be greater than number of states ("+numOfStates+")");
                 noError = false;
             }
        }
        
        if(noError){
             System.out.println("Enter final states");
             String input = sc.next();
             String[] tempString;
             tempString = input.split(",");
             finalStates = new int[tempString.length];
             for (int i = 0; i < tempString.length; i++) {
            
                    try{
                      finalStates[i] = Integer.parseInt(tempString[i]);
                        if(finalStates[i]>numOfStates || finalStates[i]<0)
                          {
                            System.out.println("Invalid input");
                            noError = false;
                            break;
                       }
                  }catch(NumberFormatException e)
                     {
                        System.out.println("Invalid input. Please enter only valid integer");
                        noError = false;
                        break;
                  }

                }
            }
        
        
        if(noError)
        {
            int inputTT;
            int[][] TT = new int[numOfStates][icc.length];
            for (int row = 0; row < numOfStates; row++) {
                for (int col = 0; col < icc.length; col++) {
                   
                   System.out.println("Enter "+row+","+col+" Element");
                   inputTT = sc.nextInt();
                   TT[row][col] = inputTT;
                }
            }
           FA myFA = new FA(numOfStates,icc,initialState,finalStates,TT);
           //myFA.display();
           System.out.println("\n\nENTER YOUR WORD");
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
    
}
