package Visitors;

import AST.Node;
import AST.Widgets.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class ASTVisitor {
    private FileWriter writer;

    public ASTVisitor(FileWriter writer) {
        this.writer = writer;
    }

    public void visit(Node node) {
        node.accept(this);
    }

    public void visitButton(ButtonNode button) {
        try {
            if (button.insertJS) {
                String onClick = "this.closest('form').action='" + button.href + ".php'";
                System.out.println(onClick);
                writer.write("<button onclick=\"" + onClick + "\">");
                button.getChild(0).accept(this);
                writer.write("</button>");
            }
            else if (!button.href.equals("")) {
//                writer.write("<form method='POST' action='/" + button.href + ".php" + "'>");
//                writer.write("<button type='submit'>");
//                button.getChild(0).accept(this);
//                writer.write("</button>");
//
//                for (Map.Entry<String, String> entry : button.argumentsToPassForWidget.entrySet()) {
//                    String key = entry.getKey();
//
//                    // Strip value from quotes
//                    String value = entry.getValue().replace("\"", "").replace("'", "");
//                    String inputString = String.format("<input type='hidden' name='%s' value='%s' />", key, value);
//                    writer.write(inputString);
//                }
//
//                writer.write("</form>");

                String urlParams = "";
                for (Map.Entry<String, String> entry : button.argumentsToPassForWidget.entrySet()) {
                    String key = entry.getKey();

                    // Strip value from quotes
                    String value = entry.getValue().replace("\"", "").replace("'", "");
                    urlParams += key + "=" + value + "&";
                }

                String href = "/" + button.href + ".php?" + urlParams;
                writer.write("<a href='" + href + "'>");
                button.getChild(0).accept(this);
                writer.write("</a>");
            } else {
                writer.write("<button>");
                button.getChild(0).accept(this);
                writer.write("</button>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitScaffold(ScaffoldNode scaffoldNode) {
        try {
            writer.write("<div style='min-height: 100vh'>");
            scaffoldNode.getChild(0).accept(this);
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitSizedBox(SizedBoxNode sizedBox) {
        String widthText = "";
        String heightText = "";

        if (sizedBox.width != 0.0f) widthText = "width: " + sizedBox.width + "px; ";
        if (sizedBox.height != 0.0f) heightText = "height: " + sizedBox.height + "px; ";

        try {
            writer.write("<div style='" + widthText + heightText + " '>");

            if (sizedBox.children.size() > 0) {
                sizedBox.getChild(0).accept(this);
            }
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitContainer(ContainerNode container) {
        String paddingText = "";
        String marginText = "";
        String heightText = "";
        String widthText = "";
        String colorText = "";

        if (container.margin != null) {
            marginText = String.format("margin: %spx %spx %spx %spx", container.margin[1], container.margin[2], container.margin[3], container.margin[0]);
            marginText += "; ";
        }

        if (container.padding != null) {
            paddingText = String.format("padding: %spx %spx %spx %spx", container.padding[1], container.padding[2], container.padding[3], container.padding[0]);
            paddingText += "; ";
        }

        if (container.width != 0.0f) {
            widthText = "width: " + container.width + "px;";
        }

        if (container.height != 0.0f) {
            heightText = "height: " + container.height + "px;";
        }
        if (container.backgroundColor != null) {
            colorText = "background-color: " + container.backgroundColor;
        }

        try {
            writer.write("<div style='" + marginText + paddingText + widthText + heightText + colorText + " '>");
            container.getChild(0).accept(this);
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitPadding(PaddingNode padding) {
        try {
            String paddingText = String.format("padding: %spx %spx %spx %spx", padding.padding[1], padding.padding[2], padding.padding[3], padding.padding[0]);
            writer.write("<div style='" + paddingText + "'>");
            padding.getChild(0).accept(this);
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitRow(RowNode row) {
        String alignmentString = "";
        if (row.mainAxisAlignment != null) {
            alignmentString += "justify-content: " + row.mainAxisAlignment.getName() + "; ";
        }

        if (row.crossAxisAlignment != null) {
            alignmentString += "align-items: " + row.crossAxisAlignment.getName() + "; ";
        }

        try {
            writer.write("<div style='display: flex; " + alignmentString + "'>");
            row.children.forEach(child -> {
                child.accept(this);
            });
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitColumn(ColumnNode column) {
        try {
            String alignmentString = "";
            if (column.mainAxisAlignment != null) {
                alignmentString += "align-items: " + column.mainAxisAlignment.getName() + "; ";
            }

            if (column.crossAxisAlignment != null) {
                alignmentString += "justify-content: " + column.crossAxisAlignment.getName() + "; ";
            }

            writer.write("<div style='display: flex; flex-direction: column; " + alignmentString + "'>");
            column.children.forEach(child -> {
                child.accept(this);
            });
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitCenter(CenterNode center) {
        try {
            writer.write("<div style='display: flex; justify-content: center; align-items: center'>");
            center.getChild(0).accept(this);
            writer.write("</div>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitText(TextNode node) {
        try {
            String color = node.style.color;
            double fontSize = node.style.fontSize;
            double fontWeight = node.style.fontWeight;

            String styleText = "font-size: " + fontSize + "px; font-weight: " + fontWeight + ";";
            if (!color.equals("")) styleText += "color: " + color + "; ";

            writer.write("<p style='" + styleText + "'>");
            writer.write(node.text);
            writer.write("</p>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitTextField(TextFieldNode textField) {
        try {

            String inputText = "<input type='text'";
            if (!textField.label.equals("")) inputText += String.format("name='%s' placeholder='%s' />", textField.label, textField.label);
            else inputText += " />";

            writer.write(inputText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visitForm(FormNode form) {
        try {
            writer.write("<form method='POST' action='MyApp.php'>");
            form.getChild(0).accept(this);
            writer.write("</form>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
