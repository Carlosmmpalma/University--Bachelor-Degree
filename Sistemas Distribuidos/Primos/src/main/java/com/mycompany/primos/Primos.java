/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.primos;

/**
 *
 * @author carlos
 */

public class Primos {
    private int start;
    private int end;
    
    
    public Primos(int start, int end) {
        this.start= start;
        this.end= end;
    }
    
    int count_primes(int start, int end) {
        int total = 0;
        int i = start; // check if prime    
        while (i <= end) {
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
        return total;
    }

    public void go() {
	
        int found_primes = count_primes(start, end);
        System.out.println("  found "+found_primes);

    }


// MAIN ... AQUI!!!!
    public static void main(String[] args) {
        Primos p1= new Primos(0, 50000); 
        
        
        Primos p2= new Primos(50001,100000);
        
        Primos p3 = new Primos(100001,150000);
        
        Primos p4= new Primos(150001,200000);
        
        p1.go();
                p2.go();
                        p3.go();
                                p4.go();



    }
    
    

}


