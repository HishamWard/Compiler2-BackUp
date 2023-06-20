package AST.Widgets;

import AST.BlockNode;
import Visitors.AST2HTML;

import java.util.HashMap;
import java.util.Map;

public class ButtonNode extends WidgetNode {
    public String href = "";
    public boolean insertJS = false;
    public Map<String, String> argumentsToPassForWidget = new HashMap<>();

    public ButtonNode(WidgetNode child, BlockNode block) {
        this.addChild(child);
        this.addChild(block);
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateButton(this);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s | onPressed", this.getClass().getSimpleName());
    }
}
