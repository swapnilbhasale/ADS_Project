package ads_project;

public class PairingHeapNode {

	public PairingHeapNode() {
		// TODO Auto-generated constructor stub
		Child = null;
		Left = null;
		Right = null;
		//data = 0;
		//frequency = 0;
		node = new Node();
	}
	public Integer getData() {
		return node.getData();
	}
	public void setData(Integer d) {
		node.setData(d);
	}
	
	
	public Integer getFrequency(){
		return node.getFrequency();
	}
	
	public void setFrequency(Integer f){
		 node.setFrequency(f);
	}
	
	public PairingHeapNode getChild(){
		return Child;
	}
	
	public PairingHeapNode getRight(){
		return Right;
	}
	public PairingHeapNode getLeft(){
		return Left;
	}
	
	public void setRight(PairingHeapNode r)
	{
		Right = r; 
	}
	public void setLeft(PairingHeapNode l)
	{
		Left = l; 
	}
	public void setChild(PairingHeapNode c)
	{
		Child = c; 
	}
	public Node getNode()
	{
		return node;
	}
	public void setRightChild(PairingHeapNode r)
	{
		node.setRightChild(r.getNode());
	}
	public void setLeftChild(PairingHeapNode l)
	{
		node.setLeftChild(l.getNode());
	}
	public Node getRightChild()
	{
		return node.getRightChild();
	}
	public Node getLeftChild()
	{
		return node.getLeftChild();
	}
	private Node node;
	//private Integer data;
	private PairingHeapNode Child;
	private PairingHeapNode Right;
	private PairingHeapNode Left;
	//private Integer frequency;
}
