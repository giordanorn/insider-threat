public class Tree {
	
	private No root;
	private Tree leftTree;
	private Tree rightTree;
	
	/*
	 * Construtor
	 */
	public Tree(){
		this.root = null;
		this.leftTree = null;
		this.rightTree = null;
	}
	
	/*
	 * Insere um usuário no nó root
	 */
	public void insertUser( User user ) {
		No no = new No(user);
		this.insert(no);
	}
	private void insert(No no) {
		if ( this.root == null ) {
			this.setRoot(no);
		} else {
			if ( no.getUser().getId().compareTo( this.root.getUser().getId() ) > 0 ) {
				if (this.rightTree == null){
					this.setRightTree(new Tree());
				}
				this.rightTree.insert(no);
			} else if ( no.getUser().getId().compareTo(this.root.getUser().getId()) < 0 ) {
				if (this.leftTree == null) {
					this.setLeftTree(new Tree());
				}
				this.leftTree.insert(no);
			}
		}
	}
	
	/*
	 * Busca o usuário pela ID e o retorna
	 */
	public User searchById(String id) {
		return this.searchById(id,this);
	}
	private User searchById(String id, Tree tree) {
		if ( id.equals( tree.getUserId()) ) {
			return tree.getUser();
		} else {
			if ( tree.leftTree == null && tree.rightTree == null ) {
				return null;
			} else if ( id.compareTo( tree.getUserId() ) > 0) {
				return searchById(id, tree.rightTree);
			} else if ( id.compareTo( tree.getUserId() ) < 0) {
				return searchById(id, tree.leftTree);
			} else {
				return null;
			}
		}
	}
	
	/*
	 * Retorna uma Tree com os User que possuem a role passada (inorder)
	 * Referência: http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
	 */
	public Tree searchByRole(String role) {
		Tree users = new Tree();
		Tree tree = this;
		while( tree != null ) {
			if (tree.leftTree == null) {
				if ( role.equals( tree.getUserRole()) )
					users.insertUser( tree.getUser() );
				tree = tree.rightTree;
			} else {
				/* Find the inorder predecessor of current */
				Tree pre = tree.leftTree;
				while(pre.rightTree != null && pre.rightTree != tree) {
					pre = pre.rightTree;
				}
				/* Make current as right child of its inorder predecessor */
				if ( pre.rightTree == null ) {
					pre.rightTree = tree;
					tree = tree.leftTree;
				}
				
				/* Revert the changes made in if part to restore the original 
				tree i.e., fix the right child of predecssor */   
				else {
					pre.rightTree = null;
					if ( role.equals( tree.getUserRole()) )
						users.insertUser( tree.getUser() );
					tree = tree.rightTree;      
				}
			}
		}
		return users;
	}
	
	/*
	 * Imprime a árvore no console pelo nome do usuário
	 * Referência: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram/8948691#8948691
	 */
	public void printByName() {
		if ( this.root != null )
			printById("user_name",true);
		else
			System.out.println("empty");
	}
	private void printByName(String prefix, boolean isTail) {
		System.out.println(prefix + (isTail ? "└── " : "├── ") + this.getUserName() );
		
		if ( this.rightTree != null ) 
			if ( this.leftTree != null ) 
				this.rightTree.printByName(prefix + (isTail ? "    " : "│   "), false);
			else
				this.rightTree.printByName(prefix + (isTail ? "    " : "│   "), true);
		if ( this.leftTree != null ) 
			this.leftTree.printByName(prefix + (isTail ? "    " : "│   "), true);
	}
	
	
	/*
	 * Imprime a árvore no console pelo id do usuário
	 * Referência: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram/8948691#8948691
	 */
	public void printById() {
		if ( this.root != null )
			printById("user_id",true);
		else
			System.out.println("empty");
	}
	private void printById(String prefix, boolean isTail) {
		System.out.println(prefix + (isTail ? "└── " : "├── ") + this.getUserId() );
		
		if ( this.rightTree != null ) 
			if ( this.leftTree != null ) 
				this.rightTree.printById(prefix + (isTail ? "    " : "│   "), false);
			else
				this.rightTree.printById(prefix + (isTail ? "    " : "│   "), true);
		if ( this.leftTree != null ) 
			this.leftTree.printById(prefix + (isTail ? "    " : "│   "), true);
	}
	
	
	public void printUsers() {
		this.getUser().printUser();
		if ( this.rightTree != null ) 
			if ( this.leftTree != null ) 
				this.rightTree.printUsers();
			else
				this.rightTree.printUsers();
		if ( this.leftTree != null ) 
			this.leftTree.printUsers();
	}
	
	/*
	 * Getters e Setters
	 */
	private User getUser() {return this.root.getUser();}
	private String getUserName() {return this.root.getUser().getName();}
	private String getUserId() {return this.root.getUser().getId();}
	public String getUserDomain() {return this.root.getUser().getDomain();}
	public String getUserEmail() {return this.root.getUser().getEmail();}
	private String getUserRole() {return this.root.getUser().getRole();}
	
	public void setRightTree(Tree rightTree) {this.rightTree = rightTree;}
	public void setLeftTree(Tree leftTree) {this.leftTree = leftTree;}
	public void setRoot(No root) {this.root = root;}
}