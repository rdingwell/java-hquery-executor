package org.mitre.hquery;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class MapReduce {
	String mr_file = "mr.js";
	String map;
	String reduce;
	Context ctx;
	
	public MapReduce(String map, String reduce){
		this.map = map;
		this.reduce = reduce;
	}
	
	
	public void execute(Iterator input, OutputCollector emit, OutputCollector values) throws IOException{
		Context ctx = Context.enter();
		ScriptableObject so = ctx.initStandardObjects();
		
		ctx.evaluateString(so, "map =  " + map, "<map>", 1, null);
		ctx.evaluateString(so, "reduce = "+ reduce, "<reduce>", 1, null);
	
		File f = new File(mr_file);
		FileReader fin = new FileReader(f);
		ctx.evaluateReader(so, fin, "mr.js", 1, null);
		
		so.put("input_iterator", so, input);
		so.put("emit_collector", so, emit);
		so.put("value_collector", so, values);	
		
		ctx.evaluateString(so, "_run()", "<run>", 1, null);
	}
}
