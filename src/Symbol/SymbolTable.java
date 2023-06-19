package Symbol;

import AST.ClassDeclarationNode;
import AST.Functions.FunctionDeclarationNode;
import AST.Variables.VariableDeclarationNode;

import java.util.HashMap;
import java.util.Objects;

public class SymbolTable {
    private HashMap<Integer, VariableDeclarationNode> var_table = new HashMap<>();
    private HashMap<Integer, FunctionDeclarationNode> function_table = new HashMap<>();
    private HashMap<Integer, ClassDeclarationNode> class_table = new HashMap<>();
    private int tableScope = 0;

    public SymbolTable() {
    }

    public SymbolTable(HashMap<Integer, VariableDeclarationNode> var_table,
                       HashMap<Integer, FunctionDeclarationNode> function_table,
                       HashMap<Integer, ClassDeclarationNode> class_table,
                       int tableScope) {

        this.tableScope = tableScope;

        this.var_table = (HashMap<Integer, VariableDeclarationNode>) var_table.clone();
        this.function_table = (HashMap<Integer, FunctionDeclarationNode>) function_table.clone();
        this.class_table = (HashMap<Integer, ClassDeclarationNode>) class_table.clone();
    }

    public void addVariable(VariableDeclarationNode v) throws RuntimeException {
        if (variableExists(v.name, v.scope))
            throw new RuntimeException("Variable \"" + v.name + "\" already exists.");
        var_table.put(Objects.hash(v.name + v.scope), v);

        System.out.printf("SymbolTable entry - [name: %s | scope: %d | type: %s]\n", v.name, v.scope, v.type.name);
    }

    public boolean variableExists(String name, int scope) {
        return var_table.containsKey(Objects.hash(name + scope));
        //return var_table.containsKey(Objects.hash(variableName));
    }

    public VariableDeclarationNode getVariable(String name, int scope) {
        return var_table.get(Objects.hash(name + scope));
    }

    public void addClass(ClassDeclarationNode c) throws RuntimeException {
        if (classExists(c.name)) throw new RuntimeException("Class \"" + c.name + "\" already exists.");
        class_table.put(Objects.hash(c.name), c);
        System.out.printf("SymbolTable entry - [name: %s | scope: %d | type: class]\n", c.name, c.scope);
    }

    public boolean classExists(String className) {
        return class_table.containsKey(Objects.hash(className));
    }

    //functions
    public void addFunction(FunctionDeclarationNode f) throws RuntimeException {
        if (functionExists(f.name)) throw new RuntimeException("Function \"" + f.name + "\" already exists.");
        //no checking for functions to avoid errors
        function_table.put(Objects.hash(f.name), f);
        System.out.printf("SymbolTable entry - [name: %s | scope: %d | type: function]\n", f.name, f.scope);
    }

    public boolean functionExists(String signature) {
        return function_table.containsKey(Objects.hash(signature));
    }

    public void print() {
        System.out.println("\nSymbol table contents: ");

        System.out.println("\nClasses: ");
        class_table.forEach((classKey, c) -> {
            System.out.println("Entry hash: " + classKey + " -- " + "class" + " " + c.name +
                    " -- scope (" + c.scope + ")");
        });

        System.out.println("\nVariables: ");
        var_table.forEach((variableKey, var) -> {
            System.out.println("Entry hash: " + variableKey + " -- " + var.type + " " + var.name +
                    " -- scope (" + var.scope + ")");
        });

        System.out.println("\nFunctions: ");
        function_table.forEach((functionKey, fun) -> {
            System.out.println("Entry hash: " + functionKey + " -- " + fun.returnType + " " + fun.name +
                    " -- scope (" + fun.scope + ")");
        });
    }

    public SymbolTable clone() {
        return new SymbolTable(this.var_table, this.function_table, this.class_table, this.tableScope + 1);
    }

    public int getTableScope() {
        return this.tableScope;
    }
}
