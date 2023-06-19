package AST.Widgets;

import Visitors.ASTVisitor;

public class FormNode extends WidgetNode {
    public FormNode(WidgetNode child) {
           this.addChild(child);
    }

    @Override
    public void accept(ASTVisitor visitor) {
//        visitor.visitForm(this);
    }
}
