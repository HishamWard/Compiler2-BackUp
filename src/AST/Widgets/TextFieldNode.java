package AST.Widgets;

import Visitors.ASTVisitor;

public class TextFieldNode extends WidgetNode{
    String controllerString;
    public TextFieldNode(String controllerString){
        this.controllerString = controllerString;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitTextField(this);
    }
    @Override
    protected String nodeInfo() {
        return String.format("%s | controller: %s", this.getClass().getSimpleName(), controllerString);
    }
}
