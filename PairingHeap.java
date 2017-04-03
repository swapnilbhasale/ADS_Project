package ads_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PairingHeap {

	public PairingHeap(String ipFilePath) {
		// TODO Auto-generated constructor stub
		root = null;
		arrlist = new ArrayList();
		inputFilePath =  ipFilePath;
		frequencyTable = new HashMap();
	}
	
	public Node huffmanTreeGenerate()
	{
		while(root.getChild() != null)
		{
			//Node N1 = extractMin();
			//Node N2 = extractMin();
			Insert(createNode(RemoveMin(), RemoveMin()));
			//insert(createInternalNode(N1, N2));
			
		}
		return root.getNode();
	}
	public void generateHuffmanCodes(Node root, String c, FileWriter w) throws IOException
	{
		if(root.getLeftChild() == null && root.getRightChild() == null )
		{
			//System.out.println(root.getData()+" ==> "+c);
			w.write(root.getData()+" " +c+"\n");
			
		}
		else
		{
			String l = c+"0";
			String r = c+"1";
			generateHuffmanCodes(root.getLeftChild(), l, w);
			generateHuffmanCodes(root.getRightChild(), r, w);
		}
		
		//PrintWriter writer1 = new PrintWriter("/home/swapnil/workspace/ADS/src/codetables.txt", "UTF-8");
		//writer1.println(Code);
		//System.out.println(root.getFrequency());
		//System.out.println(Code);
		
	}

	public PairingHeapNode createNode(PairingHeapNode N1, PairingHeapNode N2)
	{
		PairingHeapNode result = new PairingHeapNode();
		result.setLeftChild(N1);
		result.setRightChild(N2);
		result.setFrequency(N1.getFrequency() + N2.getFrequency());
		return result;
	}
	public PairingHeapNode getRoot()
	{
		return root;
	}
	public void printHeap(PairingHeapNode n)
	{
		
		System.out.println(n.getFrequency());
		while(n.getRight() != null)
		{
			n = n.getRight();
			System.out.println(n.getFrequency());
		}
		if(n.getChild() != null)
		{
			printHeap(n.getChild());
		}
	}
	public void buildHeap()
	{
		try {
			
			Scanner sc = new Scanner(new File(inputFilePath));
			    while (sc.hasNextInt()) {
			        int number = sc.nextInt();
			        frequencyTable.put(number, (frequencyTable.get(number) == null ? 1 :  frequencyTable.get(number)+1 )); 
			        //System.out.println(number);
			    }
			    //System.out.println(frequencyTable);
			    //PrintWriter writer = new PrintWriter("/home/swapnil/workspace/ADS/src/frequency.txt", "UTF-8");
			    //writer.println(frequencyTable);
			    Iterator itr = frequencyTable.entrySet().iterator();
			    while (itr.hasNext())
			    {
			      Map.Entry pairs = (Map.Entry)itr.next();
			      //System.out.println(pairs.getKey() + " ==> " + pairs.getValue());
			      //writer.println(pairs.getKey() + " ==> " + pairs.getValue());
			    }
			    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
			    	PairingHeapNode number = new PairingHeapNode();
			    	number.setData(entry.getKey());
			    	number.setFrequency(entry.getValue());
			    	
			    	Insert(number);
			    	//System.out.println(number.getFrequency());
			    	
			    }
			  
			    
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 	
	}
	public PairingHeapNode RemoveMin()
	{
		PairingHeapNode min = root;
		//System.out.println("hi " + root.getChild().getFrequency());
		
		if(root.getChild() != null)
		{
			root = Meld(root.getChild());
		}
		else
		{
			root = null;
		}
		
		min.setChild(null);
		return min;
	}
	public PairingHeapNode Meld(PairingHeapNode n)
	{
		PairingHeapNode next ;
		while(n != null)
		{
			next = n.getRight();
			n.setRight(null);
			n.setLeft(null);
			arrlist.add(n);
			n = next;
			//System.out.println("yo: " +n.getFrequency());
			
			
		}
		//for(int i=0; i<arrlist.size();i++)
			//System.out.println("list :: "+arrlist.get(i).getFrequency() + " end");
		//System.out.println("size:: " +arrlist.size());
		while(arrlist.size() > 1)
		{
			PairingHeapNode n1 = arrlist.remove(0);
			//System.out.println("n1:: " +n1.getFrequency());
			PairingHeapNode n2 = arrlist.remove(0);
			//System.out.println("n2:: " +n2.getFrequency());
			//PairingHeapNode r = new PairingHeapNode();
			if(n1.getFrequency() <= n2.getFrequency())
			{
				if(n1.getChild() == null)
				{
					n1.setChild(n2);
					n2.setLeft(n1);
					arrlist.add(n1);
				}
				else//n1 has child
				{
					PairingHeapNode temp = n1.getChild();
					n1.setChild(n2);
					n2.setRight(temp);
					temp.setLeft(n2);
					n2.setLeft(n1);
					arrlist.add(n1);
				}
				
			}
			else
			{
				if(n2.getChild() == null)
				{
					n2.setChild(n1);
					n1.setLeft(n2);
					arrlist.add(n2);
				}
				else//n2 has child
				{
					PairingHeapNode temp = n2.getChild();
					n2.setChild(n1);
					n1.setLeft(n2);
					n1.setRight(temp);
					temp.setLeft(n1);
					arrlist.add(n2);
				}
				
			}
			
		}
		//System.out.println("sup: " + arrlist.get(0).getChild().getRight().getRight().getFrequency());
		return arrlist.remove(0);
		
	}
	public void Insert(PairingHeapNode n)
	{
		
		if(root == null)
		{
			//System.out.println(n.getFrequency());
			root = n;
		}
		else 
		{
			if(root.getFrequency() <= n.getFrequency())
			{
				if(root.getChild() == null)
				{
					root.setChild(n);
					n.setLeft(root);
					//System.out.println("ABC " + n.getFrequency());
				}
				
				
				else
				{
					PairingHeapNode temp = root.getChild();
					root.setChild(n);
					n.setRight(temp);
					temp.setLeft(n);
					n.setLeft(root);
					//System.out.println("EFG " + n.getFrequency() + n.getLeft().getFrequency() + n.getRight().getFrequency() + temp.getLeft().getFrequency()+ root.getChild().getFrequency());
				}
			}
			else
			{
				n.setChild(root);
				root.setLeft(n);
				root = n;
				//System.out.println("HIJ " + root.getFrequency() + root.getChild().getFrequency() + root.getChild().getLeft().getFrequency());			
			}
		}
	}
	
	private PairingHeapNode root;
	private String inputFilePath;
	private ArrayList<PairingHeapNode> arrlist;
	private HashMap<Integer, Integer> frequencyTable;
}
