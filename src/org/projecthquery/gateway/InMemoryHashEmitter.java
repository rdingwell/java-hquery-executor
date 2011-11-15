package org.projecthquery.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * No parameters are needed to initialize this iterator.
 * 
 * @author PMORK
 */
public class InMemoryHashEmitter implements EmitIterator {

    private HashMap<Object, List<Object>> results;

    @Override
    public void init(String[] args) {
        results = new HashMap<Object, List<Object>>();
    }

    @Override
    public void emit(Object key, Object value) {
        List<Object> valueList = find(key);
        valueList.add(value);
    }



    @Override
    public Iterator<Object> iterator() {
        return new WrappedIterator(results.keySet().iterator());
    }

    @Override
    public Object[] get(Object key) {
        Object[] array = new Object[0];
        return find(key).toArray(array);
    }

    @Override
    public void delete() {
        results = null;
    }
    
    public Iterator<Object> values(Object key){
        return new WrappedIterator(find(key).iterator());
    }

    private List<Object> find(Object key) {
        for (Object kvKey : results.keySet()) {
            if (compareKeys(key, kvKey)) {
                return results.get(kvKey);
            }
        }
        List<Object> value = new ArrayList<Object>();
        results.put(key, value);
        return value;
    }

    private boolean compareKeys(Object a, Object b) {
        if (a.equals(b))
            return true;
        if (a instanceof Map && b instanceof Map) {
            return ((Map) a).entrySet().equals(((Map) b).entrySet());
        }
        return false;
    }

}
