package AST.Widgets;

import AST.Widgets.Style.TextStyleNode;
import Visitors.AST2HTML;

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
    public void accept(AST2HTML visitor) {
        visitor.generateText(this);
    }

    @Override
    protected String nodeInfo() {
        if (style != null)
            return String.format("%s|text: %s |{ style | {color: %s|size: %s}}", this.getClass().getSimpleName(),text,style.color,style.fontSize);
        else
            return String.format("%s|{text: %s}", this.getClass().getSimpleName(),text);
    }
}
