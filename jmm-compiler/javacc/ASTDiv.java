import exceptions.SemanticParseException;
import java.util.Set;
import java.util.Map;

/* Generated By:JJTree: Do not edit this line. ASTDiv.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTDiv extends SimpleNode {
    public ASTDiv(int id) {
        super(id);
        this.returnType = "int";
    }

    public ASTDiv(Parser p, int id) {
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
			this.parser.handleSemanticError(new SemanticParseException("Wrong types in division: " + lhsNode.jjtGetValue() + "[" + lhsType + "]"
            + " and " + rhsNode.jjtGetValue() + "[" + rhsType + "]"), this);

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
            constNode.value = Integer.toString(leftOperand / rightOperand);

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
			this.calcValue = leftOperand / rightOperand;
		}
	}

    @Override
	public void generateCode(Descriptor descriptor){

        SimpleNode lhsNode = this.jjtGetChild(0);
        SimpleNode rhsNode = this.jjtGetChild(1);

        lhsNode.generateCode(descriptor);

        // X / 2 ^ s: s max = 5 bits 31
        if(rhsNode instanceof ASTNumeric){
            int den = Integer.parseInt((String) rhsNode.jjtGetValue());
            int s = powerOfTwo(den);
            if( s >= 0 && s <= 31){
                parser.addInstruction(JVMHelper.loadNumber((int) s));
                parser.addInstruction(new JVMInstruction("ishr",-1));
                return;
            }
        }

		rhsNode.generateCode(descriptor);
		parser.addInstruction(new JVMInstruction("idiv",-1));
    }

    public int powerOfTwo(int n)
    {
        if ((int)(Math.ceil((Math.log(n) / Math.log(2))))  == (int)(Math.floor(((Math.log(n) / Math.log(2))))))
            return (int) (Math.log(n) / Math.log(2));
        return -1;
    }
}
/*
 * JavaCC - OriginalChecksum=a6ae99679f76890aff99ebcee897988e (do not edit this
 * line)
 */
