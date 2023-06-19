package AST.Widgets;

import Visitors.ASTVisitor;

public class ScaffoldNode extends WidgetNode{
    public ScaffoldNode(WidgetNode body){
        this.addChild(body);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitScaffold(this);
    }
    @Override
    protected String nodeInfo() {
        return String.format("%s", this.getClass().getSimpleName());
    }
}
