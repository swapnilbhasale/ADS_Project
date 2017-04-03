package ads_project;

public class Node{

	public Node()
	{
		LeftChild = null;
		RightChild = null;
		frequency = 0;
		data = 0;
	}
	public Integer getData() {
		return data;
	}
	
	public void setData(Integer d) {
		data = d;
	}
	
	
	
	public Integer getFrequency(){
		return frequency;
	}
	
	public void setFrequency(Integer f){
		 frequency = f;
	}
	
	public Node getLeftChild(){
		return LeftChild;
	}
	
	public Node getRightChild(){
		return RightChild;
	}
	
	public void setLeftChild(Node l)
	{
		LeftChild = l; 
	}
	public void setRightChild(Node r)
	{
		RightChild = r; 
	}
	private Integer data;
	private Node LeftChild;
	private Node RightChild;
	private Integer frequency;
}
