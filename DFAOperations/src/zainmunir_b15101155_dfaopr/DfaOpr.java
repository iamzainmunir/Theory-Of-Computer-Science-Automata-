/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zainmunir_b15101155_dfaopr;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Zain
 */
public class DfaOpr {

    ArrayList<StateOr> stateCombOR = new ArrayList<>();
    ArrayList<StateConc> stateCombCONC = new ArrayList<>();
    ArrayList<StateClosure> stateCombClosure = new ArrayList<>();
    
    ArrayList<ArrayList<TR>> TT = new ArrayList<>();
    
    private int index;
    private int IS;
    private int nSt;
    private int[] FS;
    private int explicitFS;

    private DFA dfa1, dfa2;

    public DfaOpr() {
        this.index = -1;
    }

    public DFA dfaOR(DFA dfa1, DFA dfa2) {

        this.dfa1 = dfa1;
        this.dfa2 = dfa2;
        
        StateOr s1 = new StateOr(dfa1.IS, dfa2.IS);
        stateCombOR.add(s1);

        for (int outer = 0; outer < stateCombOR.size(); outer++) {
            StateOr s;
            ArrayList<TR> row = new ArrayList<>();

            for (int i = 0; i < dfa1.inputChar.length; i++) {

                int x;
                int y;
                int st;

                String ch = dfa1.inputChar[i];
                x = dfa1.transition(stateCombOR.get(outer).getX(), dfa1.inputChar[i].charAt(0));
                y = dfa2.transition(stateCombOR.get(outer).getY(), dfa2.inputChar[i].charAt(0));

                if (!existForOr(s = new StateOr(x, y))) {

                    s.setX(x);
                    s.setY(y);
                    stateCombOR.add(s);

                    st = stateCombOR.indexOf(s);
                    row.add(new TR(st, ch));

                } else {

                    st = index;
                    row.add(new TR(st, ch));

                }

            }

            TT.add(row);
        }

        //setting FS
        ArrayList<Integer> tempXFS = new ArrayList<>();
        ArrayList<Integer> tempYFS = new ArrayList<>();
        ArrayList<Integer> tempFS = new ArrayList<>();

        for (int i = 0; i < dfa1.FS.length; i++) {
            tempXFS.add(dfa1.FS[i]);
        }
        for (int i = 0; i < dfa2.FS.length; i++) {
            tempYFS.add(dfa2.FS[i]);
        }

        boolean alreadyAdded = false;
        for (int i = 0; i < stateCombOR.size(); i++) {
            alreadyAdded = false;
            for (int j = 0; j < tempXFS.size(); j++) {

                if (stateCombOR.get(i).getX() == tempXFS.get(j)) {
                    tempFS.add(i);
                    alreadyAdded = true;
                    break;
                }
            }

            if (!alreadyAdded) {

                for (int j = 0; j < tempYFS.size(); j++) {

                    if (stateCombOR.get(i).getY() == tempYFS.get(j)) {
                        tempFS.add(i);
                        break;
                    }
                }

            }
        }

        FS = new int[tempFS.size()];
        for (int i = 0; i < tempFS.size(); i++) {
            FS[i] = tempFS.get(i);
        }

        this.IS = 0;
        this.nSt = TT.size();

        //arraylistTT to simple array converter
        int[][] TTFinal = new int[nSt][dfa1.inputChar.length];
        for (int row = 0; row < nSt; row++) {
            for (int col = 0; col < dfa1.inputChar.length; col++) {

                TTFinal[row][col] = TT.get(row).get(col).getState();
            }
        }

        displayWTForOr();
        displayTT();

        TT.clear();
        DFA dfaOr = new DFA(nSt, dfa1.inputChar, IS, FS, TTFinal);
        return dfaOr;

    }

    public DFA DFAConcatenate(DFA dfa1, DFA dfa2) {

        
        this.dfa1 = dfa1;
        this.dfa2 = dfa2;
        
        
        ArrayList<Integer> y0 = new ArrayList<>();
        StateConc s0;
        int st;

        int x0 = dfa1.IS;

        if (isFinalForConcNClosure(x0)) {
            y0.add(dfa2.IS);
        } else {
            y0 = null;
        }

        s0 = new StateConc(x0, y0);

        stateCombCONC.add(s0);

        for (int i = 0; i < stateCombCONC.size(); i++) {

            ArrayList<TR> row = new ArrayList<>();

            for (int j = 0; j < dfa1.inputChar.length; j++) {
                ArrayList<Integer> yArray = new ArrayList<>();
                StateConc s;

                String ch = dfa1.inputChar[j];
                int x = dfa1.transition(stateCombCONC.get(i).getX(), dfa1.inputChar[j].charAt(0));
                if (isFinalForConcNClosure(x) && !yArray.contains(dfa2.IS)) {
                    yArray.add(dfa2.IS);
                }

                if (stateCombCONC.get(i).getY() != null) {

                    for (int k = 0; k < stateCombCONC.get(i).getY().size(); k++) {
                        int y = dfa2.transition(stateCombCONC.get(i).getY().get(k), dfa2.inputChar[j].charAt(0));

                        if (!yArray.contains(y)) {
                            yArray.add(y);
                            Collections.sort(yArray);
                        }

                    }
                }
                if (yArray.isEmpty()) {
                    yArray = null;
                }
                s = new StateConc(x, yArray);

                if (existForConc(s) == -1) {  
                    stateCombCONC.add(s);
                    st = stateCombCONC.indexOf(s);
                    row.add(new TR(st, ch));

                } else {

                    st = index;
                    row.add(new TR(st, ch));
                }

            }
            TT.add(row);
        }

        displayWTForConc();
        displayTT();

        this.IS = dfa1.IS;
        this.nSt = TT.size();

        int[][] TTFinal = new int[nSt][dfa1.inputChar.length];
        for (int row = 0; row < nSt; row++) {
            for (int col = 0; col < dfa1.inputChar.length; col++) {

                TTFinal[row][col] = TT.get(row).get(col).getState();
            }
        }

        TT.clear();
        DFA dfaConc = new DFA(nSt, dfa1.inputChar, IS, FindFinalStatesForConc(), TTFinal);
        return dfaConc;
        
    }

