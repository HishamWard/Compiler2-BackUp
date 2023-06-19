package Visitors;

import AST.BlockNode;
import AST.ClassDeclarationNode;
import AST.Functions.ClassBuildMethodNode;
import AST.Functions.FunctionCallNode;
import AST.Functions.FunctionDeclarationNode;
import AST.Functions.FunctionParameterNode;
import AST.Node;
import AST.ProgramNode;
import AST.Variables.VariableAssignNode;
import AST.Variables.VariableDeclarationNode;
import AST.Variables.VariableValueNode;
import AST.Widgets.*;
import AST.Widgets.Style.AxisAlignment;
import AST.Widgets.Style.TextStyleNode;
import Grammer.Flotter;
import Grammer.FlotterBaseVisitor;
import Symbol.SymbolTable;
import Types.Type;

import java.util.*;

public class ParseTreeVisitor extends FlotterBaseVisitor {
    final Stack<SymbolTable> symbolTables = new Stack<SymbolTable>();

    public ProgramNode visitProgram(Flotter.ProgramContext ctx) {
        symbolTables.push(new SymbolTable());

        ProgramNode programNode = new ProgramNode();

        for (int i = 0; i < ctx.importStatement().size(); i++) {
            programNode.addChild((Node) visit(ctx.importStatement(i)));
        }

        for (int i = 0; i < ctx.expression().size(); i++) {
            boolean doNotVisitExpressionAgain = false;
            // Visit all class declarations in program
            if (ctx.expression(i).class_declaration() != null) {

                // Get all class declarations
                List<Flotter.Class_member_declarationContext> classMemberDeclarations = ctx.expression(i).class_declaration().class_member_declaration();

                // Find the build method between these declarations. Note that only one build method should be provided.
                Flotter.Class_build_methodContext buildMethod = null;
                for (int j = 0; j < classMemberDeclarations.size(); j++) {
                    if (classMemberDeclarations.get(j).class_build_method() != null)
                        buildMethod = classMemberDeclarations.get(j).class_build_method();
                }

                // If a build method was provided, CONGRATS! This is a widget.
                if (buildMethod != null) {
                    doNotVisitExpressionAgain = true;
                    WidgetNode widgetNode = (WidgetNode) visitWidget(buildMethod.widget());

                    // This will also be the name of the generated HTML file.
                    String className = ctx.expression(i).class_declaration().IDENTIFIER(0).getText();
                    programNode.addWidget(className, widgetNode);
                }
            }

            // TODO: There might be some duplicate visits spread here and in the loop above.
            // This is a temporary fix but I think I might just know the solution.
            if (!doNotVisitExpressionAgain) {
                Node node = (Node) visitExpression(ctx.expression(i));
                programNode.addChild(node);
            }
        }
        return programNode;
    }

    @Override
    public Object visitExpression(Flotter.ExpressionContext ctx) {
        if (ctx.function_call() != null) {
            return visit(ctx.function_call());
        }
        //TODO why is this like this?
//        if (ctx.arith() != null) {
//            return visit(ctx.arith());
//        }
        return super.visitExpression(ctx);
    }

    @Override
    public Object visitWidgetExpression(Flotter.WidgetExpressionContext ctx) {
        return visit(ctx.widget());
    }

    @Override
    public ContainerNode visitContainer(Flotter.ContainerContext ctx) {
        float width = 0, height = 0, margin[] = null, padding[] = null;
        String color = "white";
        WidgetNode child = null;
        for (Flotter.ContainerPropertyContext propertyContext : ctx.containerProperties().containerProperty()) {
            if (propertyContext.width() != null) {
                width = Float.parseFloat(propertyContext.width().NUMBER().getText());
            }
            if (propertyContext.height() != null) {
                height = Float.parseFloat(propertyContext.height().NUMBER().getText());
            }
            if (propertyContext.margin() != null) {
                margin = new float[propertyContext.margin().edgeInsetsOnly().edgeInsetsParams().NUMBER().size()];
                for (int i = 0; i < margin.length; i++) {
                    margin[i] = Float.parseFloat(propertyContext.margin().edgeInsetsOnly().edgeInsetsParams().NUMBER(i).getText());
                }
            }
            if (propertyContext.padding() != null) {
                padding = new float[propertyContext.padding().edgeInsetsOnly().edgeInsetsParams().NUMBER().size()];
                for (int i = 0; i < padding.length; i++) {
                    padding[i] = Float.parseFloat(propertyContext.padding().edgeInsetsOnly().edgeInsetsParams().NUMBER(i).getText());
                }
            }
            if (propertyContext.color() != null) {
                color = propertyContext.color().colorName.getText().toLowerCase();
            }
            if (propertyContext.child() != null) {
                child = (WidgetNode) visit(propertyContext.child());
            }
        }
        return new ContainerNode(width, height, child, margin, padding, color);
    }

