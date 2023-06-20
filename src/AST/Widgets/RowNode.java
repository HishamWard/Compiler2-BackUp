package AST.Widgets;

import AST.Widgets.Style.AxisAlignment;
import Visitors.AST2HTML;

import java.util.List;


public class RowNode extends WidgetNode {
    public AxisAlignment mainAxisAlignment;
    public AxisAlignment crossAxisAlignment;

    public RowNode(List<WidgetNode> children, AxisAlignment mainAxisAlignment, AxisAlignment crossAxisAlignment) {
        for (WidgetNode child: children){
            this.addChild(child);
        }
        this.mainAxisAlignment = mainAxisAlignment;
        this.crossAxisAlignment = crossAxisAlignment;
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateRow(this);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s | {Main: %s | Cross: %s}", this.getClass().getSimpleName(), mainAxisAlignment, crossAxisAlignment);
    }
}
