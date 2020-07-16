import exceptions.SemanticParseException;
import java.util.HashSet;
import java.util.List;
import java.util.BitSet;

/* Generated By:JJTree: Do not edit this line. ASTIfStatement.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTIfStatement extends SimpleNode {
    public ASTIfStatement(int id) {
        super(id);
    }

    public ASTIfStatement(Parser p, int id) {
        super(p, id);
    }

    @Override
    public String analyze(Descriptor descriptor, HashSet<String> variables) throws SemanticParseException {
        MethodDescriptor methodDescriptor = (MethodDescriptor) descriptor;

        try {
            this.jjtGetChild(0).analyze(descriptor, variables);
        } catch (SemanticParseException ex) {

            // TODO: Auto-generated catch block
            // ex.printStackTrace();
        }

        HashSet<String> cloned1 = new HashSet<String>(); 
        cloned1 = (HashSet) variables.clone();
        try {
            this.jjtGetChild(1).analyze(descriptor, cloned1);
        } catch (SemanticParseException ex) {
            // TODO: Auto-generated catch block
            // ex.printStackTrace();
        }

        HashSet<String> cloned2 = new HashSet<String>(); 
        cloned2 = (HashSet) variables.clone();
        try {
            this.jjtGetChild(2).analyze(descriptor, cloned2);
        } catch (SemanticParseException ex) {
            // TODO: Auto-generated catch block
            // ex.printStackTrace();
        }

        for(String variable : cloned1)
            methodDescriptor.searchVariable(variable).setProgression();
        for(String variable : cloned2)
            methodDescriptor.searchVariable(variable).setProgression();

        cloned1.retainAll(cloned2);

        variables.addAll(cloned1);

        return "";
    }

    @Override
    public void generateCode(Descriptor descriptor) {
        int ifNumber = JVMHelper.getIfLabel();


        // Generate Condition
        // TODO: ver se pode ser null 
        if(this.jjtGetChild(0).jjtGetChild(0) instanceof ASTLess)
            this.generateLessCondition(descriptor, ifNumber);
        else{
            this.jjtGetChild(0).generateCode(descriptor);
            parser.addInstruction(new JVMInstruction("ifeq ELSE_" + ifNumber,-1));
        }

        // Generate If
        this.jjtGetChild(1).generateCode(descriptor);
        if(!(this.jjtGetChild(1) instanceof ASTScope)){
            if(! (this.jjtGetChild(1) instanceof ASTVarDeclaration)){

				if(this.jjtGetChild(1) .getReturnType() != null && this.jjtGetChild(1).getReturnType() != "void" )
					parser.addInstruction(new JVMInstruction("pop",-1));
			}
        }
        parser.addInstruction(new JVMInstruction("goto END_IF_ELSE_" + ifNumber,0));
        parser.addInstruction(new JVMInstruction("ELSE_" + ifNumber + ":",0));

        // Generate Else
        if(!(this.jjtGetChild(2) instanceof ASTScope)){
            if(! (this.jjtGetChild(2) instanceof ASTVarDeclaration)){

				if(this.jjtGetChild(2) .getReturnType() != null && this.jjtGetChild(2).getReturnType() != "void" )
					parser.addInstruction(new JVMInstruction("pop",-1));
			}
        }
        this.jjtGetChild(2).generateCode(descriptor);
        
        parser.addInstruction(new JVMInstruction("END_IF_ELSE_" + ifNumber + ":",0));        
    }

    // TODO: talvez refactor pq parecido com while??
	public void generateLessCondition(Descriptor descriptor, int ifLabel) {
        SimpleNode astLess = this.jjtGetChild(0).jjtGetChild(0);

		SimpleNode lhsNode = astLess.jjtGetChild(0);
        SimpleNode rhsNode = astLess.jjtGetChild(1);
        
        if(lhsNode instanceof ASTNumeric && Integer.parseInt((String) lhsNode.jjtGetValue()) == 0){
            // 0 < x <=> x > 0 -> x =< 0
            rhsNode.generateCode(descriptor);
            parser.addInstruction(new JVMInstruction("ifle ELSE_" + ifLabel,-1));
        }
        else if(rhsNode instanceof ASTNumeric && Integer.parseInt((String) rhsNode.jjtGetValue()) == 0){
            // x < 0 -> x >= 0
            lhsNode.generateCode(descriptor);
            parser.addInstruction(new JVMInstruction("ifge ELSE_" + ifLabel,-1));
        }
        else{
            //e < D -> esquerda >= direita
            lhsNode.generateCode(descriptor);
            rhsNode.generateCode(descriptor);
    
            parser.addInstruction(new JVMInstruction("if_icmpge ELSE_" + ifLabel,-2));
        }

    }
    
    @Override
    public void generateCFG(List<BitSet> use, List<BitSet> def, List<BitSet> succ, MethodDescriptor mDescriptor) {
        
        // Generate condition
        ASTCondition cond = (ASTCondition) this.jjtGetChild(0);
        cond.generateCFG(use, def, succ, mDescriptor);
        int condNodeID = succ.size() - 1;

        // Generate if
        // ASTScope ifScope = (ASTScope) this.jjtGetChild(1);
        this.jjtGetChild(1).generateCFG(use, def, succ, mDescriptor);
        succ.get(condNodeID).set(succ.size());
        int ifEndNodeID = succ.size() - 1; // Store id of the last instruction of the if

        // Generate else
        // ASTScope elseScope = (ASTScope) this.jjtGetChild(2);
        this.jjtGetChild(2).generateCFG(use, def, succ, mDescriptor);
        int elseEndNodeID = succ.size() - 1; // Store id of the last instruction of the else

        // Make the last instruction of the if point to after the else
        succ.get(ifEndNodeID).clear();
        succ.get(ifEndNodeID).set(elseEndNodeID + 1);

        // Add dummy instruction after the else
        BitSet useBoot = new BitSet(mDescriptor.getLocalsCount());
		BitSet defBoot = new BitSet(mDescriptor.getLocalsCount());
        BitSet succBoot = new BitSet();
        use.add(useBoot);
		def.add(defBoot);
        succ.add(succBoot);

        // Make dummy instruction point forward
        succBoot.set(succ.size());

        // Make last else instruction point forward
        succ.get(elseEndNodeID).clear();
        succ.get(elseEndNodeID).set(elseEndNodeID + 1);

    }
}
/*
 * JavaCC - OriginalChecksum=c5401068449a6245fa8125a53993f1d7 (do not edit this
 * line)
 */
