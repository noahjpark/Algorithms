/* Noah Park

Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that represents this expression.

Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example, the postfix tokens of the expression 4*(5-(2+7)) are represented in the array postfix = ["4","5","7","2","+","-","*"].

The class Node is an interface you should use to implement the binary expression tree. The returned tree will be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove the Node class; however, you can modify it as you wish, and you can define other classes to implement it if needed.

A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).

It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations are valid (i.e., no division by zero).

Follow up: Could you design the expression tree such that it is more modular? For example, is your design able to support additional operators without making changes to your existing evaluate implementation?

*/

/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
    public Node left;
	public Node right;
	public String data;
};

class TreeNode extends Node {
	public TreeNode(String data) {
		left = null;
		right = null;
		this.data = data;
	}

	public int evaluate() {
		if(left == null && right == null) return Integer.parseInt(data);
		if (data.equals("/")) return left.evaluate() / right.evaluate();
		else if (data.equals("*")) return left.evaluate() * right.evaluate();
	  else if (data.equals("+")) return left.evaluate() + right.evaluate();
		return left.evaluate() - right.evaluate();
	}
};


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Stack<TreeNode> stack = new Stack<>();
		for(String exp : postfix){
			TreeNode cur = new TreeNode(exp);
			if (exp.equals("/") || exp.equals("+") || exp.equals("-") || exp.equals("*")) {
				cur.right = stack.pop();
				cur.left = stack.pop();
			}
			stack.push(cur);
		}
		return stack.peek();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */
