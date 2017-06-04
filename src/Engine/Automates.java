package Engine;

public class Automates {
	private Node root;
	private Node lastExecuted;
	private Node lastAccolade;

	/*
	 * inner class
	 * 
	 */
	private class Node {
		private Node left;
		private Node right;
		private Operateurs data;

		public Node(Operateurs data) {
			this.left = null;
			this.right = null;
			this.data = data;
		}

	}

	public Automates() {
		root = null;
	}

	public void buildTree(Node node, Operateurs data) {
		if (root == null) {
			root = new Node(data);
		} else {
			if (node.left == null) {
				node.left = new Node(data);
			} else if (node.left != null && node.right == null) {
				node.right = new Node(data);
			} else {
				System.out.println("ce node est complet");
			}
		}
	}

	public void preOrder(Node p) {
		if (p != null) {
			System.out.println(p.data);
			preOrder(p.left);
			preOrder(p.right);
		}
	}

	public void do_fonction(Node p) {
		// data.excuter();
	}

	public String toString() {
		String retour = "";
		if (root.left != null)
			retour += root.left.toString();
		retour += root.data.toString();
		if (root.right != null)
			retour += root.right.toString();

		return retour;
	}
}
