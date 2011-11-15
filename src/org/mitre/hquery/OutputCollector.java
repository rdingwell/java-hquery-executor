package org.mitre.hquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OutputCollector {
	
	private String workingDir;
	private Map<Object,List> keyValues = new HashMap<Object, List>();
	public OutputCollector(String workingDir){
		this.workingDir=workingDir;
	}

	public Map getKeyValues(){
		return keyValues;
	}
	public void collect(Object key, Object value){
		List<Object> valueWriter = getValueWriter(key);
		valueWriter.add(value);
	}
	
	
	public Iterator keys(){
		return new WrappedIterator(keyValues.keySet().iterator());
	}
	
	public Iterator values(Object key){
		return keyValues.containsKey(key)? new WrappedIterator(keyValues.get(key).iterator()): null;
	}
	
	
	private List getValueWriter(Object key){
		System.out.println(key);
		for (Object kvKey : keyValues.keySet()) {
			if(compareKeys(key, kvKey)) {
				return keyValues.get(kvKey);
			}
		}
		List<Object> value = new ArrayList<Object>();
		keyValues.put(key, value);
		return value;
	}
	
	private boolean compareKeys(Object a, Object b){	
		if(a.equals(b)) return true;
		if(a instanceof Map && b instanceof Map){
			System.out.println("Instance of maps");
			return ((Map)a).entrySet().equals(((Map)b).entrySet());
		}
		System.out.println(a + "  "  + b);
		System.out.println((a instanceof Map) + " a Instance of map");
		System.out.println((b instanceof Map) + " b Instance of map");
		return false;
	}
	
	class WrappedIterator implements Iterator{
		Iterator iter;
		public WrappedIterator(Iterator iter){
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
	
}
