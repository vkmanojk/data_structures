import java.util.Scanner;

class BinaryTestTree {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
		try{	
			int size=0;
			Scanner scan=new Scanner(System.in);
			CapacityGetterSetter getset = new CapacityGetterSetter();
			size=Integer.parseInt(scan.nextLine());
			size++;
			getset.setCapacity(size);
			Integer[] testlist = new Integer[size];
			testlist[0] = null;
			String data=scan.nextLine();
			String[] items=data.split(" ");
			for (int i = 1; i <size; i++) {
					testlist[i]=Integer.parseInt(items[i-1]);
			}
			IBinaryTree<Integer> btree1 = new BinaryTree<Integer>();
			btree1.constructTree(testlist);
			//System.out.println();
			int inputs = Integer.parseInt(scan.nextLine());

			while (inputs > 0) {
				String operation = scan.nextLine();
				String[] temp = operation.split(" ");
				if (temp[0].equals("P")) {
					btree1.printTree();
				}
				else if (temp[0].equals("D")) {
						System.out.println( btree1.delete());
				}
				else if (temp[0].equals("S")) {
						System.out.println(btree1.swap(Integer.parseInt(temp[1])));
				}
				else if (temp[0].equals("RMin")) {
					System.out.println(btree1.removemin()); 
				}
	
				if (temp[0].equals("RMax")) {
					System.out.println(btree1.removemax()); 
				}
						
				inputs--;
			}	
		}catch(Exception e){
		    System.out.println(e);
		    return;
		}
			
		}

	}

	class CapacityGetterSetter {
		private static int treesize;

		public int getCapacity() {
			return this.treesize;
		}

		public void setCapacity(int cap) {
			treesize = cap;
		}
	}


class Node<E> {
	private E element; 
	private Node<E> parent;
	private Node<E> leftchild;
	private Node<E> rightchild;
	public Node(){
		element = null;
		parent = null;
		leftchild = null;
		rightchild = null;
	}
	public Node(E e, Node curparent)
	{
		element = e;
		parent = curparent;
	}
	public E getElement(){
		return element;
	}
	public Node<E> getParent(){
		return parent;
	}
	public Node<E> getlChild(){
		return leftchild;
	}
	public Node<E> getrChild(){
		return rightchild;
	}
	public void setElement(E e){
		element = e;
	}
	public void setParent(Node<E> parent1){
		parent = parent1;
		
	}
	public void setLeft(Node<E> lchild){
		leftchild = lchild;
		leftchild.setParent(this);
	}
	public void setRight(Node<E> rchild){
		rightchild = rchild;
		rightchild.setParent(this);
	}
	public boolean isInternal(){
		return (leftchild!=null||rightchild!=null);
	}
	public boolean isExternal(){
		return (leftchild==null && rightchild ==null);
	}
	public boolean isRoot(){
		return (parent == null);
	}
}

interface IBinaryTree<E> {
	int size();
	boolean isEmpty();
	Object getRoot();
	void setRoot(Node<E> node);
	void insertChild(Node<E> curnode, E elt, char type);
	void constructTree(E[] eltArray);
	void printTree();
	E delete();
	boolean swap(E element);
	E removemin();
	E removemax();
}


class BinaryTree<E> implements IBinaryTree<E>{
	private Node<E> root;
	private int tree_sz;
	CapacityGetterSetter getset = new CapacityGetterSetter();
	int size=0;
	private Node<E> nodelist[];
	public BinaryTree(){
		size=getset.getCapacity();
		nodelist = new Node[size];
		root = null;
		tree_sz=0;
	}
	
	
	public Node<E> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	public void setRoot(Node<E> node) {
		// TODO Auto-generated method stub
		root = node;
		tree_sz++;
	}
	
	//inserts a node whose value is elt as a child of curnode
		public void insertChild(Node<E> curnode, E elt, char type) {
		// TODO Auto-generated method stub
		Node<E> newnode = new Node<E>(elt,null);
		if(type=='l')
		  curnode.setLeft(newnode);
		else if(type=='r')
			curnode.setRight(newnode);

	}

	public void constructTree(E[] eltArray) {
		// TODO Auto-generated method stub
		Node<E> newnode = new Node<E>(eltArray[1],null);
		root = newnode;
		nodelist[0]=null;
		nodelist[1]=root; //array reprsentation
		tree_sz=1;
		for (int i=2; i<eltArray.length;i++){
			++tree_sz;
			if (eltArray[i]!=null & nodelist[i/2]!=null){
				 if(i%2==0){
				//	System.out.println(nodelist[i/2].getElement());
					insertChild(nodelist[i/2],eltArray[i],'l');
					nodelist[i]=nodelist[i/2].getlChild();
				}
				else{
					insertChild(nodelist[i/2],eltArray[i],'r');
					nodelist[i]=nodelist[i/2].getrChild();
				}
			}
			else nodelist[i]=null;
		}
	}

	public void printTree() {
		// TODO Auto-generated method stub
		for (int i=1;i<=tree_sz;i++)
		{
			if (nodelist[i]!=null){
				System.out.print(" [Pos:"+i+ "] Elt: "+nodelist[i].getElement());
			}
			else System.out.print(" [Pos:"+i+ "]  Null");
		}
		System.out.println("");
		
	}

	
	public int size() {
		// TODO Auto-generated method stub
		return tree_sz;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (tree_sz==0);
	}

	public E delete(){
	    
	    E temp = nodelist[tree_sz].getElement();
		nodelist[tree_sz].setElement(null);
		tree_sz--;
		return temp;
	}
	
	public boolean swap(E element) {
	    
	    for(int i=1;i<=tree_sz;i++){
	        if(nodelist[1]==null){
	            return false;
	        }
	        if(nodelist[i].getElement()==element){
	            if(nodelist[i].getrChild()!=null && nodelist[i].getlChild()!=null){
	                E temp = nodelist[2*i].getElement();
	                nodelist[2*i].setElement(nodelist[2*i+1].getElement());
	                nodelist[2*i+1].setElement(temp);
	                return true;
	            }else{
	                return false;
	            }
	        }
	    }
		return false;
	}
	
	public E removemin(){
		
		//  To compare the element as integer use (Integer)(nodelist[i].getElement())
		if(tree_sz!=0){
		E min = nodelist[1].getElement();
		int flag = 1;
		for(int i=1;i<=tree_sz;i++){
		    if((Integer)nodelist[i].getElement()<=(Integer)min){
		        min = nodelist[i].getElement();
		        flag = i;
		    }
		}
		nodelist[flag].setElement(nodelist[tree_sz].getElement());
	    nodelist[tree_sz].setElement(null);
	    tree_sz--;
	    return min;}
	    else{
	        return null;
	    }
	}
	public E removemax(){
		
		//  To compare the element as integer use (Integer)(nodelist[i].getElement()) 
		if(tree_sz!=0){
		E max = nodelist[1].getElement();
		int flag = 1;
		for(int i=1;i<tree_sz;i++){
		    if((Integer)nodelist[i].getElement()>=(Integer)max){
		        max = nodelist[i].getElement();
		        flag = i;
		    }
		}
		nodelist[flag].setElement(nodelist[tree_sz].getElement());
	    nodelist[tree_sz].setElement(null);
	    tree_sz--;
	    return max;}
	    else{
	        return null;
	    }
	}
}