/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_tg2re;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Zain
 */
public class Converter {
    
    ArrayList<ArrayList<TR>> TT = new ArrayList<>();
    ArrayList<Integer> incomming = new ArrayList<>();
    ArrayList<Integer> outgoing = new ArrayList<>();
    
    ArrayList<TR> pickOut = new ArrayList<>();
    ArrayList<TR> pickIncom = new ArrayList<>();
    
    int[] IS;
    int[] FS;
    int nSt;
    
    public Converter(ArrayList<ArrayList<TR>> tt, int[] IS, int[] FS, int st){
        this.TT = tt;
        this.IS = IS;
        this.FS = FS;
        this.nSt = st;
    }
    
    public String convert(){
        
        int state;
        
        for (int i = 0; i < TT.size(); i++) {
            reduceEdges(i);
        }
        
        for (int j = 0; j< nSt; j++) {
            System.out.println("\nStep : "+(j+1));
            state = stateToEliminate();
            System.out.println(state+" choosen due to less incomming and outgoing edges!");
            eliminateState(state);
            
            for (int i = 0; i < TT.size(); i++) {
                    reduceEdges(i);
            }
          // display();
        }
        
        String re = TT.get(nSt).get(0).getCh();
        System.out.println("\nRE of given TT is : "+re+"\n");
        return re;

    }
    
    public void reduceEdges(int states){
        
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Integer> delTrack = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        
        //concatenate duplicates and save index to delTrack array...
          for (int elements = 0; elements < TT.get(states).size(); elements++) {
             index.add(TT.get(states).get(elements).getState());
             
             if(s.add(TT.get(states).get(elements).getState()) == false){
                    
                 int st = TT.get(states).get(elements).getState();
                 String s2 = TT.get(states).get(elements).getCh();
               //System.out.println(elements);
               //  System.out.println("State: "+st+" appears double with string "+s2);
                  
                 delTrack.add(elements);

                 for (int i = 0; i < index.size(); i++) {
                    if(index.get(i).equals(st)){
                        String s1 = TT.get(states).get(i).getCh();
                      //  System.out.println("s1 :"+s1);
                        if(s1.equals("")){
                          TT.get(states).get(i).setCh(s2);
                        }else{
                            TT.get(states).get(i).setCh("("+s1+"+"+s2+")");
                        }
                    }
                }
            }

                
         }

          //remove elements of delTrack
          int size = delTrack.size();
          for (int z =0; z < size; z++) {
              int max = Collections.max(delTrack);

            //  System.out.println("eeee :"+max);
              TT.get(states).remove(max);
              
              for (int i = 0; i < delTrack.size(); i++) {
                  if(delTrack.get(i).equals(max)){
                      delTrack.remove(i);
                  }
             }

        }
            
    }
    
    
    public int stateToEliminate(){
        
        ArrayList<Integer> totalEdges = new ArrayList<>();
        int total;
        
        for (int i = 0; i < this.nSt; i++) {

                incomming(i);
                outgoing(i);

                if(!incomming.isEmpty() && !outgoing.isEmpty()){
                    
                
                    total = incomming.size() * outgoing.size();

                  //  System.out.println(incomming.size() + ","+outgoing.size());
                  //  System.out.println(total);

                    totalEdges.add(total);
                    incomming.clear();
                    outgoing.clear();
              }else{
                  totalEdges.add(9999);
              }
              
            
        }
        int min = Collections.min(totalEdges);
       // System.out.println("min :"+min);
        for (int i = 0; i < totalEdges.size(); i++) {
            if(totalEdges.get(i).equals(min))
            {
        //        System.out.println(i);
                return i;
            }
        }
        
        return -1;
    }
    

    public void incomming(int st){
        
        incomming.clear();
        for (int states = 0; states < TT.size(); states++) {
            for (int elements = 0; elements < TT.get(states).size(); elements++) {
                if(TT.get(states).get(elements).getState() == st && states != st){
                    incomming.add(states);
                }
            }
        }
        
       // return incomStr;
        
//        System.out.println("\nIncomming");
//        for (int z = 0; z < incomming.size(); z++) {
//            System.out.println(incomming.get(z));
//        }

    }
    
    public void outgoing(int st){
        
        outgoing.clear();
        pickOut.clear();
        for (int elements = 0; elements < TT.get(st).size(); elements++) {
            if(TT.get(st).get(elements).getState() != st){
                pickOut.add(TT.get(st).get(elements));
                outgoing.add(TT.get(st).get(elements).getState());
            }       
        }
        
//        System.out.println("\nOutgoing");
//        for (int x = 0; x < outgoing.size(); x++) {
//            System.out.println(pickOut.get(x).getState()+","+pickOut.get(x).getCh());
//        }
    }
    
    
    public String loop(int st){
        int loop;
        String loopConcatenate = "";
        //System.out.println("\nLoop");
        for (int i = 0; i < TT.get(st).size(); i++) {
            if(TT.get(st).get(i).getState() == st){
                 loop = TT.get(st).get(i).getState();
                 String s1;
                 s1 = TT.get(st).get(i).getCh();
                 TT.get(st).get(i).setCh("("+s1+")*");
                 loopConcatenate = TT.get(st).get(i).getCh();
            }
        }
        return loopConcatenate;
        //System.out.println(loop);
    }
    
    
    
    public void eliminateState(int st){
        
        //System.out.println("st :"+st);
      //  ArrayList<Integer> delTrack2 = new ArrayList<>();
        incomming(st);
        outgoing(st);
        String containLoop = loop(st);
        
        for (int i = 0; i < incomming.size(); i++) {
            for (int j = 0; j < TT.get(incomming.get(i)).size(); j++) {
                if(TT.get(incomming.get(i)).get(j).getState() == st){
                    
                    String s1 = TT.get(incomming.get(i)).get(j).getCh();
                  // delTrack2.add(j);
                    
                    for (int k = 0; k < pickOut.size(); k++) {
                        String s2 = pickOut.get(k).getCh();
                        int outSt = pickOut.get(k).getState();
                       // System.out.println("st :"+outSt+", ch :"+s2);
                        TT.get(incomming.get(i)).add(new TR(outSt, s1+containLoop+s2));
                      //  System.out.println(s1+containLoop+s2);
                    }
                    
                    
                   // int del = delTrack.get(0);
                    TT.get(incomming.get(i)).remove(j);
                    
                }
                
                
            }
            
            
        }
        
       TT.get(st).clear();
        
        
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
