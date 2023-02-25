/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.baeldung.thrift.impl;

import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;

public class CrossPlatformServiceClient {

    public static void main(String[] args) throws Exception {

        TTransport transport = new TSocket("localhost", 9090);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        CrossPlatformService.Client client = new CrossPlatformService.Client(protocol);

        boolean result = client.ping();
        
        System.out.println("result: "+result);

        
        CrossPlatformResource r1= client.get(1);
        System.out.println("first fetch for 1: "+r1);
        
        // update object
        r1.setName("SD");
        client.save(r1);
        
        System.out.println("LIST: "+ client.getList());
        CrossPlatformResource r2= client.get(1);
        System.out.println("second fetch for 1: "+r2);
        System.out.println("LIST: "+ client.getList());
        
        transport.close();

    }
}
