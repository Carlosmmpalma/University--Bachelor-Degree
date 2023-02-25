/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primos;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author carlos
 */

public class PrimosThreadPool {
public static void main(String[] args) {
    
        PrimosThread p1=  new PrimosThread(0,      50000);
        PrimosThread p2=new PrimosThread( 50000, 100000);
        PrimosThread p3=new PrimosThread(100000, 150000);
        PrimosThread p4=new PrimosThread(150000, 200000);
        
LinkedBlockingQueue<Runnable> queue= new LinkedBlockingQueue();
queue.add(p1);
queue.add(p2);
queue.add(p3);
queue.add(p4);

ThreadPoolExecutor pool= new ThreadPoolExecutor(4,4,5,java.util.concurrent.TimeUnit.MINUTES, queue);
pool.prestartAllCoreThreads();


/*  a colocacao de tarefas na pool pode ser aqui */

//pool.shutdown();
System.out.println("end");
 }

}