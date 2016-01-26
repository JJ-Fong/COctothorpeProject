// Generated from C:\Users\Javier Fong\Desktop\Universidad\2016\Ciclo 1\Clases\Compiladores\COctothorpeProject\src\Grammar\expDecaf2.g4 by ANTLR 4.4
package Grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link expDecaf2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface expDecaf2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull expDecaf2Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#arg2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg2(@NotNull expDecaf2Parser.Arg2Context ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#arg1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg1(@NotNull expDecaf2Parser.Arg1Context ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#methodType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodType(@NotNull expDecaf2Parser.MethodTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(@NotNull expDecaf2Parser.LocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(@NotNull expDecaf2Parser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull expDecaf2Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(@NotNull expDecaf2Parser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#rel_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRel_op(@NotNull expDecaf2Parser.Rel_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(@NotNull expDecaf2Parser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull expDecaf2Parser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#bool_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_literal(@NotNull expDecaf2Parser.Bool_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#parameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterType(@NotNull expDecaf2Parser.ParameterTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#expressionA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionA(@NotNull expDecaf2Parser.ExpressionAContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#cond_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_op(@NotNull expDecaf2Parser.Cond_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(@NotNull expDecaf2Parser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(@NotNull expDecaf2Parser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull expDecaf2Parser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(@NotNull expDecaf2Parser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#int_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_literal(@NotNull expDecaf2Parser.Int_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#char_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_literal(@NotNull expDecaf2Parser.Char_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull expDecaf2Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#arith_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith_op(@NotNull expDecaf2Parser.Arith_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull expDecaf2Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#eq_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_op(@NotNull expDecaf2Parser.Eq_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(@NotNull expDecaf2Parser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link expDecaf2Parser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(@NotNull expDecaf2Parser.LiteralContext ctx);
}