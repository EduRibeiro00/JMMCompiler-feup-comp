import exceptions.SemanticParseException;
import java.util.Set;

/* Generated By:JJTree: Do not edit this line. ASTClass.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTClass extends SimpleNode {
	public ASTClass(int id) {
		super(id);
	}

	public ASTClass(Parser p, int id) {
		super(p, id);
	}

	@Override
	public String analyze(Descriptor descriptor, Set<String> variables) throws SemanticParseException{
		ClassDescriptor classST = ((GeneralDescriptor) descriptor).getClass((String)this.jjtGetValue());
		for(int i = 0; i < this.jjtGetNumChildren(); i++)
			try{

				this.jjtGetChild(i).analyze(classST, variables);
			}
			catch(SemanticParseException ex){
			}
		return "";
	}

	public void optR(Descriptor descriptor){
		ClassDescriptor classST = ((GeneralDescriptor) descriptor).getClass((String)this.jjtGetValue());
		for (int i = 0; i < this.jjtGetNumChildren(); i++)
			this.jjtGetChild(i).optR(classST);
		return;
	}



	public void generateCode(Descriptor descriptor) {

        ClassDescriptor classST = ((GeneralDescriptor) descriptor).getClass((String)this.jjtGetValue());

		this.parser.addInstruction(new JVMInstruction(".class public " + classST.getIdentifier(),0));
		String superClass = classST.getExtendedClassName();
		superClass = superClass != null ? superClass  : "java/lang/Object";
		this.parser.addInstruction(new JVMInstruction(".super " + superClass ,0));

        for (int i = 0; i < this.jjtGetNumChildren(); i++)
                this.jjtGetChild(i).generateCode(classST);

		this.parser.addInstruction(new JVMInstruction(".method public <init>()V",0));
		this.parser.addInstruction(new JVMInstruction("   aload_0",1));
		this.parser.addInstruction(new JVMInstruction("   invokenonvirtual " + superClass + "/<init>()V",0));
		this.parser.addInstruction(new JVMInstruction("   return",0));
		this.parser.addInstruction(new JVMInstruction(".end method",0));

        return;
    };

}
/* JavaCC - OriginalChecksum=f92e5600b4d1b54c8cf65f0fa9dc9373 (do not edit this line) */