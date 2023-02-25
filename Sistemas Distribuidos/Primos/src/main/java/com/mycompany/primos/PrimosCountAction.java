package com.mycompany.primos;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


/**
 *
 * @author carlos
 */

public class PrimosCountAction extends RecursiveAction {
    private int actionStart;
    private int actionEnd;
 
    private int result;
    
    public PrimosCountAction(int start, int end) {
        this.actionStart= start;
        this.actionEnd= end;
    }
    
    
    protected int getResult() {
        return result;
    }
    
    
    /**
     * contagem de primos...
     * Note que nao existem as classes auxiliares do exemplo anterior.
     */
    protected int computeDirectly() {
        int total = 0;
        int i = actionStart; // check if prime    
        while (i <= actionEnd) {
            int c;
            for (c = 2; c <= i - 1; c++) {
                if (i % c == 0) {
                    break;
                }
            }
            if (c == i) {
                //printf("%d\n", i);
                total++;
            }
            i++;   // next prime candidate
        }
        //System.out.println(" (start="+actionStart+") found " + total);
        return total;
    }

    
    @Override
    protected void compute() {
        if ( actionEnd-actionStart < 10000 ) {
            int r= computeDirectly();
            //System.err.println("\t found: "+r);
            this.result= r;
            return;
        }
        
        // subtasks
        int middle= (actionEnd-actionStart)/2  + actionStart;        
        PrimosCountAction t1= new PrimosCountAction(actionStart, middle);
        PrimosCountAction t2= new PrimosCountAction(middle, actionEnd);
        invokeAll(t1,t2);
        // join reduction
        this.result= t1.getResult()+t2.getResult();
    }
    
     public static void main(String[] args) {
       PrimosCountAction contaPrimos = new PrimosCountAction(1,200000);
        
       ForkJoinPool pool = new ForkJoinPool();
       System.out.println( pool.getParallelism() );
        
       pool.invoke(contaPrimos);
        
       System.out.println("RESULTADO: " + contaPrimos.getResult());
   }
    
}