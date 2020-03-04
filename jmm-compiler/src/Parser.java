/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
public class Parser implements ParserConstants {

  static final public void Program() throws ParseException {
    ImportDeclaration();
    ClassDeclaration();
    jj_consume_token(0);
}

  static final public void ImportDeclaration() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IMPORT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(IMPORT);
    }
}

  static final public void ClassDeclaration() throws ParseException {
    jj_consume_token(CLASS);
    jj_consume_token(IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EXTENDS:{
      jj_consume_token(EXTENDS);
      jj_consume_token(IDENTIFIER);
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(LBRACKET);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case BOOLEAN:
      case IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      VarDeclaration();
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PUBLIC:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      MethodDeclaration();
    }
    jj_consume_token(RBRACKET);
}

  static final public void VarDeclaration() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(SCOLON);
}

  static final public void MethodDeclaration() throws ParseException {
    jj_consume_token(PUBLIC);
    Type();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(LPAR);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:
    case BOOLEAN:
    case IDENTIFIER:{
      Type();
      jj_consume_token(IDENTIFIER);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        Type();
        jj_consume_token(IDENTIFIER);
      }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(RPAR);
    jj_consume_token(LBRACKET);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case BOOLEAN:
      case IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_5;
      }
      VarDeclaration();
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case NEW:
      case TRUE:
      case THIS:
      case FALSE:
      case WHILE:
      case LBRACKET:
      case LPAR:
      case NOT:
      case IDENTIFIER:
      case INTEGERLITERAL:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_6;
      }
      Statement();
    }
    jj_consume_token(RETURN);
    Expression();
    jj_consume_token(SCOLON);
    jj_consume_token(RBRACKET);
}

  static final public void Type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      jj_consume_token(INT);
      jj_consume_token(LSQRBRACKET);
      jj_consume_token(RSQRBRACKET);
      break;
      }
    case BOOLEAN:{
      jj_consume_token(BOOLEAN);
      break;
      }{
      jj_consume_token(INT);
      break;
      }
    case IDENTIFIER:{
      jj_consume_token(IDENTIFIER);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LBRACKET:{
      jj_consume_token(LBRACKET);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case IF:
        case NEW:
        case TRUE:
        case THIS:
        case FALSE:
        case WHILE:
        case LBRACKET:
        case LPAR:
        case NOT:
        case IDENTIFIER:
        case INTEGERLITERAL:{
          ;
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          break label_7;
        }
        Statement();
      }
      jj_consume_token(RBRACKET);
      break;
      }
    case IF:{
      jj_consume_token(IF);
      jj_consume_token(LPAR);
      Expression();
      jj_consume_token(RPAR);
      Statement();
      jj_consume_token(ELSE);
      Statement();
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      jj_consume_token(LPAR);
      Expression();
      jj_consume_token(RPAR);
      Statement();
      break;
      }
    case NEW:
    case TRUE:
    case THIS:
    case FALSE:
    case LPAR:
    case NOT:
    case IDENTIFIER:
    case INTEGERLITERAL:{
      Expression();
      jj_consume_token(SCOLON);
      break;
      }{
      jj_consume_token(IDENTIFIER);
      jj_consume_token(EQUALS);
      Expression();
      jj_consume_token(SCOLON);
      break;
      }{
      jj_consume_token(IDENTIFIER);
      jj_consume_token(LSQRBRACKET);
      Expression();
      jj_consume_token(RSQRBRACKET);
      jj_consume_token(EQUALS);
      jj_consume_token(SCOLON);
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void Expression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INTEGERLITERAL:{
      jj_consume_token(INTEGERLITERAL);
      ExpressionFragment();
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
      ExpressionFragment();
      break;
      }
    case FALSE:{
      jj_consume_token(FALSE);
      ExpressionFragment();
      break;
      }
    case IDENTIFIER:{
      jj_consume_token(IDENTIFIER);
      ExpressionFragment();
      break;
      }
    case THIS:{
      jj_consume_token(THIS);
      ExpressionFragment();
      break;
      }
    case NEW:{
      jj_consume_token(NEW);
      jj_consume_token(INT);
      jj_consume_token(LSQRBRACKET);
      Expression();
      jj_consume_token(RSQRBRACKET);
      ExpressionFragment();
      break;
      }{
      jj_consume_token(NEW);
      jj_consume_token(IDENTIFIER);
      jj_consume_token(LPAR);
      jj_consume_token(RPAR);
      ExpressionFragment();
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      Expression();
      ExpressionFragment();
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      Expression();
      jj_consume_token(RPAR);
      ExpressionFragment();
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void ExpressionFragment() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:
    case LESS:
    case PLUS:
    case MINUS:
    case MUL:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        jj_consume_token(AND);
        break;
        }
      case LESS:{
        jj_consume_token(LESS);
        break;
        }
      case PLUS:{
        jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        jj_consume_token(MINUS);
        break;
        }
      case MUL:{
        jj_consume_token(MUL);
        break;
        }
      case DIV:{
        jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Expression();
      ExpressionFragment();
      break;
      }
    case LSQRBRACKET:{
      jj_consume_token(LSQRBRACKET);
      Expression();
      jj_consume_token(RSQRBRACKET);
      ExpressionFragment();
      break;
      }
    case DOT:{
      jj_consume_token(DOT);
      jj_consume_token(LENGTH);
      ExpressionFragment();
      break;
      }{
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
      jj_consume_token(LPAR);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NEW:
      case TRUE:
      case THIS:
      case FALSE:
      case LPAR:
      case NOT:
      case IDENTIFIER:
      case INTEGERLITERAL:{
        Expression();
        label_8:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[13] = jj_gen;
            break label_8;
          }
          jj_consume_token(COMMA);
          Expression();
        }
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        ;
      }
      jj_consume_token(RPAR);
      ExpressionFragment();
      break;
      }
    default:
      jj_la1[15] = jj_gen;

    }
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x10000,0x40000,0x20200,0x80,0x40000000,0x20200,0x20200,0x2881d20,0x20200,0x2881d20,0x2881d20,0x2001d00,0x0,0x40000000,0x2001d00,0x8000000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x100,0x0,0x0,0x100,0x100,0x380,0x100,0x380,0x380,0x380,0x3f,0x0,0x380,0x7f,};
	}

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[42];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 16; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 42; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
