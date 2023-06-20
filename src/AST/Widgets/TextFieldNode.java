package AST.Widgets;

import Visitors.AST2HTML;

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
    public void accept(AST2HTML visitor) {
        visitor.generateTextField(this);
    }
    @Override
    protected String nodeInfo() {
        return String.format("%s | controller: %s", this.getClass().getSimpleName(), controllerString);
    }
}
