package HTMLGeneration;

import AST.ProgramNode;
import Visitors.ASTVisitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTMLGenerator {
    private String outputDirectory;
    public HTMLGenerator(String outputDirectory) {
        this.outputDirectory = outputDirectory;

        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            System.out.println("⚠️ Directory '" + outputDirectory + "' doesn't exist. Creating a new one...");
            directory.mkdir();
        }

        System.out.println("📂 Will generate PHP files to: " + outputDirectory);
        System.out.println();
    }

    public void generateHTMLFromProgram(ProgramNode programNode) throws IOException {
        programNode.widgetNodesMap.forEach((widgetClassName, widgetAST) -> {
            try {
                String outputFilename = Path.of(outputDirectory, widgetClassName + ".php").toString();
                FileWriter astWriter = new FileWriter(outputFilename);
                ASTVisitor astVisitor = new ASTVisitor(astWriter);
                writeScriptsBoilerplateToFile(astWriter);

                System.out.println("🚥 Generating HTML + PHP for '" + widgetClassName + "' widget.");

                astVisitor.visit(widgetAST);
                astWriter.write("</body>\n</html>");
                astWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void writeScriptsBoilerplateToFile(FileWriter writer) throws IOException {
        String phpContent = Files.readString(Path.of("src/HTMLGeneration/template.php"));
        writer.write(phpContent);
    }
}
