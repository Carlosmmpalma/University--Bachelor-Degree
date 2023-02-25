package com.baeldung.thrift.impl;


import java.util.LinkedList;
import java.util.List;
import org.apache.thrift.TException;


public class CrossPlatformServiceImpl implements CrossPlatformService.Iface {

    private List<CrossPlatformResource> resources;
    private int counter;
    
    
    public CrossPlatformServiceImpl() {
        resources= new LinkedList<>();
        counter= 0;
    }

    
    @Override
    public CrossPlatformResource get(int id) 
      throws InvalidOperationException, TException {
        for (CrossPlatformResource c: resources) {
            if (c.getId()== id) {
                return c;
            }
        }
        // else, send a new one
        CrossPlatformResource r= new CrossPlatformResource(++counter, "unknown");
        resources.add(r);
        return r;
    }

    @Override
    public void save(CrossPlatformResource resource) 
      throws InvalidOperationException, TException {
        for (CrossPlatformResource c: resources) {
            if (c.getId()== resource.getId()) {
                resources.remove( c );  // this allows to replace the resource
            }
        }        
        resources.add(resource);
    }

    @Override
    public List<CrossPlatformResource> getList() 
      throws InvalidOperationException, TException {
        //return Collections.emptyList();
        return resources;
    }

    @Override
    public boolean ping() throws InvalidOperationException, TException {
        return true;
    }
}