    @Override
    public RowNode visitRow(Flotter.RowContext ctx) {
        List<WidgetNode> children = new ArrayList<>();
        String mainAxisString = "", crossAxisString = "";
        for (Flotter.RowColumnPropertyContext propertyContext : ctx.rowColumnProperties().rowColumnProperty()) {
            if (propertyContext.mainAxisAlignment() != null) {
                mainAxisString = propertyContext.mainAxisAlignment().mainCrossAxisAlignmentProperty().getText();
            }
            if (propertyContext.crossAxisAlignment() != null) {
                crossAxisString = propertyContext.crossAxisAlignment().mainCrossAxisAlignmentProperty().getText();
            }
            if (propertyContext.children() != null) {
                for (Flotter.WidgetContext childWidget : propertyContext.children().widget())
                    children.add((WidgetNode) visit(childWidget));
            }
        }
        return new RowNode(children, AxisAlignment.getAxisAlignmentType(mainAxisString), AxisAlignment.getAxisAlignmentType(crossAxisString));
    }

    @Override
    public ColumnNode visitColumn(Flotter.ColumnContext ctx) {
        List<WidgetNode> children = new ArrayList<>();
        String mainAxisString = "", crossAxisString = "";
        for (Flotter.RowColumnPropertyContext propertyContext : ctx.rowColumnProperties().rowColumnProperty()) {
            if (propertyContext.mainAxisAlignment() != null) {
                mainAxisString = propertyContext.mainAxisAlignment().mainCrossAxisAlignmentProperty().getText();
            }
            if (propertyContext.crossAxisAlignment() != null) {
                crossAxisString = propertyContext.crossAxisAlignment().mainCrossAxisAlignmentProperty().getText();
            }
            if (propertyContext.children() != null) {
                for (Flotter.WidgetContext childWidget : propertyContext.children().widget())
                    children.add((WidgetNode) visit(childWidget));
            }
        }
        return new ColumnNode(children, AxisAlignment.getAxisAlignmentType(mainAxisString), AxisAlignment.getAxisAlignmentType(crossAxisString));
    }

    public TextNode visitText(Flotter.TextContext ctx) {
        String text = "";
        TextStyleNode style = new TextStyleNode();

        for (Flotter.TextPropertyContext TextProperty : ctx.textProperties().textProperty()) {
            if (TextProperty.SingleLineString() != null) {
                text = TextProperty.SingleLineString().getText();
            } else if (TextProperty.textStyle() != null) {
                style = (TextStyleNode) visit(TextProperty.textStyle());
            }
        }

        String textWithNoQuotes = text.replace("\"", "").replace("'", "");
        return new TextNode(textWithNoQuotes, style);
    }

    @Override
    public TextStyleNode visitTextStyle(Flotter.TextStyleContext ctx) {
        TextStyleNode style = new TextStyleNode();

        for (Flotter.TextStylePropertyContext textStyleProperty : ctx.textStyleProperties().textStyleProperty()) {
            if (textStyleProperty.color() != null) {
                style.color = textStyleProperty.color().colorName.getText().toLowerCase();
            }
            if (textStyleProperty.fontWeight() != null) {
                style.fontWeight = Float.parseFloat(textStyleProperty.fontWeight().NUMBER().getText());
            }
            if (textStyleProperty.fontSize() != null) {
                style.fontSize = Float.parseFloat(textStyleProperty.fontSize().NUMBER().getText());
            }
        }

        return style;
    }

