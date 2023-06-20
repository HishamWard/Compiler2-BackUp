package AST.Widgets;

import AST.Node;
import Visitors.AST2HTML;

public abstract class WidgetNode extends Node {

    public abstract void accept(AST2HTML visitor);
}
