package AST.Functions;

import AST.ExpressionNode;
import AST.Node;
import Visitors.ASTVisitor;

import java.util.LinkedList;

public class FunctionCallNode extends ExpressionNode {
    public String name;

    public FunctionCallNode(String name, LinkedList<Node> args) {
        this.name = name;
        args.forEach(arg -> this.addChild(arg));
    }

    protected String nodeInfo() {
        return String.format("%s|%s", this.getClass().getSimpleName(), this.name);
    }

    @Override
    public void accept(ASTVisitor visitor) {

    }
}