    @Override
    public ButtonNode visitElevatedButton(Flotter.ElevatedButtonContext ctx) {
        WidgetNode child = null;
        BlockNode block = null;
        String href = "";
        Map<String, String> argumentsToPassForWidget = new HashMap<>();

        for (Flotter.ElevatedButtonPropertyContext propertyContext : ctx.elevatedButtonProperties().elevatedButtonProperty()) {
            if (propertyContext.child() != null) {
                child = (WidgetNode) visit(propertyContext.child());
            }
            if (propertyContext.onPressed() != null) {
                if (propertyContext.onPressed().block() != null) {
                    block = (BlockNode) visit(propertyContext.onPressed().block());

                    // I'm sorry for this mess but this is the only way since navigator is considered a widget
                    for (Flotter.ExpressionContext e : propertyContext.onPressed().block().expression()) {
                        if (e.widgetExpression() != null) {
                            if (e.widgetExpression().widget().navigator() != null) {
                                Flotter.Function_callContext functionCallContext = e.widgetExpression().widget().navigator().materialpageroute().function_call();
                                // The name of the function call (i.e. name of the Material Page widget) will be the name
                                // of the widget that we want to transition to.
                                href = functionCallContext.IDENTIFIER().getText();

                                // Collect named arguments to pass for new widget
                                if(functionCallContext.function_call_named_arguments() != null) {
                                    functionCallContext.function_call_named_arguments().function_call_named_argument().forEach(i -> {
                                        String key = i.IDENTIFIER().getText();
                                        String value = i.variable_value().getText();
                                        argumentsToPassForWidget.put(key, value);
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }

        ButtonNode button = new ButtonNode(child, block);
        if(argumentsToPassForWidget != null) {
            button.argumentsToPassForWidget = argumentsToPassForWidget;
        }
        if (!href.equals("")) button.href = href;

        return button;
    }

    @Override
    public BlockNode visitBlock(Flotter.BlockContext ctx) {
        symbolTables.push(symbolTables.peek().clone());

        LinkedList<Node> expressionNodes = new LinkedList<>();
        for (Flotter.ExpressionContext propertyContext : ctx.expression()) {
            expressionNodes.add((Node) visit(propertyContext));
        }

        int firstLineNum = ctx.getStart().getLine();
        int lastLineNum = ctx.getStop().getLine();

        symbolTables.pop();
        return new BlockNode(firstLineNum, lastLineNum, expressionNodes);
    }

    @Override
    public PaddingNode visitPaddingWidget(Flotter.PaddingWidgetContext ctx) {
        float padding[] = null;
        WidgetNode child = null;
        for (Flotter.PaddingPropertyContext propertyContext : ctx.paddingProperties().paddingProperty()) {
            if (propertyContext.padding() != null) {
                padding = new float[propertyContext.padding().edgeInsetsOnly().edgeInsetsParams().NUMBER().size()];
                for (int i = 0; i < padding.length; i++) {
                    padding[i] = Float.parseFloat(propertyContext.padding().edgeInsetsOnly().edgeInsetsParams().NUMBER(i).getText());
                }
            }
            if (propertyContext.child() != null) {
                child = (WidgetNode) visit(propertyContext.child());
            }
        }
        return new PaddingNode(child, padding);
    }

    @Override
    public TextFieldNode visitTextField(Flotter.TextFieldContext ctx) {
        TextFieldNode node = new TextFieldNode("");


        for (Flotter.TextFieldPropertyContext propertyContext : ctx.textFieldProperties().textFieldProperty()) {
            if (propertyContext.controller() != null) {
                node.controllerString = propertyContext.controller().IDENTIFIER().getText();
            }

            else if (propertyContext.textFieldDecoration() != null) {
                node.label = propertyContext.textFieldDecoration().inputDecoration().IDENTIFIER().getText();
            }
        }

        return node;
    }

    @Override
    public MaterialAppNode visitMaterialApp(Flotter.MaterialAppContext ctx) {
        String title = null;
        String home = null;
        for (Flotter.MaterialAppPropertyContext propertyContext : ctx.materialAppProperties().materialAppProperty()) {
            if (propertyContext.title() != null) {
                title = propertyContext.title().SingleLineString().getText();
            }
            if (propertyContext.home() != null) {
                home = propertyContext.home().function_call().getText();
            }
        }
        return new MaterialAppNode(title, home);
    }

    @Override
    public FormNode visitForm(Flotter.FormContext ctx) {
        WidgetNode child = null;

        if (ctx.formProperty().child() != null) {
            child = (WidgetNode) visit(ctx.formProperty().child());
        }
        return new FormNode(child);
    }

    @Override
    public Object visitScaffold(Flotter.ScaffoldContext ctx) {
        WidgetNode body = null;
        for (Flotter.ScaffoldPropertyContext propertyContext : ctx.scaffoldProperties().scaffoldProperty()) {
            if (propertyContext.body() != null) {
                body = (WidgetNode) visit(propertyContext.body());
            }
        }
        return new ScaffoldNode(body);
    }

    @Override
    public Object visitClass_declaration(Flotter.Class_declarationContext ctx) {
        String name = ctx.IDENTIFIER().toString();
        name = name.substring(1, name.length() - 1);

        LinkedList<Node> declarations = new LinkedList<>();
        LinkedList<Node> functions = new LinkedList<>();
        LinkedList<Node> functionCalls = new LinkedList<>();

        symbolTables.push(symbolTables.peek().clone());
        for (Flotter.Class_member_declarationContext propertyContext : ctx.class_member_declaration()) {
            if (propertyContext.function() != null)
                functions.add((Node) visit(propertyContext));
            else if (propertyContext.class_build_method() != null)
                functions.add((Node) visitClass_build_method(propertyContext.class_build_method()));
            else if (propertyContext.decl() != null) {
                declarations.add(visitDecl(propertyContext.decl()));
            } else if (propertyContext.function_call() != null) {
                functionCalls.add(visitFunction_call(propertyContext.function_call()));
            }
        }
        symbolTables.pop();

        int scope = symbolTables.peek().getTableScope();
        if (ctx.EXTENDS() != null) {
            String baseClass = ctx.IDENTIFIER().toString();
            ClassDeclarationNode classNode = new ClassDeclarationNode(name, declarations, functions, functionCalls, baseClass, scope);
            symbolTables.peek().addClass(classNode);
            return classNode;
        }

        ClassDeclarationNode classNode = new ClassDeclarationNode(name, declarations, functions, functionCalls, scope);
        symbolTables.peek().addClass(classNode);
        return classNode;
    }

    @Override
    public Object visitClass_build_method(Flotter.Class_build_methodContext ctx) {
        boolean override = ctx.override() != null;

        String name = ctx.BUILD().toString();
        String returnType = ctx.WIDGET().toString();
        FunctionParameterNode parameter = new FunctionParameterNode(
                ctx.BUILD_CONTEXT().toString(), ctx.CONTEXT().toString()
        );
        WidgetNode widgetNode = (WidgetNode) visitWidget(ctx.widget());

        return new ClassBuildMethodNode(returnType, name, parameter, override, widgetNode);
    }

    @Override
    public VariableValueNode visitReturnStatement(Flotter.ReturnStatementContext ctx) {
        return new VariableValueNode(visitVariable_value(ctx.variable_value()).getType(), (Node) visit(ctx.variable_value()));
    }

    @Override
    public Object visitFunction(Flotter.FunctionContext ctx) {
        boolean override = ctx.override() != null;

        String name = (ctx.IDENTIFIER() != null) ? ctx.IDENTIFIER().getText() : "Anonymous";

        String returnType = (ctx.function_return_type() != null) ? ctx.function_return_type().getText() : "";
        LinkedList<FunctionParameterNode> parameters = new LinkedList<>();
        BlockNode body = (BlockNode) visit(ctx.block());

        if (ctx.parameters() != null) {
            parameters = visitParameters(ctx.parameters());
        }

        int scope = symbolTables.peek().getTableScope();

        FunctionDeclarationNode functionDeclarationNode = new FunctionDeclarationNode(returnType, name, parameters, override, body, scope);

        if (symbolTables.peek().functionExists(name)) {
            int currentLine = ctx.getStart().getLine();
            throw new RuntimeException("Line no." + currentLine + " function " + name + " is already defined");
        }
        symbolTables.peek().addFunction(functionDeclarationNode);
        return functionDeclarationNode;
    }

    @Override
    public LinkedList<FunctionParameterNode> visitParameters(Flotter.ParametersContext ctx) {
        LinkedList<FunctionParameterNode> parameters = new LinkedList<>();

        ctx.parameter().forEach(parameter -> parameters.add(visitParameter(parameter)));

        return parameters;
    }

    @Override
    public FunctionParameterNode visitParameter(Flotter.ParameterContext ctx) {
        String type = (ctx.variable_type() != null) ? ctx.variable_type().getText() : "dynamic";
        String name = ctx.IDENTIFIER().getText();

        return new FunctionParameterNode(name, type);
    }

    @Override
    public FunctionCallNode visitFunction_call(Flotter.Function_callContext ctx) {
        String calledFunctionName = ctx.IDENTIFIER().getText();
        LinkedList<Node> arguments = new LinkedList<>();
        if (ctx.function_call_arguments() != null) {
            arguments.addAll(visitFunction_call_arguments(ctx.function_call_arguments()));
        }
        return new FunctionCallNode(calledFunctionName, arguments);
    }

    @Override //TODO check if this is working
    public LinkedList<Node> visitFunction_call_arguments(Flotter.Function_call_argumentsContext ctx) {
        LinkedList<Node> arguments = new LinkedList<>();

        ctx.variable_value().forEach(propertyContext -> {
            Node argument = visitVariable_value(propertyContext);
            arguments.add(argument);
        });
        return arguments;
    }

    @Override
    public VariableValueNode visitVariable_value(Flotter.Variable_valueContext ctx) {
        if (ctx.literal() != null) {
            if (ctx.literal().SingleLineString() != null) {
                return new VariableValueNode("String", ctx.literal().SingleLineString().getText());
            } else if (ctx.literal().TRUE_FALSE() != null) {
                return new VariableValueNode("bool", ctx.literal().TRUE_FALSE().getText());
            } else if (ctx.literal().NUMBER() != null) {
                return new VariableValueNode("double", ctx.literal().NUMBER().getText());
            } else {
                return new VariableValueNode("NULL", (String) null);
            }

        } else {
            if (ctx.abstract_().function_call(0) != null)
                return new VariableValueNode("dynamic", (Node) visit(ctx.abstract_().function_call().get(ctx.abstract_().function_call().size() - 1)));
            else if (ctx.abstract_().widget() != null)
                return new VariableValueNode("dynamic", (Node) visit(ctx.abstract_().widget()));
            else
                return new VariableValueNode("dynamic", (Node) visit(ctx.abstract_().IDENTIFIER(0)));
        }
    }

    @Override
    public VariableDeclarationNode visitDecl(Flotter.DeclContext ctx) {
        Type type = visitVariable_type(ctx.variable_type());
        String name = ctx.IDENTIFIER().getText();
        Node initializer;

        int currentLine = ctx.getStart().getLine();

        if (ctx.variable_value() != null) {
            initializer = (Node) visit(ctx.variable_value());
            VariableValueNode assignee = visitVariable_value(ctx.variable_value());
            if (assignee.getType().toString() != type.toString() && type.toString() != "dynamic") {
                throw new RuntimeException("Line no." + currentLine + " Variable " + name + " is not a " + assignee.getType());
            }
        } else {
            initializer = null;
        }

        int scope = symbolTables.peek().getTableScope();

        VariableDeclarationNode ans = new VariableDeclarationNode(scope, type, name, initializer, currentLine);
        symbolTables.peek().addVariable(ans);
        return ans;
    }

    @Override
    public VariableAssignNode visitAssign(Flotter.AssignContext ctx) {
        int scope = symbolTables.peek().getTableScope();
        int currentLine = ctx.getStart().getLine();
        String name = ctx.IDENTIFIER().toString();

        VariableDeclarationNode declared = null;
        for (int i = scope; i >= 0; i--) {
            if (symbolTables.peek().variableExists(name, i)) {
                declared = symbolTables.peek().getVariable(name, i);
                break;
            }
        }

        if (declared != null) {
            VariableValueNode assignee = visitVariable_value(ctx.variable_value());

            if (assignee.getType() != declared.type.toString()) {
                throw new RuntimeException("Line no." + currentLine + " Variable " + name + " is not a " + assignee.getType());
            }
        } else {
            throw new RuntimeException("Line no." + currentLine + " Variable \"" + name + "\" does not exist.");
        }
        return new VariableAssignNode((Node) visit(ctx.IDENTIFIER()), (Node) visit(ctx.variable_value()), ctx.IDENTIFIER().getText());
    }

    @Override
    public Type visitVariable_type(Flotter.Variable_typeContext ctx) {
        if (ctx.IDENTIFIER() != null)
            return Type.TYPE_DYNAMIC;
        else if (ctx.BOOL() != null)
            return Type.TYPE_BOOL;
        else if (ctx.INT() != null)
            return Type.TYPE_INT;
        else if (ctx.DOUBLE() != null)
            return Type.TYPE_DOUBLE;
        else
            return Type.TYPE_STRING;
    }

    @Override
    public NavigatorNode visitNavigator(Flotter.NavigatorContext ctx) {
//        String context = ctx.navContext.getText();
//        String argument = ctx.materialpageroute().function_call().function_call_arguments().variable_value(0).literal().SingleLineString().toString();
//        String target = ctx.materialpageroute().function_call().IDENTIFIER().getText();
//        return new NavigatorNode(context, argument, target);
        return new NavigatorNode(null, null, null);
    }
}
