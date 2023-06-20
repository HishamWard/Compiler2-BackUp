package AST.Widgets;

import Visitors.AST2HTML;

public class ScaffoldNode extends WidgetNode{
    public ScaffoldNode(WidgetNode body){
        this.addChild(body);
    }
    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateScaffold(this);
    }
    @Override
    protected String nodeInfo() {
        return String.format("%s", this.getClass().getSimpleName());
    }
}
