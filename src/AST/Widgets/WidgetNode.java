package AST.Widgets;

import AST.Node;
import Visitors.ASTVisitor;

public abstract class WidgetNode extends Node {

    public abstract void accept(ASTVisitor visitor);
}
