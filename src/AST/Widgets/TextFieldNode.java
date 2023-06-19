package AST.Widgets;

import Visitors.ASTVisitor;

public class TextFieldNode extends WidgetNode{
    public String label = "";
    public String controllerString;
    public TextFieldNode(String controllerString){
        this.controllerString = controllerString;
    }

    public TextFieldNode(String controllerString, String label){
        this.controllerString = controllerString;
        this.label = label;
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
