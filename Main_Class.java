package ads_project;
import java.io.FileWriter;
import java.io.IOException;



public class Main_Class {
	public static void main(String[] args) throws IOException {
		/*
		System.out.println("Generating Code Table using Four-Way Cache optimized Heap: ");
		long FH_Final_Time = 0;
		for(int i=0; i<10; i++)
		{
			long startTimeFourWay = System.nanoTime();
			FourwayHeap fh = new FourwayHeap("/home/swapnil/workspace/ADS/src/sample_input_large.txt");
			fh.buildHeap();
			//fh.printHeap();
			Node r = fh.huffmanTreeGenerate();
			FileWriter w_fh = new FileWriter("/home/swapnil/workspace/ADS/src/FH_code_table.txt");
			fh.generateHuffmanCodes(r, "", w_fh);
			w_fh.close();
			long endTimeFourway = System.nanoTime();
			long duration_FH = (endTimeFourway - startTimeFourWay)/1000000000;
			System.out.println("Run "+ (i+1) + ": " + duration_FH + " seconds");
			FH_Final_Time = FH_Final_Time + duration_FH;
			
			
		}
		System.out.println("FOUR-WAY CACHE OPTIMIZED HEAP TIME = " + FH_Final_Time/10 + " seconds");
		System.gc();
		
		/*
		
		System.out.println("Generating Code Table using Binary Heap: ");
		long BT_Final_Time = 0;
		for(int i =0; i<10; i++)
		{
			long startTimeBT = System.nanoTime();
			BinaryHeap bh = new BinaryHeap("/home/swapnil/workspace/ADS/src/sample_input_large.txt");
			bh.buildHeap();
			Node root = bh.huffmanTreeGenerate();
			FileWriter w_bh = new FileWriter("/home/swapnil/workspace/ADS/src/BT_code_table.txt");
			bh.generateHuffmanCodes(root, "", w_bh);
			w_bh.close();
			long endTimeBT = System.nanoTime();
			long duration_BT = (endTimeBT - startTimeBT)/1000000000;
			System.out.println("Run "+ (i+1) + ": " + duration_BT + " seconds");
			BT_Final_Time = BT_Final_Time + duration_BT;
		}
		
		System.out.println("BINARY HEAP TIME = " + BT_Final_Time/10 + " seconds");
		System.gc();
		
		/*
		long startTimePH = System.nanoTime();
		PairingHeap ph = new PairingHeap("/home/swapnil/workspace/ADS/src/sample_input_large.txt");
		ph.buildHeap();
		PairingHeapNode n = ph.getRoot();
		Node m = ph.huffmanTreeGenerate();
		FileWriter w_ph = new FileWriter("/home/swapnil/workspace/ADS/src/PH_code_table.txt");
		ph.generateHuffmanCodes(m, "", w_ph);	
		w_ph.close();
		long endTimePH = System.nanoTime();
		long duration_PH = (endTimePH - startTimePH)/1000000000;
		System.out.println("PAIRING HEAP TIME = " + duration_PH + " seconds");
		System.gc();
		*/

		
		
	}

}
