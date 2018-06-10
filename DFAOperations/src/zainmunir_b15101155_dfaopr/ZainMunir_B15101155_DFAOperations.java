/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_dfaopr;

import java.util.Scanner;

/**
 *
 * @author Zain
 */
public class ZainMunir_B15101155_DFAOperations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner sc = new Scanner(System.in);
        
        
//        int[][] TT = new int[][]{
//            {2,3,1},
//            {2,2,3},
//            {2,2,3},
//            {3,3,3}   
//        };
        

        String allowChar = "a,b";
        String[] inCharArray = allowChar.split(",");
        
        int[][] TTdfa1 = new int[][]{
            {1,0},
            {1,0}
            
//            {0,1},
//            {1,2},
//            {2,3},
//            {3,3}
            
     //       {1,0}, {1,0}
//            {1,2},
//            {1,1},
//            {2,2}
//            {0,2},
//            {3,1},
//            {2,0}   
        };

        
        int[] finalStatedfa1 = new int[]{
          1
        };
        int nStdfa1 = 2;
        int ISdfa1= 0;
        
        
        int[][] TTdfa2 = new int[][]{
            {0,1},{1,2},{2,3},{3,3} 
         //   {1,0},{2,0},{2,2}
                
        //    {0,1},{0,1}//,{2,2}
//            {1,2},
//            {3,2},
//            {1,3},
//            {3,3}
//            {0,2},
//            {3,1},
//            {2,0}   
        };


        int[] finalStatedfa2 = new int[]{
           2 ,0
        };
        int nStdfa2 = 4;
        int ISdfa2= 0;

        DFA dfa1 = new DFA(nStdfa1, inCharArray, ISdfa1, finalStatedfa1, TTdfa1);
        DFA dfa2 = new DFA(nStdfa2, inCharArray, ISdfa2, finalStatedfa2, TTdfa2);
        
        dfa1.display();
        dfa2.display();
        
        
        DfaOpr dfaOR = new DfaOpr();
        DfaOpr dfaConc = new DfaOpr();
        DfaOpr dfaClosure = new DfaOpr();
        DfaOpr dfaComplement = new DfaOpr();
        DfaOpr dfaInter = new DfaOpr();
        

        
        
       // DFA dfaor = dfaOR.dfaOR(dfa1, dfa2);
       // DFA dfaconc = dfaConc.DFAConcatenate(dfa1, dfa2);
        DFA dfaClos = dfaClosure.DFAClosure(dfa2);
      //  DFA dfaComp = dfaComplement.DFAComplement(dfa1);
      //  DFA dfaInt = dfaInter.DFAIntersection(dfa1, dfa2);
//        
//        
//        System.out.println("\n\nOR");
//        dfaor.display();
        
//        System.out.println("\n\nConcatenate");
//        dfaconc.display();
//        System.out.println();
        System.out.println("\n\nClosure");
        dfaClos.display();
//        
//        System.out.println("\n\nComplement");
//        dfaComp.display();
//        
//        
//        System.out.println("\n\nIntersection");
//        dfaInt.display();
//        DfaOpr dfaConc = new DfaOpr(dfa1, dfa2);
//       // DfaOpr dfaComp = new DfaOpr(dfa1, dfa2);
//        
//        DFA conc = dfaConc.DFAConcatenate(dfa1, dfa2);
//        conc.display();
        
        
//        ArrayList<Integer> A= new ArrayList<>();
//        ArrayList<Integer> B= new ArrayList<>();
//        
//        A.add(1);
//        A.add(2);
//        A.add(3);
//        
        
//        for (int i = 0; i < dddd.FS.length; i++) {
//            System.out.println(dddd.FS[i]);
//        }
//        
//        System.out.println("DO YOU WANNA TEST YOUR DFA?(Y/N)");
//        String test = sc.next();
//        if(test.equals("Y") || test.equals("y")){
//            
//        
//            String again = "n";
//
//            do{
//
//               System.out.println("\nENTER WORD");
//               boolean isValid = conc.validate(sc.next());
//               if(isValid)
//               {
//                   System.out.println("VALID");
//               }
//               else
//               {
//                   System.out.println("INVALID");
//               }
//                System.out.println("Enter another word? (Y/N)");
//                again = sc.next();
//
//            }while (again.equals("Y") || again.equals("y")); 
//        }

    }
    
}