    public DFA DFAClosure(DFA dfa){
        
        this.dfa1 = dfa;
        boolean ISisFS = false;
        ArrayList<Integer> xInit = new ArrayList<>();
        StateClosure s0;
        int state;
        
        if(isFinalForConcNClosure(dfa.IS)){
            ISisFS = true;
        }

        this.explicitFS = dfa.IS;
        xInit.add(dfa.IS);
        s0 = new StateClosure(xInit);
        stateCombClosure.add(s0);
        
        for (int i = 0; i < stateCombClosure.size(); i++) {
            
            ArrayList<TR> row = new ArrayList<>();
            
            for (int j = 0; j < dfa.inputChar.length; j++) {
                
                ArrayList<Integer> x = new ArrayList<>();
                StateClosure s;
                String ch = dfa.inputChar[j];
                
                for (int st = 0; st < stateCombClosure.get(i).getX().size(); st++) {
                    
                  int tempX = dfa.transition(stateCombClosure.get(i).getX().get(st), dfa.inputChar[j].charAt(0));
                    
                    if(!x.contains(tempX)){
                        x.add(tempX);
                        
                    }
                }
                
                if (FindFSForClosure(x) && !x.contains(dfa.IS)) {
                    x.add(dfa.IS);
                }
                
                Collections.sort(x);
                
                s = new StateClosure(x);

                if(existClosure(s , ISisFS) == -1){
                    stateCombClosure.add(s);
                    state = stateCombClosure.indexOf(s);
                    row.add(new TR(state, ch));

                } else {

                    state = index;
                    row.add(new TR(state, ch));
                
                }
                
            }
            TT.add(row);
            
        }
        displayWTForClosure();
        displayTT();
        
        this.IS = dfa.IS;

        this.nSt = TT.size();

        int[][] TTFinal = new int[nSt][dfa.inputChar.length];
        for (int row = 0; row < nSt; row++) {
            for (int col = 0; col < dfa.inputChar.length; col++) {

                TTFinal[row][col] = TT.get(row).get(col).getState();
            }
        }

        TT.clear();
        DFA dfaClosure = new DFA(nSt, dfa.inputChar, IS, FindFinalStatesForClosure(), TTFinal);
        return dfaClosure;
    }
    
    
    public DFA DFAComplement(DFA dfa){
        
        this.dfa1 = dfa;
        
        int[] newFS = new int[dfa.numStates - dfa.FS.length];

        int newStates = 0;

        for (int i = 0; i < newFS.length; i++) {

            boolean isFinal = false;

            for (int j = 0; j < dfa.FS.length; j++) {
                if (newStates == dfa.FS[j]) {
                    isFinal = true;
                }

            }

            if (!isFinal) {
                newFS[i] = newStates;
            } else {
                i--;
            }

            newStates++;

        }

        DFA dfaComplement = new DFA(dfa.numStates, dfa.inputChar, dfa.IS, newFS, dfa.TT);
        return dfaComplement;
    }
    
    
    public DFA DFAIntersection(DFA dfa1, DFA dfa2){
        this.dfa1 = dfa1;
        this.dfa2 = dfa2;
        
        DFA dfacomp1 = DFAComplement(dfa1);
        DFA dfacomp2 = DFAComplement(dfa2);
        
        DFA union = dfaOR(dfacomp1, dfacomp2);
        
        DFA finalDFA = DFAComplement(union);
        
        return finalDFA;
        
    }
    
    
    private boolean FindFSForClosure(ArrayList<Integer> Transitions) {

        for (int i = 0; i < dfa1.FS.length; i++) {
            int search = dfa1.FS[i];
            for (int j = 0; j < Transitions.size(); j++) {
                if (Transitions.get(j) == search) {
                    return true;
                }
            }

        }

        return false;

    }
    
    
    private int[] FindFinalStatesForClosure() {

        ArrayList<Integer> FinalStates = new ArrayList<>();
        
        

        for (int i = 0; i < stateCombClosure.size(); i++) {

            boolean Final = false;

            for (int j = 0; j < dfa1.FS.length; j++) {
                int search =dfa1.FS[j];
                for (int k = 0; k < stateCombClosure.get(i).getX().size(); k++) {
                    if (stateCombClosure.get(i).getX().get(k) == search) {
                        Final = true;
                        break;
                    }
                }

                if (Final) {
                    break;
                }

            }

            if (Final) {
                FinalStates.add(i);
            }
        }

        if(!FinalStates.contains(explicitFS)){
            FinalStates.add(explicitFS);
            Collections.sort(FinalStates);
            
        }
        int[] Final = new int[FinalStates.size()];

        for (int i = 0; i < Final.length; i++) {
            Final[i] = FinalStates.get(i);
        }

        return Final;
    }

    
    private int[] FindFinalStatesForConc() {

        ArrayList<Integer> tempFS = new ArrayList<>();

        for (int i = 0; i < stateCombCONC.size(); i++) {

            boolean Final = false;

            for (int j = 0; j < dfa2.FS.length; j++) {
                int search = dfa2.FS[j];

                if (stateCombCONC.get(i).getY() != null) {

                    for (int k = 0; k < stateCombCONC.get(i).getY().size(); k++) {
                        if (stateCombCONC.get(i).getY().get(k) == search) {
                            Final = true;
                            break;
                        }
                    }

                }

                if (Final) {
                    break;
                }

            }

            if (Final) {
                tempFS.add(i);
            }
        }

        int[] Final = new int[tempFS.size()];

        for (int i = 0; i < Final.length; i++) {
            Final[i] = tempFS.get(i);
        }

        return Final;
    }

    
    private int existClosure(StateClosure s, boolean checkISisFS) {

        int j = 1;
        if(checkISisFS){
            j =0;
        }
        
        for (int i = j; i < stateCombClosure.size(); i++) {

            if (stateCombClosure.get(i).getX().equals(s.getX())) {

                this.index = i;
                return i;
            }

        }

        return -1;

    }
    
    
    private int existForConc(StateConc s) {

        for (int i = 0; i < stateCombCONC.size(); i++) {

            if (s.getY() == null || stateCombCONC.get(i).getY() == null) {

                if (stateCombCONC.get(i).getX() == s.getX() && stateCombCONC.get(i).getY() == null && s.getY() == null) {

                    this.index = i;
                    return i;
                }

            } else if (stateCombCONC.get(i).getX() == s.getX() && stateCombCONC.get(i).getY().equals(s.getY())) {

                this.index = i;
                return i;
            }

        }

        return -1;

    }

  
    public boolean CheckFinalClosure(ArrayList<Integer> x) {

        for (int i = 0; i < dfa1.FS.length; i++) {
            int search = dfa1.FS[i];
            for (int j = 0; j < x.size(); j++) {
                if (x.get(j) == search) {
                    return true;
                }
            }

        }

        return false;

    }
     
