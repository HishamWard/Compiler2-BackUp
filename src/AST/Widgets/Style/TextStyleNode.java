package AST.Widgets.Style;

import AST.Widgets.WidgetNode;
import Visitors.ASTVisitor;

public class TextStyleNode extends WidgetNode {
    public String color = "";
    public double fontSize = 16;
    public double fontWeight = 400;

    public TextStyleNode() {}

    public TextStyleNode(String color, double fontSize) {
        this.color = color;
        this.fontSize = fontSize;
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s|{color: %s|size: %s}", this.getClass().getSimpleName(),color,fontSize);
    }

    @Override
    public void accept(ASTVisitor visitor) {

    }
}
