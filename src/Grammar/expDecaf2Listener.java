// Generated from C:\Users\Javier Fong\Documents\Universidad\2016\Ciclo 1\Clases\Compiladores\COctothorpeProject\src\Grammar\expDecaf2.g4 by ANTLR 4.4
package Grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link expDecaf2Parser}.
 */
public interface expDecaf2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull expDecaf2Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull expDecaf2Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#arg2}.
	 * @param ctx the parse tree
	 */
	void enterArg2(@NotNull expDecaf2Parser.Arg2Context ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#arg2}.
	 * @param ctx the parse tree
	 */
	void exitArg2(@NotNull expDecaf2Parser.Arg2Context ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#arg1}.
	 * @param ctx the parse tree
	 */
	void enterArg1(@NotNull expDecaf2Parser.Arg1Context ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#arg1}.
	 * @param ctx the parse tree
	 */
	void exitArg1(@NotNull expDecaf2Parser.Arg1Context ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(@NotNull expDecaf2Parser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(@NotNull expDecaf2Parser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#methodType}.
	 * @param ctx the parse tree
	 */
	void enterMethodType(@NotNull expDecaf2Parser.MethodTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#methodType}.
	 * @param ctx the parse tree
	 */
	void exitMethodType(@NotNull expDecaf2Parser.MethodTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(@NotNull expDecaf2Parser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(@NotNull expDecaf2Parser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#relop}.
	 * @param ctx the parse tree
	 */
	void enterRelop(@NotNull expDecaf2Parser.RelopContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#relop}.
	 * @param ctx the parse tree
	 */
	void exitRelop(@NotNull expDecaf2Parser.RelopContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull expDecaf2Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull expDecaf2Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#add_Exp}.
	 * @param ctx the parse tree
	 */
	void enterAdd_Exp(@NotNull expDecaf2Parser.Add_ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#add_Exp}.
	 * @param ctx the parse tree
	 */
	void exitAdd_Exp(@NotNull expDecaf2Parser.Add_ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(@NotNull expDecaf2Parser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(@NotNull expDecaf2Parser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#rel_Exp}.
	 * @param ctx the parse tree
	 */
	void enterRel_Exp(@NotNull expDecaf2Parser.Rel_ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#rel_Exp}.
	 * @param ctx the parse tree
	 */
	void exitRel_Exp(@NotNull expDecaf2Parser.Rel_ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull expDecaf2Parser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull expDecaf2Parser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull expDecaf2Parser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull expDecaf2Parser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#addop}.
	 * @param ctx the parse tree
	 */
	void enterAddop(@NotNull expDecaf2Parser.AddopContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#addop}.
	 * @param ctx the parse tree
	 */
	void exitAddop(@NotNull expDecaf2Parser.AddopContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void enterBool_literal(@NotNull expDecaf2Parser.Bool_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void exitBool_literal(@NotNull expDecaf2Parser.Bool_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull expDecaf2Parser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull expDecaf2Parser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#parameterType}.
	 * @param ctx the parse tree
	 */
	void enterParameterType(@NotNull expDecaf2Parser.ParameterTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#parameterType}.
	 * @param ctx the parse tree
	 */
	void exitParameterType(@NotNull expDecaf2Parser.ParameterTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#expressionA}.
	 * @param ctx the parse tree
	 */
	void enterExpressionA(@NotNull expDecaf2Parser.ExpressionAContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#expressionA}.
	 * @param ctx the parse tree
	 */
	void exitExpressionA(@NotNull expDecaf2Parser.ExpressionAContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(@NotNull expDecaf2Parser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(@NotNull expDecaf2Parser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(@NotNull expDecaf2Parser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(@NotNull expDecaf2Parser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull expDecaf2Parser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull expDecaf2Parser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#int_literal}.
	 * @param ctx the parse tree
	 */
	void enterInt_literal(@NotNull expDecaf2Parser.Int_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#int_literal}.
	 * @param ctx the parse tree
	 */
	void exitInt_literal(@NotNull expDecaf2Parser.Int_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#mult_Exp}.
	 * @param ctx the parse tree
	 */
	void enterMult_Exp(@NotNull expDecaf2Parser.Mult_ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#mult_Exp}.
	 * @param ctx the parse tree
	 */
	void exitMult_Exp(@NotNull expDecaf2Parser.Mult_ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#char_literal}.
	 * @param ctx the parse tree
	 */
	void enterChar_literal(@NotNull expDecaf2Parser.Char_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#char_literal}.
	 * @param ctx the parse tree
	 */
	void exitChar_literal(@NotNull expDecaf2Parser.Char_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull expDecaf2Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull expDecaf2Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#multiple_declaration}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_declaration(@NotNull expDecaf2Parser.Multiple_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#multiple_declaration}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_declaration(@NotNull expDecaf2Parser.Multiple_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull expDecaf2Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull expDecaf2Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#conditionalop}.
	 * @param ctx the parse tree
	 */
	void enterConditionalop(@NotNull expDecaf2Parser.ConditionalopContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#conditionalop}.
	 * @param ctx the parse tree
	 */
	void exitConditionalop(@NotNull expDecaf2Parser.ConditionalopContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#negate_Exp}.
	 * @param ctx the parse tree
	 */
	void enterNegate_Exp(@NotNull expDecaf2Parser.Negate_ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#negate_Exp}.
	 * @param ctx the parse tree
	 */
	void exitNegate_Exp(@NotNull expDecaf2Parser.Negate_ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(@NotNull expDecaf2Parser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(@NotNull expDecaf2Parser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#mulop}.
	 * @param ctx the parse tree
	 */
	void enterMulop(@NotNull expDecaf2Parser.MulopContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#mulop}.
	 * @param ctx the parse tree
	 */
	void exitMulop(@NotNull expDecaf2Parser.MulopContext ctx);
	/**
	 * Enter a parse tree produced by {@link expDecaf2Parser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull expDecaf2Parser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link expDecaf2Parser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull expDecaf2Parser.LiteralContext ctx);
}