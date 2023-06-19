package AST;

import Visitors.ASTVisitor;
import java.util.LinkedList;

public class ClassDeclarationNode extends ExpressionNode {
    public String name;
    public int scope;
    String baseClassName = null;

    public ClassDeclarationNode(String name, LinkedList<Node> attributes, LinkedList<Node> functions, LinkedList<Node> functionCalls, int scope) {
        this.name = name;
        this.scope = scope;
        this.addChildren(attributes);
        this.addChildren(functions);
        this.addChildren(functionCalls);
    }

    public ClassDeclarationNode(String name, LinkedList<Node> attributes, LinkedList<Node> functions, LinkedList<Node> functionCalls, String baseClassName, int scope) {
        this.name = name;
        this.baseClassName = baseClassName;
        this.scope = scope;

        this.addChildren(attributes);
        this.addChildren(functions);
        this.addChildren(functionCalls);
    }

    protected String nodeInfo() {
        if (baseClassName != null)
            return String.format("{%s | %s | Base Class: %s} | scope: %d", this.getClass().getSimpleName(), name, baseClassName, scope);
        else
            return String.format("{%s | %s} | scope: %d", this.getClass().getSimpleName(), name, scope);
    }

    @Override
    public void accept(ASTVisitor visitor) {

    }
}
