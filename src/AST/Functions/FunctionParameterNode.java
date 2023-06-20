package AST.Functions;

import AST.ExpressionNode;
import Visitors.AST2HTML;

public class FunctionParameterNode extends ExpressionNode {
    String name;
    String type;
    public FunctionParameterNode(String name, String type) {
        this.name = name;
        this.type = type;
    }
    @Override
    protected String nodeInfo() {
        return String.format("%s|{%s|%s}", this.getClass().getSimpleName(), this.type, this.name);
    }

    @Override
    public void accept(AST2HTML visitor) {

    }
}
