package AST;

import AST.Widgets.WidgetNode;
import Visitors.AST2HTML;

import java.util.HashMap;
import java.util.Map;

public class ProgramNode extends Node {
    public final Map<String, WidgetNode> widgetNodesMap = new HashMap<>();
    @Override
    public void accept(AST2HTML visitor) {

    }

    public void addWidget(String name, WidgetNode widget) {
        widgetNodesMap.put(name, widget);
        addChild(widget);
    }
}
