package AST.Widgets;

import Visitors.ASTVisitor;


public class NavigatorNode extends WidgetNode {
    public String context, argument,targetWidget;

    public NavigatorNode(String context, String argument,String targetWidget) {
        this.context = context;
        this.argument = argument;
        this.targetWidget = targetWidget;
    }

    @Override
    public void accept(ASTVisitor visitor) {
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s|{context: %s|argument: %s|target: %s}", this.getClass().getSimpleName(), context, argument,targetWidget);
    }
}
