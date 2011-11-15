package org.projecthquery.gateway;

import java.util.Iterator;

class WrappedIterator implements Iterator<Object>{
    Iterator<Object> iter;
    public WrappedIterator(Iterator<Object> iter){
        this.iter = iter;
    }
    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return iter.hasNext();
    }

    @Override
    public Object next() {
        // TODO Auto-generated method stub
        return iter.next();
    }

    @Override
    public void remove() {
        
        
    }
    
}