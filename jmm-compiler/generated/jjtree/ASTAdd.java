import exceptions.SemanticParseException;
import java.util.HashSet;


/* Generated By:JJTree: Do not edit this line. ASTAdd.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTAdd extends SimpleNode {
	public ASTAdd(int id) {
		super(id);
		this.returnType = "int";
	}

	public ASTAdd(Parser p, int id) {
		super(p, id);
		this.returnType = "int";
	}

	@Override
	public String analyze(Descriptor descriptor, HashSet<String> variables) throws SemanticParseException{
		MethodDescriptor functionST = (MethodDescriptor) descriptor;

		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);

		String lhsType = lhsNode.analyze(functionST, variables);
		String rhsType = rhsNode.analyze(functionST, variables);

		if ( !lhsType.equals(this.returnType) || !rhsType.equals(this.returnType))
			this.parser.handleSemanticError(new SemanticParseException("Wrong types in addition: " + lhsNode.jjtGetValue() + "[" + lhsType + "]" + " and " + rhsNode.jjtGetValue() + "[" + rhsType + "]"), this);
		
		return this.returnType;
	}

	@Override
	public void generateCode(Descriptor descriptor){

		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);
		lhsNode.generateCode(descriptor);
		rhsNode.generateCode(descriptor);
		parser.addInstruction(new JVMInstruction("iadd", -1));
	}
}
/*
 * JavaCC - OriginalChecksum=f725c76bcd804513aae1cfb65158cd55 (do not edit this
 * line)
 */