    private boolean isFinalForConcNClosure(int state) {

        for (int i = 0; i < dfa1.FS.length; i++) {

            if (state == dfa1.FS[i]) {
                return true;
            }

        }
        return false;
    }

    private boolean existForOr(StateOr s) {

        boolean exist = false;

        for (int i = 0; i < stateCombOR.size(); i++) {
            if (stateCombOR.get(i).getX() == s.getX() && stateCombOR.get(i).getY() == s.getY()) {
                exist = true;
                index = i;
                break;
            }
        }

        return exist;
    }

    private void displayTT() {

        System.out.println("\n\n****** Transition Table *******");
        for (int i = 0; i < TT.size(); i++) {

            System.out.print("\n" + i + " : ");

            for (int j = 0; j < TT.get(i).size(); j++) {

                TT.get(i).get(j).display();

            }
            System.out.println();
        }
    }

    private void displayWTForConc() {

        System.out.println("\n\n***** DFA Concatenate Working Table ******");
        for (int i = 0; i < stateCombCONC.size(); i++) {
            System.out.print("\n" + i + " | ");
            if (stateCombCONC.get(i).getY() != null) {

                stateCombCONC.get(i).display();
            } else {
                stateCombCONC.get(i).display();
            }

        }

    }

    private void displayWTForOr() {

        System.out.println("\n\n***** DFA OR Working Table ******");
        for (int i = 0; i < stateCombOR.size(); i++) {
            System.out.print("\n" + i + " | ");
            stateCombOR.get(i).display();
        }
        System.out.println();
    }
    
    private void displayWTForClosure() {

        System.out.println("\n\n***** DFA Closure Working Table ******");
        for (int i = 0; i < stateCombClosure.size(); i++) {
            System.out.print("\n" + i + " | ");
            stateCombClosure.get(i).display();
        }
        System.out.println();
    }

}
