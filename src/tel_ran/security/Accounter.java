package tel_ran.security;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import tel_ran.security.io.LinesInputOutput;

public class Accounter {
	private String fileName;
	private HashMap<String,int[]> callRejects;//key - method name. value - array of statistics 
	//[0]- number of calls, [1] - number of rejects
	
	public Accounter(String fileName) {
		super();
		this.fileName = fileName;
	}
	public void restoreMap() throws IOException{
		//restore map from the file
		callRejects = new HashMap<String,int[]>();
		BufferedReader input = getInput(fileName);
		
		LinesInputOutput lines = new LinesInputOutput(input, null);
		List<String> linesInput = lines.getLines();
		
		for(String str : linesInput){
			String[] strArr = str.split(" ");
			int[] arr = {Integer.parseInt(strArr[1]),Integer.parseInt(strArr[2])};
			callRejects.put(strArr[0], arr );
		}
	}
	private static BufferedReader getInput(String str) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(str));
		} catch (FileNotFoundException e) {
			System.out.println("input file not found");
		}
		return input;
	}
	public void saveUpdatedMap(){
		//save map into file
		PrintWriter output = getOutput(fileName);
		
		LinesInputOutput lines = new LinesInputOutput(null, output);
		lines.putLines(getLines(callRejects));
	}
	private List<String> getLines(HashMap<String, int[]> callRejects) {
		LinkedList<String> lines = new LinkedList<>();
		for(Map.Entry<String,int[]> entry : callRejects.entrySet()){
			lines.add(entry.getKey() + " " + entry.getValue()[0] + " " + entry.getValue()[1]);
		}
		return lines;
	}
	private static PrintWriter getOutput(String str) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(str);
		} catch (FileNotFoundException e) {
			System.out.println("output file not found");
		}
		return output;
	}
	public void methodCallReject(String methodName, boolean call){
		int[] value = callRejects.get(methodName);
		if(value == null)
			value = new int[]{0,0};
		if(call)
			value[0]++;
		else
			value[1]++;
		callRejects.put(methodName, value);
	}
}
