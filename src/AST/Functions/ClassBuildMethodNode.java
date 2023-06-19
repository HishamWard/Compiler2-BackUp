package AST.Functions;

import AST.BlockNode;
import AST.ExpressionNode;
import AST.Node;
import AST.Widgets.WidgetNode;
import Visitors.ASTVisitor;

import java.util.LinkedList;

public class ClassBuildMethodNode extends ExpressionNode {
    public final String returnType;
    public final String name;
    public final boolean override;

    public ClassBuildMethodNode(String returnType, String name, FunctionParameterNode parameter, boolean override, WidgetNode widget) {
        this.returnType = returnType;
        this.name = name;
        this.override = override;

        this.addChild(parameter);
        this.addChild(widget);
    }

    @Override
    public void accept(ASTVisitor visitor) {

    }

    @Override
    protected String nodeInfo() {
        return String.format("(override) %s|{%s|%s}", this.getClass().getSimpleName(), this.returnType, this.name);
    }
}
