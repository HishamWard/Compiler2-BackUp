package AST.Widgets;

import Visitors.ASTVisitor;

public class SizedBoxNode extends WidgetNode {
    public float width;
    public float height;

    public SizedBoxNode(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitSizedBox(this);
    }
}
