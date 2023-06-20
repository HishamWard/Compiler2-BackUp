package AST.Functions;

import AST.BlockNode;
import AST.ExpressionNode;
import Visitors.AST2HTML;

import java.util.LinkedList;

public class FunctionDeclarationNode extends ExpressionNode {
    public final String returnType;
    public final String name;
    public final boolean override;
    public int scope;


    public FunctionDeclarationNode(String returnType, String name, LinkedList<FunctionParameterNode> parameters, boolean override, BlockNode body, Integer scope) {
        this.returnType = returnType;
        this.name = name;
        this.override = override;
        this.scope = scope;


        parameters.forEach((param) -> this.addChild(param));
        this.addChild(body);
    }

    @Override
    public void accept(AST2HTML visitor) {

    }

    @Override
    protected String nodeInfo() {
        if (override)
            return String.format("(override) %s|{%s|%s} | {scope: %d}", this.getClass().getSimpleName(), this.returnType, this.name, scope);
        else
            return String.format("%s | {%s | %s} | {scope: %d}", this.getClass().getSimpleName(), this.returnType, this.name, scope);
    }
}
