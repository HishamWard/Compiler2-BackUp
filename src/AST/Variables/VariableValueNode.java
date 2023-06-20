package AST.Variables;

import AST.Node;
import Visitors.AST2HTML;

public class VariableValueNode extends Node {
    String type;
    String value;

    public VariableValueNode(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public VariableValueNode(String type, Node child) {
        this.type = type;
        addChild(child);
    }

    public String getType() {
        return type;
    }

    @Override
    public void accept(AST2HTML visitor) {

    }

    protected String nodeInfo() {
        return String.format("%s|{Type: %s|Value: %s}", this.getClass().getSimpleName(), type, value == null ? null : value.replaceAll("\"", ""));
    }

    public String getValue() {
        return value;
    }
}
