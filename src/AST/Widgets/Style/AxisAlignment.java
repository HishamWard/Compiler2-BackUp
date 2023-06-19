package AST.Widgets.Style;

import Types.Type;

public enum AxisAlignment {
    START("start"),
    END("end"),
    CENTER("center"),
    SPACE_EVENLY("space-evenly"),
    SPACE_AROUND("space-around"),
    SPACE_BETWEEN("space-between");

    private String name;

    private AxisAlignment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static AxisAlignment getAxisAlignmentType(String axisName) {
        switch (axisName) {
            case "center":
                return AxisAlignment.CENTER;
            case "start":
                return AxisAlignment.START;
            case "end":
                return AxisAlignment.END;
            case "space-evenly":
                return AxisAlignment.SPACE_EVENLY;
            case "space-around":
                return AxisAlignment.SPACE_AROUND;
            case "space-between":
                return AxisAlignment.SPACE_BETWEEN;
            default:
                return AxisAlignment.CENTER;
        }
    }
}
