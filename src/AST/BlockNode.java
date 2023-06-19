package AST;
import Visitors.ASTVisitor;

import java.util.LinkedList;

public class BlockNode extends ExpressionNode {
    int firstLineNum, lastLineNum;

    public BlockNode(int firstLineNum, int lastLineNum, LinkedList<Node> expressionNodes) {
        this.firstLineNum = firstLineNum;
        this.lastLineNum = lastLineNum;
        expressionNodes.forEach(this::addChild);
    }

    @Override
    public void accept(ASTVisitor visitor) {

    }
}
