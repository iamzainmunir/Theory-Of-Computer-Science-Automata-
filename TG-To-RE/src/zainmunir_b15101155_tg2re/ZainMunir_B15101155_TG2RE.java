/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_tg2re;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zain
 */
public class ZainMunir_B15101155_TG2RE {


    public static void main(String[] args) {
      
        ArrayList<ArrayList<TR>> TT = new ArrayList<>();
        
                
        ArrayList<TR> row0=new ArrayList<>();
        ArrayList<TR> row1=new ArrayList<>();
        ArrayList<TR> row2=new ArrayList<>();
        ArrayList<TR> row3=new ArrayList<>();
        ArrayList<TR> row4=new ArrayList<>();
        
        //EVEN EVEN
        row0.add(new TR(1,"a"));
        row0.add(new TR(3,"b"));
        
        row1.add(new TR(0,"a"));
        row1.add(new TR(2,"b"));
        
        row2.add(new TR(3,"a"));
        row2.add(new TR(1,"b"));
        
        row3.add(new TR(2,"a"));
        row3.add(new TR(0,"b"));
        

    //     row0.add( new TR(1,"a"));
         //row0.add( new TR(1,"new"));
//        
//         row1.add( new TR(1,"b"));
//         row1.add( new TR(2,"bb"));
//        // row1.add( new TR(1,"new"));
//         
//         row2.add( new TR(2,"a"));
//        // row2.add( new TR(6,""));
//       //  row2.add( new TR(2,"new"));
//         
//         row3.add( new TR(1,"aa"));
//         row3.add( new TR(4,"ba"));
//         
//         row4.add( new TR(2,"b"));
        // row4.add( new TR(6,""));
         

         TT.add(row0);
         TT.add(row1);
         TT.add(row2);
         TT.add(row3);
        // TT.add(row4);
         
         int nSt = 4;
         int[] IS = {
             0 //, 3 
         };
         
         int[] FS = {
            0 //2, 4
         };


         //adding IS and FS
            ArrayList<TR> rowIS=new ArrayList<>();
            ArrayList<TR> rowFS=new ArrayList<>();
            
             for (int j = 0; j < IS.length ; j++) {
                 rowIS.add(new TR(IS[j], ""));
             }
             
             for (int j = 0; j < FS.length ; j++) {
                 TT.get(FS[j]).add(new TR(nSt+1,""));
             }
            
            
            TT.add(rowIS);
            TT.add(rowFS);
        //****
      
         Scanner sc= new Scanner(System.in);
         Converter con = new Converter(TT, IS, FS, nSt);
         
//         con.eliminateState(1);
//         
//         con.convert();
//         con.display();
//         
//         con.eliminateState(3);
//         
//         con.convert();
//         con.display();
//         
//         con.eliminateState(2);
//         
//         con.convert();
//         con.display();
//         
//         con.eliminateState(0);
//         
//         con.convert();
//         con.display();
         
         
        con.display();
        String regex = con.convert();
        
        System.out.println("Do you want to check your RE ? (Y/N)");
        String checkRe = sc.next();
        
        if(checkRe.equals("Y") || checkRe.equals("y")){
            
        
        String replace = regex.replace('+', '|');
        System.out.println("Your RE in pattern form : "+replace);

        String again = "n";
        
        do{
            
            System.out.println("\nEnter word to check your RE");
            String input = sc.next();

            Pattern regexx = Pattern.compile(replace);
            Matcher check = regexx.matcher(input);
            boolean isTrue = check.matches();

            if(isTrue){
                System.out.println("Valid");
            }
            else{
                System.out.println("Invalid");
            }
            
            System.out.println("\nEnter another word? (Y/N)");
            again = sc.next();
            
        }while("Y".equals(again) || "y".equals(again));
            
        }
        
        
        
         
        
//         
//         con.display();
         
         

    }
    
}
