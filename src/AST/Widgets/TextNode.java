package AST.Widgets;

import AST.Widgets.Style.TextStyleNode;
import Visitors.ASTVisitor;

public class TextNode extends WidgetNode {
    public final String text;
    public TextStyleNode style;

    public TextNode(String text, TextStyleNode style) {
        this.text = text;
        this.style = style;
    }

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitText(this);
    }

    @Override
    protected String nodeInfo() {
        if (style != null)
            return String.format("%s|text: %s |{ style | {color: %s|size: %s}}", this.getClass().getSimpleName(),text,style.color,style.fontSize);
        else
            return String.format("%s|{text: %s}", this.getClass().getSimpleName(),text);
    }
}
