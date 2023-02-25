/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primos;

/**
 *
 * @author carlos
 */
public class PrimosThread extends Thread {
    int inicio;
    int fim;
    
    public PrimosThread() {
        super();
        this.inicio= 0;
        this.fim= 10;
    }
    public PrimosThread(int inicio, int fim) {
        super();
        this.inicio= inicio;
        this.fim= fim;
    }    
    
    public void run() {
        Primos p= new Primos(inicio,fim);
        p.go();
    }
    
    public static void main(String[] args) {
        PrimosThread p1= new PrimosThread(0,50000);
        
        PrimosThread p2= new PrimosThread(50001,100000);
        
        PrimosThread p3 = new PrimosThread(100001,150000);
        
        PrimosThread p4= new PrimosThread(150001,200000);
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        
    }
}
