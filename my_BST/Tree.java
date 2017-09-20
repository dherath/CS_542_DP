

class Tree
{
    private Node root; // the only data field in the tree

    public Tree(){
	root = null;
    }
    
    public Node find(int key){
	Node current = root;
	while(current.getBNumber()!=key){
	    if(key<current.getBNumber()){
		current = current.getLeftChild();
	    }else{
		current = current.getRightChild();
	    }
	    if(current==null){
		return null;
	    }
	}
	return current;
    }

    public void insert(int id, String subjectName){
	if(this.find(id)==null){
	    Node newNode = new Node();
	    newNode.setBNumber(id);
	    newNode.insertSubject(subjectName);
	    if(root==null){
		root = neNode;
	    }else{
		Node current = root;
		Node parent;
		while(true){
		    parent = current;
		    if(id<current.getBNumber()){
			current = current.getLeftChild();
			if(current==null){
			    
			}
		    }
		}
	    }
	}else{
	    
	}
	
    }

    public void delete(){}
}
