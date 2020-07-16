import exceptions.SemanticParseException;
import java.util.Set;
import java.util.Map;

/* Generated By:JJTree: Do not edit this line. ASTNot.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTNot extends SimpleNode {
	public ASTNot(int id) {
		super(id);
		this.returnType = "boolean";
	}

	public ASTNot(Parser p, int id) {
		super(p, id);
		this.returnType = "boolean";
	}

	@Override
	public String analyze(Descriptor descriptor, Set<String> variables) throws SemanticParseException{
		MethodDescriptor functionST = (MethodDescriptor) descriptor;

		SimpleNode node = this.jjtGetChild(0);

		String type = node.analyze(functionST, variables);

		if (!type.equals(this.returnType)) {
			this.parser.handleSemanticError(new SemanticParseException("Wrong type in negation: " + node.jjtGetValue() + "[" + type + "]"), this);

		}

		return this.returnType;
	}

	public void optimize(Descriptor descriptor, Map<String, Map<String, Integer>> variables) {
		SimpleNode node = this.jjtGetChild(0);

		node.optimize(descriptor, variables);

		// If child is boolean substitute this node with its result
		if (this.jjtGetChild(0) instanceof ASTBoolean) {
			ASTBoolean constNode = new ASTBoolean(this.parser, ParserTreeConstants.JJTBOOLEAN);

			boolean op = this.jjtGetChild(0).value.equals("true");
			if (! op) {
				constNode.value = "true";
			} else {
				constNode.value = "false";
			}

			((SimpleNode) this.jjtGetParent()).replaceChild(this, constNode);
			return;
		}

		// Other wise, if we shouldn't simplify (p.e. in 1st phase of conditions) set calcValue with the calculated value
		Integer operand = node.getCalcValue();
		if(operand == null && this.jjtGetChild(0) instanceof ASTBoolean)
			operand = this.jjtGetChild(0).value.equals("true") ? 1 : 0;
		if(variables.get("#cond#") != null && operand != null){
			boolean op = operand == 1;
			this.calcValue = !op ? 1 : 0;		
		}
	}

	@Override
	public void generateCode(Descriptor descriptor) {
		SimpleNode node = this.jjtGetChild(0);
		node.generateCode(descriptor);
		parser.addInstruction(JVMHelper.loadBoolean("true"));
		parser.addInstruction(new JVMInstruction("ixor",-1));
	}
}
/*
 * JavaCC - OriginalChecksum=a8e76e5251f32aca84931a9ecc0089f6 (do not edit this
 * line)
 */