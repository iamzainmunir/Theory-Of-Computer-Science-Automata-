/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_nfa;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Zain
 */
public class ZainMunir_B15101155_NFA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        ArrayList<ArrayList<TR>> TT = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
      
        
      //  **** HARDCODED ****
        
        ArrayList<TR> row0=new ArrayList<>();
        ArrayList<TR> row1=new ArrayList<>();
        ArrayList<TR> row2=new ArrayList<>();

         row0.add( new TR(0,"[a]"));
         row0.add( new TR(1,"[a]"));
         row0.add( new TR(2,"[a]"));
         
        // row1.add( new TR(1,"[a]"));
         row1.add( new TR(2,"[a]"));
         
         row2.add( new TR(2,"[a]"));
         row2.add( new TR(2,"[b]"));
         
         TT.add(row0);
         TT.add(row1);
         TT.add(row2);
         
         
        int[] FS = {
            2
        };
        int IS = 0;
        
        
         
//         System.out.println("Enter number of states");
//         int nSt = sc.nextInt();
//         System.out.println("Enter initial state");
//         IS = sc.nextInt();
//         
//         String again = "n";
//         
//        for (int i = 0; i < nSt; i++) {
//            
//            ArrayList<TR> row=new ArrayList<>();
//
//            
//            
//            do{
//                System.out.println("Enter state and allow alpha in "+i+" row");
//                row.add(new TR(sc.nextInt(), sc.next()));
//                TT.add(row);
//                
//                System.out.println("Add more tansitions in a row?");
//                again = sc.next();
//                
//            }while(again.equals("Y") || again.equals("y"));
//            
//        }
//        
//        System.out.println("Enter Final States");
//        do{
//            int fs = sc.nextInt();
//            FS.add(fs); 
//            System.out.println("Add more final states?");
//            again = sc.next();
//        }while(again.equals("Y") || again.equals("y"));
//
//        System.out.println("Enter your word");
//        String input = sc.next();
        
         NFA nfa = new NFA(TT,IS,FS);
         nfa.display();
         
         
         String again = "n";
         
          do{
            
            System.out.println("\nEnter word");
            String input = sc.next();
            
            if(nfa.validate(input)){
                System.out.println("Valid");
            }
            else{
                System.out.println("Invalid");
            }
            
            System.out.println("\nEnter another word? (Y/N)");
            again = sc.next();
            
        }while("Y".equals(again) || "y".equals(again));
         
       //  System.out.println(nfa.validate("aab"));
         
//         String s1 = TT.get(0).get(0).getCh();
//         String s2 = "a";
//         if(s1.equals(s2)){
//             System.out.println("tt");
//         }
         
    }
    
}
