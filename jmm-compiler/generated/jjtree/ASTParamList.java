import java.util.ArrayList;

/* Generated By:JJTree: Do not edit this line. ASTParamList.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTParamList extends SimpleNode {
    public ASTParamList(int id) {
        super(id);
    }

    public ASTParamList(Parser p, int id) {
        super(p, id);
    }

    public ArrayList<ASTType> getParamTypes() {
        ArrayList<ASTType> ret = new ArrayList<>();

        for(int i = 0; i < this.jjtGetNumChildren(); i++)
            ret.add(((ASTVarDeclaration) this.jjtGetChild(i)).getType());

        return ret;
    }

    // analyze nao necessario
}
/* JavaCC - OriginalChecksum=d86d4b3287f529b31bac5273502d0001 (do not edit this line) */
