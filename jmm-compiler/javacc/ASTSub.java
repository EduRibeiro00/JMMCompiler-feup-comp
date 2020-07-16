import exceptions.SemanticParseException;
import java.util.Set;
import java.util.Map;

/* Generated By:JJTree: Do not edit this line. ASTSub.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTSub extends SimpleNode {
	public ASTSub(int id) {
		super(id);
		this.returnType = "int";
	}

	public ASTSub(Parser p, int id) {
		super(p, id);
		this.returnType = "int";
	}

	@Override
	public String analyze(Descriptor descriptor, Set<String> variables) throws SemanticParseException{
		MethodDescriptor functionST = (MethodDescriptor) descriptor;

		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);

		String lhsType = lhsNode.analyze(functionST, variables);
		String rhsType = rhsNode.analyze(functionST, variables);

		if (!lhsType.equals(this.returnType) || !rhsType.equals(this.returnType))
			this.parser.handleSemanticError(new SemanticParseException("Wrong types in subtraction: " + lhsNode.jjtGetValue() + "[" + lhsType
			+ "]" + " and " + rhsNode.jjtGetValue() + "[" + rhsType + "]"), this);

		return this.returnType;
	}

	public void optimize(Descriptor descriptor, Map<String, Map<String, Integer>> variables) {
		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);

		lhsNode.optimize(descriptor, variables);
		rhsNode.optimize(descriptor, variables);

		// If both childs are numeric substitute this node with its result
		if (this.jjtGetChild(0) instanceof ASTNumeric && this.jjtGetChild(1) instanceof ASTNumeric) {
			Integer leftOperand = Integer.parseInt((String) this.jjtGetChild(0).value);
			Integer rightOperand = Integer.parseInt((String) this.jjtGetChild(1).value);
			ASTNumeric constNode = new ASTNumeric(this.parser, ParserTreeConstants.JJTNUMERIC);
			constNode.value = Integer.toString(leftOperand - rightOperand);
			
			((SimpleNode) this.jjtGetParent()).replaceChild(this, constNode);
			return;
		}

		// Other wise, if we shouldn't simplify (p.e. in 1st phase of conditions) set calcValue with the calculated value
		Integer leftOperand = this.jjtGetChild(0).getCalcValue();
		Integer rightOperand = this.jjtGetChild(1).getCalcValue();
		if(leftOperand == null && this.jjtGetChild(0) instanceof ASTNumeric)
			leftOperand = Integer.parseInt((String) this.jjtGetChild(0).value);
		else if(rightOperand == null && this.jjtGetChild(1) instanceof ASTNumeric)
			rightOperand = Integer.parseInt((String) this.jjtGetChild(1).value);
		if(variables.get("#cond#") != null && leftOperand != null && rightOperand != null){
			this.calcValue = leftOperand - rightOperand;		
		}
	}

	@Override
	public void generateCode(Descriptor descriptor){

		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);
		lhsNode.generateCode(descriptor);
		rhsNode.generateCode(descriptor);
		parser.addInstruction(new JVMInstruction("isub",-1));
	}
}
/*
 * JavaCC - OriginalChecksum=b7fc14f4941a1c938c1b03e9740f85d8 (do not edit this
 * line)
 */
