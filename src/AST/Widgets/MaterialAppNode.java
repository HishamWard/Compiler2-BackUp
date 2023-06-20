package AST.Widgets;

import Visitors.AST2HTML;

public class MaterialAppNode extends WidgetNode{
    public String title;
    public String home;
    public MaterialAppNode(String title, String home){
        this.home = home;
        this.title = title;
    }
    @Override
    public void accept(AST2HTML visitor) {

    }
    @Override
    protected String nodeInfo() {
        return String.format("%s | {title: %s | home: %s}", this.getClass().getSimpleName(), title, home);
    }
}
