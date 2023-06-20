package AST.Widgets;

import Visitors.AST2HTML;

public class CenterNode extends WidgetNode {
    public CenterNode(WidgetNode child) {
           this.addChild(child);
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateCenter(this);
    }
}
