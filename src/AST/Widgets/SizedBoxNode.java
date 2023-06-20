package AST.Widgets;

import Visitors.AST2HTML;

public class SizedBoxNode extends WidgetNode {
    public float width;
    public float height;

    public SizedBoxNode(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateSizedBox(this);
    }
}
