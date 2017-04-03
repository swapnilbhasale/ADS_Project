package ads_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FourwayHeap {
	public FourwayHeap(String ipFilePath){
		 heap = new ArrayList();
		 heap.add(null);
		 heap.add(null);
		 heap.add(null);
		 frequencyTable = new HashMap();
		 inputFilePath =  ipFilePath;
		 Code = "";
	}
	public Integer getParentIdx(Integer index)
	{
		if(index == 3)
			return 3;
		return (index)/4 + 2;
	}
	public Integer getChildOneIndex(Integer n)
	{
		return 4*n + 1 - 9 < heap.size()-1 ? 4*n + 1 - 9 : -1;
	}
	public Integer getChildTwoIndex(Integer n)
	{
		return 4*n + 2 - 9 < heap.size()-1 ? 4*n + 2 - 9 : -1;
	}
	public Integer getChildThreeIndex(Integer n)
	{
		return 4*n + 3 - 9 < heap.size()-1 ? 4*n + 3 - 9 : -1;
	}
	public Integer getChildFourIndex(Integer n)
	{
		return 4*n + 4 - 9 < heap.size()-1 ? 4*n + 4 - 9 : -1;
	}
	public void printHeap(){
	    for (FourwayHeapNode n: heap)
	    	if(n != null)
	    		System.out.println(n.getFrequency());
	    System.out.println("\n");
	}
	public Node huffmanTreeGenerate()
	{
		while(heap.size() != 4)
		{
			//Node N1 = extractMin();
			//Node N2 = extractMin();
			insert(createNode(extractMin(), extractMin()));
			//insert(createInternalNode(N1, N2));
			
		}
		return heap.get(3).getNode();
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

	public FourwayHeapNode createNode(FourwayHeapNode N1, FourwayHeapNode N2)
	{
		FourwayHeapNode result = new FourwayHeapNode();
		result.setLeftChild(N1);
		result.setRightChild(N2);
		result.setFrequency(N1.getFrequency() + N2.getFrequency());
		return result;
	}
	public Integer getMinChildIndex(Integer numberIndex)
	{
		Integer c1 = getChildOneIndex(numberIndex);
		Integer c2 = getChildTwoIndex(numberIndex);
		Integer c3 = getChildThreeIndex(numberIndex);
		Integer c4 = getChildFourIndex(numberIndex);
		
		Integer minChild12, minChild34, minChildIndex;
		if(c1 != -1 && c2 != -1)
		{
			minChild12 = heap.get(c1).getFrequency() < heap.get(c2).getFrequency() ? c1 : c2;
		}
		else if(c1 == -1 && c2 == -1)
		{
			minChild12 = -1;
		}
		else if(c1 == -1)
		{
			minChild12 = c2;
		}
		else
		{
			minChild12 = c1;
		}
		
		
		if(c3 != -1 && c4 != -1)
		{
			minChild34 = heap.get(c3).getFrequency() < heap.get(c4).getFrequency() ? c3 : c4;
		}
		else if(c3 == -1 && c4 == -1)
		{
			minChild34 = -1;
		}
		else if(c3 == -1)
		{
			minChild34 = c4;
		}
		else
		{
			minChild34 = c3;
		}
		
		if(minChild12 != -1 && minChild34 != -1)
		{
			minChildIndex = heap.get(minChild12).getFrequency() < heap.get(minChild34).getFrequency() ? minChild12 : minChild34;
		}
		else if(minChild12 == -1 && minChild34 == -1)
		{
			minChildIndex = -1;
		}
		else if(minChild12 == -1)
		{
			minChildIndex = minChild34;
		}
		else
		{
			minChildIndex = minChild12;
		}
		
		
		return minChildIndex;
	}
	public void minHeapify(Integer numberIndex)
	{
		Integer minChildIdx = getMinChildIndex(numberIndex);
		if(minChildIdx == -1)
		{
			return;
		}
		swapNodes(minChildIdx, numberIndex);
		minHeapify(minChildIdx);
	
		
				
	}
	
	
	public FourwayHeapNode extractMin(){
		if(heap.size()<4)
		{
			return null;
		}
		FourwayHeapNode min = heap.get(3);
		if(heap.size()-1==3)
		{
			heap.remove(3);
			return min;
		}
		heap.set(3, heap.remove(heap.size()-1));
		minHeapify(3);
		//System.out.println(min.getFrequency());
		return min;
		
	}
	
	public void swapNodes(Integer node1Idx, Integer node2Idx){
		FourwayHeapNode temp = heap.get(node1Idx);
		heap.set(node1Idx, heap.get(node2Idx));
		heap.set(node2Idx, temp);
	}
	public void insert(FourwayHeapNode number)
	{
		heap.add(number);
		Integer numberIndex = heap.size()-1;
		if(heap.size() == 4)
			return;
		FourwayHeapNode parent = heap.get(getParentIdx(numberIndex));
		while( number.getFrequency() < parent.getFrequency() && getParentIdx(numberIndex) >= 3 )
		{
			swapNodes(getParentIdx(numberIndex), numberIndex);
			numberIndex = getParentIdx(numberIndex);
			parent = heap.get(getParentIdx(numberIndex));
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
			    PrintWriter writer = new PrintWriter("/home/swapnil/workspace/ADS/src/frequency.txt", "UTF-8");
			    //writer.println(frequencyTable);
			    Iterator itr = frequencyTable.entrySet().iterator();
			    while (itr.hasNext())
			    {
			      Map.Entry pairs = (Map.Entry)itr.next();
			      //System.out.println(pairs.getKey() + " ==> " + pairs.getValue());
			      //writer.println(pairs.getKey() + " ==> " + pairs.getValue());
			    }
			    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
			    	FourwayHeapNode number = new FourwayHeapNode();
			    	number.setData(entry.getKey());
			    	number.setFrequency(entry.getValue());
			    	insert(number);
			    	
			    }
			  
			    
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	private String Code;
	private String inputFilePath;
	private List<FourwayHeapNode> heap;
	private HashMap<Integer, Integer> frequencyTable;

}

