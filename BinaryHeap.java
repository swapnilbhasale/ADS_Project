package ads_project;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class BinaryHeap {

	public BinaryHeap(){
		 heap = new ArrayList();
		 frequencyTable = new HashMap();
		 //inputFilePath =  ipFilePath;
		 Code = "";
	}
	
	
	public void buildHeap(){
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
		    	Node number = new Node();
		    	number.setData(entry.getKey());
		    	number.setFrequency(entry.getValue());
		    	insert(number);
		    	
		    }
		  
		    
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 	
	}
	
	public void printHeap(){
	    for (Node n: heap)
	    	System.out.println(n.getFrequency());
	}
	
	public Integer getLeftChildIdx(Integer index){
		return index*2+1 < heap.size()-1 ?  index*2+1 : -1;
	}
	
	
	public Integer getRightChildIdx(Integer index){
		return index*2+2 < heap.size()-1 ?  index*2+2 : -1;
	}
	
	public Integer getParentIdx(Integer index){
		if(index == 0)
			return 0;
		return (index-1)/2;
	}
	
	
	
	public void insert(Node number){
		
		heap.add(number);
		Integer numberIndex = heap.size()-1;
		if(heap.size() == 1)
			return;
		Node parent = heap.get(getParentIdx(numberIndex));		
		while( number.getFrequency() < parent.getFrequency() && getParentIdx(numberIndex) >= 0 ){
			swapNodes(getParentIdx(numberIndex), numberIndex);
			numberIndex = getParentIdx(numberIndex);
			parent = heap.get(getParentIdx(numberIndex));
		}
	}
	
	
	public Node extractMin(){
		if(heap.size()<1)
		{
			return null;
		}
		Node min = heap.get(0);
		if(heap.size()-1==0)
		{
			heap.remove(0);
			return min;
		}
		heap.set(0, heap.remove(heap.size()-1));
		minHeapify(0);
		//System.out.println(min.getFrequency());
		return min;
		
	}
	
	
	

	public Integer getMinChildIdx(Integer i)
	{
		Integer leftChild = getLeftChildIdx(i);
		Integer rightChild = getRightChildIdx(i);
		Integer minChildIdx;
		if(leftChild != -1 && rightChild != -1)
		{
			minChildIdx = heap.get(leftChild).getFrequency() < heap.get(rightChild).getFrequency() ? leftChild : rightChild;
		}
		else if(leftChild == -1 && rightChild == -1)
		{
			minChildIdx = -1;
		}
		else if(leftChild == -1)
		{
			minChildIdx = rightChild;
		}
		else
		{
			minChildIdx = leftChild;
		}
		return minChildIdx;
	}
	public void minHeapify(Integer numberIndex){
		
		Integer minChildIdx = getMinChildIdx(numberIndex);
		if(minChildIdx == -1)
		{
			return;
		}
		swapNodes(minChildIdx, numberIndex);
		minHeapify(minChildIdx);
	
	}
	
	
	public void swapNodes(Integer node1Idx, Integer node2Idx){
		Node temp = heap.get(node1Idx);
		heap.set(node1Idx, heap.get(node2Idx));
		heap.set(node2Idx, temp);
	}
	
	
	public Node huffmanTreeGenerate()
	{
		while(heap.size() != 1)
		{
			//Node N1 = extractMin();
			//Node N2 = extractMin();
			insert(createNode(extractMin(), extractMin()));
			//insert(createInternalNode(N1, N2));
			
		}
		return heap.get(0);
	}
	public Integer getHeapSize()
	{
		return heap.size();
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
		
		
		
	}

	public Node createNode(Node N1, Node N2)
	{
		Node result = new Node();
		result.setLeftChild(N1);
		result.setRightChild(N2);
		result.setFrequency(N1.getFrequency() + N2.getFrequency());
		return result;
	}
	
	private String Code;
	private String inputFilePath;
	private List<Node> heap;
	private HashMap<Integer, Integer> frequencyTable;

}

