package AST.Widgets;

import AST.Node;
import Visitors.ASTVisitor;

public class CenterNode extends WidgetNode {
    public CenterNode(WidgetNode child) {
           this.addChild(child);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitCenter(this);
    }
}
