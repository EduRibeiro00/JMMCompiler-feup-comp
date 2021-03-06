import exceptions.SemanticParseException;

import java.util.BitSet;
import java.util.HashSet;
import java.util.List; 

/* Generated By:JJTree: Do not edit this line. ASTVarDeclaration.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTVarDeclaration extends SimpleNode {

	public ASTVarDeclaration(int id) {
		super(id);
	}

	public ASTVarDeclaration(Parser p, int id) {
		super(p, id);
	}

	public ASTType getType(){
		return (ASTType)this.jjtGetChild(0);
	}

	public ASTIdentifier getIdentifier() {
		return (ASTIdentifier)this.jjtGetChild(1);
	}

	@Override
	public String analyze(Descriptor descriptor, HashSet<String> variables) {
		GeneralDescriptor classDesc = descriptor.getGeneralDescriptor();
		String className = (String) this.jjtGetChild(0).value;

		if (className.equals("int") || className.equals("int[]") || className.equals("boolean"))
			return className;

		if (!classDesc.classExists(className))
			this.parser.handleSemanticError(new SemanticParseException(className + " doesn't exist."), this);

		this.returnType = className;
		return this.returnType;
	}

	public void generateCode(Descriptor descriptor) {

		if(descriptor instanceof ClassDescriptor){

			this.classFieldGeneration(descriptor);
		}

        return;
	};
	
	private void classFieldGeneration(Descriptor descriptor){

		ClassDescriptor classD = (ClassDescriptor) descriptor;
		VariableDescriptor classField = classD.searchAttribute((String)this.getIdentifier().jjtGetValue());

		this.parser.addInstruction(JVMHelper.getFieldInstruction(classField));
	}

	@Override
	public void generateCFG(List<BitSet> use, List<BitSet> def, List<BitSet> succ, MethodDescriptor mDescriptor) {
		return;
	}

	@Override
	public void generateCFGNode(BitSet useNode, BitSet defNode, boolean varDef, MethodDescriptor mDescriptor) {
		return;
	}
}
/* JavaCC - OriginalChecksum=6f2c131a0909e861e826b68fd6544de1 (do not edit this line) */
