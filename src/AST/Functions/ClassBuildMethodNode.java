package AST.Functions;

import AST.ExpressionNode;
import AST.Widgets.WidgetNode;
import Visitors.AST2HTML;

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
    public void accept(AST2HTML visitor) {

    }

    @Override
    protected String nodeInfo() {
        return String.format("(override) %s|{%s|%s}", this.getClass().getSimpleName(), this.returnType, this.name);
    }
}
