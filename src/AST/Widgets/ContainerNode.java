package AST.Widgets;

import Visitors.AST2HTML;

public class ContainerNode  extends WidgetNode  {
    public float width;
    public float height;
    public float[] margin;
    public float[] padding;
    public String backgroundColor = "white";

    public ContainerNode(WidgetNode child) {
        this.addChild(child);
    }

    public ContainerNode(float width, float height, WidgetNode child) {
        this.width = width;
        this.height = height;
        this.margin = new float[]{0, 0, 0, 0};
        this.padding = new float[]{0, 0, 0, 0};

        this.addChild(child);
    }

    public ContainerNode(float width, float height, WidgetNode child, float[] margin, float[] padding, String backgroundColor) {
        this.width = width;
        this.height = height;
        this.margin = margin == null? new float[]{0, 0, 0, 0} : margin;
        this.padding = padding == null? new float[]{0, 0, 0, 0} : padding;
        this.backgroundColor = backgroundColor;

        this.addChild(child);
    }

    @Override
    public void accept(AST2HTML visitor) {
        visitor.generateContainer(this);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s|{{width: %s|height: %s}|margin: [%s,%s,%s,%s]|padding: [%s,%s,%s,%s]}",
                this.getClass().getSimpleName(),
                width, height,
                margin[0], margin[1], margin[2], margin[3],
                padding[0], padding[1], padding[2], padding[3]);
    }
}
