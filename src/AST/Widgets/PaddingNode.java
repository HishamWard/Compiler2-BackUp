package AST.Widgets;

import Visitors.ASTVisitor;

public class PaddingNode extends WidgetNode {
    public float[] padding;

    public PaddingNode(WidgetNode child, float[] padding) {
        this.padding = padding == null? new float[]{0, 0, 0, 0} : padding;
        this.addChild(child);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitPadding(this);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s | {padding: [%s,%s,%s,%s]}",
                this.getClass().getSimpleName(),
                padding[0], padding[1], padding[2], padding[3]);
    }
}
