package AST.Variables;

import AST.ExpressionNode;
import AST.Node;
import Visitors.AST2HTML;

public class VariableAssignNode extends ExpressionNode {
    public Node left;
    public Node right;

    public String name;

    public VariableAssignNode(Node left, Node right, String name) {
        this.left = left;
        this.right = right;
        this.name = name;
        this.addChild(left);
        this.addChild(right);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s|%s", this.getClass().getSimpleName(), name);
    }

    @Override
    public void accept(AST2HTML visitor) {

    }
}
