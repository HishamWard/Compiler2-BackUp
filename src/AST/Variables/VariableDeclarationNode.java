package AST.Variables;

import AST.Node;
import Types.Type;
import Visitors.ASTVisitor;

public class VariableDeclarationNode extends Node {
	public Integer position;
	public int scope;
	public Type type;
	public String name;

	public Node initializer;

	public VariableDeclarationNode(Integer scope,
								   Type type, String name, Node initializer, Integer position){
		this.scope = scope;
		this.type = type;
		this.name = name;
		this.initializer = initializer;
		this.position = position;

		this.addChild(initializer);
	}
	protected String nodeInfo() {
		return String.format("{%s|%s|%s}|{scope: %d|line: %d}", this.getClass().getSimpleName(), type, name, scope, position);
	}

	@Override
	public void accept(ASTVisitor visitor) {

	}
}
