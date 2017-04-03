package ads_project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class decoder {

	public decoder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_inputEncodedFile = args[0];
		_inputCodeTableFile = args[1];
		long startTime = System.nanoTime();
		try {
			System.out.println("Decoding started.....");
			decode();
			
			//printHuffmanTree(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000000;
		System.out.println("Time to Decode: " + duration + " seconds");
		System.out.println("Decoding complete.");

	}
	private static Node generateHuffmanTree(String inputFilePath) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(_inputCodeTableFile));
		String line;
		Node root = new Node();
		Node traverser;
	    while ((line = br.readLine())!= null) 
	    {
	    	if(!line.isEmpty() && line!=null)
	    	{
	    		
	    		Integer number = Integer.parseInt(line.split(" ")[0]);
	    		String code = line.split(" ")[1];
	    		//System.out.println(code);
	    		traverser = root;
	    		for(int i = 0; i < code.length()-1; i++)
	    		{
	    			if(code.charAt(i) == '0')
	    			{
	    				if(traverser.getLeftChild() == null)
	    				{
	    					
	    					traverser.setLeftChild(new Node());
	    				}
	    				//System.out.println("hi");
	    				traverser = traverser.getLeftChild();
	    				//System.out.println("hi2");
	    			}
	    			else if(code.charAt(i) == '1')
	    			{
	    				if(traverser.getRightChild() == null)
	    				{
	    					traverser.setRightChild(new Node());
	    				}
	    				traverser = traverser.getRightChild();
	    			}
	    		}
	    		char c = code.charAt(code.length() - 1);
	    		if(c == '0')
	    		{
	    			traverser.setLeftChild(new Node());
	    			traverser = traverser.getLeftChild();
	    			traverser.setData(number);
	    		}
	    		else
	    		{
	    			traverser.setRightChild(new Node());
	    			traverser = traverser.getRightChild();
	    			traverser.setData(number);
	    		}
	    		//traverser.setData(number);
	    	}
	        
	    }
	    br.close();
	    return root;
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
	private static void printHuffmanTree(Node root) throws IOException 
	{
		
		if(root.getLeftChild() == null && root.getRightChild() == null )
		{
			System.out.println(root.getData());
		}
		else
		{
			printHuffmanTree(root.getLeftChild());
			printHuffmanTree(root.getRightChild());
		}
		
		
		
		
	}
	private static Boolean isBitSet(byte b, int bit){
		return (b & (1 << bit)) != 0;
	}
	
	public static String getEncodedString(String encodedPath) throws IOException{
        byte[] byteArray;
        StringBuilder outputStr=new StringBuilder();
        byteArray=Files.readAllBytes(new File(encodedPath).toPath());
        for(byte b: byteArray){
            outputStr.append(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        return outputStr.toString();
    }
	
	private static void decode() throws IOException
	{
		//read a bit at a time and traverse the tree until you fall off.
		Node root = generateHuffmanTree(_inputCodeTableFile);
		String encodedFileString = getEncodedString(_inputEncodedFile);
        //File file = new File(_inputEncodedFile);
        //byte[] fileData = new byte[ (int) file.length() ];
        //DataInputStream dis;
        FileWriter w;
        try {
            //dis = new DataInputStream(new FileInputStream(file));
            //dis.readFully(fileData);
            //dis.close();
            w = new FileWriter("/home/swapnil/workspace/ADS/src/decoded.txt");
            int newCodePathLength = 0;
            Node traverser = root;
            
            for(int i = 0; i<encodedFileString.length() ; i++ )
            {
                
               
                if( encodedFileString.charAt(i) == '0'){
                    //go to left
                    if (traverser.getLeftChild() == null){
                        //fallen off the tree. Print to file
                        if (i == encodedFileString.length() -1){
                            w.write(traverser.getData());
                        }
                        else{
                            w.write(traverser.getData() + "\n");
                            traverser = root.getLeftChild();
                        }
                    }
                    else{
                        traverser = traverser.getLeftChild();
                    }
                } 
                else{
                    //go to right of tree
                    if (traverser.getRightChild() == null){
                        //fallen off the tree. PRint to file
                        if (i == encodedFileString.length() -1){
                            w.write(traverser.getData() );
                        }
                        else{
                            w.write(traverser.getData() + "\n");
                            traverser = root.getRightChild();
                        }
                        
                    }
                    else{
                        traverser = traverser.getRightChild();
                    }
                }
            }
            
            w.close();
            HashMap<Integer, String> a = new HashMap<>();
			FileWriter w1 = new FileWriter("/home/swapnil/workspace/ADS/src/new_code_table.txt");
			generateHuffmanCodes(root, "",a , w1);
        } catch (IOException ex) {
            
        }
	    
	        
	}
	private static Node createNode(String code)
	{
		Node node = new Node();
		return node;
	}
	private static String _inputCodeTableFile;
	private static String _inputEncodedFile;
}
