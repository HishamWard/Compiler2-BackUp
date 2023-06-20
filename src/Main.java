import AST.ProgramNode;
import Grammer.Flotter;
import Grammer.LexerGrammar;
import HTMLGeneration.HTMLGenerator;
import Symbol.SymbolTable;
import Visitors.ParseTreeVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        String testFilepath = "tests/error_test.dart";

        // Initialize streams and parser
        CharStream charStream = CharStreams.fromFileName(testFilepath);
        LexerGrammar lexer = new LexerGrammar(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Flotter flotterParseGrammer = new Flotter(tokens);

        // Get parseTree
        ParseTree parseTree = flotterParseGrammer.program();

        // Get AST and write it to a text file
        FileWriter writer = new FileWriter("ast1.dot");
        ParseTreeVisitor testParseTreeVisitor = new ParseTreeVisitor();
        ProgramNode programNode = (ProgramNode) testParseTreeVisitor.visit(parseTree);

        HTMLGenerator htmlGenerator = new HTMLGenerator("C:\\xampp\\htdocs");
        htmlGenerator.generateHTMLFromProgram(programNode);

        programNode.print(writer);
        writer.close();

        System.out.println("🚀 Generation done!");
    }
}
