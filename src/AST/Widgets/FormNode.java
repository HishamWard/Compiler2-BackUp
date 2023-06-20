package AST.Widgets;

import Visitors.AST2HTML;

public class FormNode extends WidgetNode {
    public FormNode(WidgetNode child) {
           this.addChild(child);
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateForm(this);
    }
}
