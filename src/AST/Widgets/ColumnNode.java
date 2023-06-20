package AST.Widgets;

import AST.Widgets.Style.AxisAlignment;
import Visitors.AST2HTML;

import java.util.List;


public class ColumnNode extends WidgetNode {
    public AxisAlignment mainAxisAlignment;
    public AxisAlignment crossAxisAlignment;

    public ColumnNode(List<WidgetNode> children, AxisAlignment mainAxisAlignment, AxisAlignment crossAxisAlignment) {
        for (WidgetNode child: children){
            this.addChild(child);
        }
        this.mainAxisAlignment = mainAxisAlignment;
        this.crossAxisAlignment = crossAxisAlignment;
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateColumn(this);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s | {Main: %s | Cross: %s}", this.getClass().getSimpleName(), mainAxisAlignment, crossAxisAlignment);
    }
}
