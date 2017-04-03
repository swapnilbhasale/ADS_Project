package ads_project;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class encoder {

	public encoder() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_inputFile = args[0];
		System.out.println("Encoding started.....");
		long startTime = System.nanoTime();
		try {
			encode();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000000;
		System.out.println("Time to Encode: " + duration + " seconds");
		System.out.println("Encoding complete.");
		

	}
	private static Node huffmanTreeGenerate(BinaryHeap bh)
	{
		while(bh.getHeapSize() != 1)
		{
			bh. insert(createHuffmanNode(bh.extractMin(), bh.extractMin()));
			
			
		}
		return bh.extractMin();
	}
	private static Node createHuffmanNode(Node N1, Node N2)
	{
		Node result = new Node();
		result.setLeftChild(N1);
		result.setRightChild(N2);
		result.setFrequency(N1.getFrequency() + N2.getFrequency());
		return result;
	}
	private static void generateHuffmanCodes(Node root, String c, HashMap<Integer, String> codeTable , FileWriter w) throws IOException 
	{
		
		if(root.getLeftChild() == null && root.getRightChild() == null )
		{
			
			codeTable.put(root.getData(), c);
			w.write(root.getData()+" " +c+"\n");
		}
		else
		{
			StringBuilder l =new StringBuilder(c);  
			StringBuilder r =new StringBuilder(c);  
			String L = l.append("0").toString();
			String R = r.append("1").toString();
			generateHuffmanCodes(root.getLeftChild(), L, codeTable, w);
			generateHuffmanCodes(root.getRightChild(), R, codeTable, w);
		}
		
		
		
		
	}
	private static void encode() throws NumberFormatException, IOException
	{
		BinaryHeap bh = new BinaryHeap();
		FileWriter w = new FileWriter("/home/swapnil/workspace/ADS/src/code_table.txt");
		BufferedOutputStream encodedFile = new BufferedOutputStream(new FileOutputStream("/home/swapnil/workspace/ADS/src/encoded.bin"));
		//DataOutputStream os = new DataOutputStream(new FileOutputStream("/home/swapnil/workspace/ADS/src/encoded.bin"));
		HashMap<Integer, String> codeTable = new HashMap<>();
		HashMap<Integer, Integer> frequencyTable = buildFrequencyTable(_inputFile);
	    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
	    	Node number = new Node();
	    	number.setData(entry.getKey());
	    	number.setFrequency(entry.getValue());
	    	bh.insert(number);
	    	
	    }
	    Node r = huffmanTreeGenerate(bh);
	    generateHuffmanCodes(r, "", codeTable, w);
	    w.close();
	    BufferedReader br = new BufferedReader(new FileReader(_inputFile));
	    String line;
	    StringBuilder encodedFileString = new StringBuilder();
	    while ((line = br.readLine()) != null ) 
	    {
	    	if(!line.isEmpty() && line != null)
	    	{
	    		int number = Integer.parseInt(line);
		        if(codeTable.containsKey(number))
		        {
		        	String code = codeTable.get(number);
		        	encodedFileString.append(code);
		        }
	    	}
	        
	    }
        byte[] arr = new byte[encodedFileString.length()/8 + 1];
        for(int i =0; i<encodedFileString.length()/8; i++)
        {
        	arr[i] = (byte) Short.parseShort(encodedFileString.substring(8*i, 8*(i+1)), 2);
        }
        
        System.out.println(arr[0]);
        OutputStream output = null;
        output = encodedFile;
        
        output.write(arr);
        br.close();
        output.close();
    
        encodedFile.close();
        //os.close();
        
	    
	    
	    
	}
	
	private static HashMap<Integer, Integer> buildFrequencyTable(String inputFilePath) throws NumberFormatException, IOException
	{
		HashMap<Integer, Integer> frequencyTable = new HashMap<>();
		//Scanner sc = new Scanner(new File(inputFilePath));
		BufferedReader br = new BufferedReader(new FileReader(_inputFile));
		String line;
	    while ((line = br.readLine())!= null) 
	    {
	    	if(!line.isEmpty() && line!=null)
	    	{
	    		int number = Integer.parseInt(line);
	    		if(frequencyTable.containsKey(number))
	    		{
	    			frequencyTable.put(number, frequencyTable.get(number)+1);
	    		}
	    		else
	    			frequencyTable.put(number, 1);
	    	}
	        
	    }
	    br.close();
	    return frequencyTable;
	}
	private static String _inputFile;
	
}
