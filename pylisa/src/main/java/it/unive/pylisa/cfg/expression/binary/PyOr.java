package it.unive.pylisa.cfg.expression.binary;

import it.unive.lisa.analysis.AnalysisState;
import it.unive.lisa.analysis.HeapDomain;
import it.unive.lisa.analysis.SemanticException;
import it.unive.lisa.analysis.ValueDomain;
import it.unive.lisa.callgraph.CallGraph;
import it.unive.lisa.cfg.CFG;
import it.unive.lisa.cfg.statement.Expression;
import it.unive.lisa.symbolic.SymbolicExpression;
import it.unive.lisa.cfg.statement.BinaryNativeCall;
import it.unive.pylisa.cfg.type.PyBoolType;

/**
 * A Python or function call (e1 | e2, e1 or e2).
 * 
 * @author Nicol� Barbato
 */

public class PyOr extends BinaryNativeCall{

	/**
	 * Builds a Python or expression at a given location in the program.
	 * 
	 * @param cfg           the cfg that this expression belongs to
	 * @param sourceFile    the source file where this expression happens. If
	 *                      unknown, use {@code null}
	 * @param line          the line number where this expression happens in the
	 *                      source file. If unknown, use {@code -1}
	 * @param col           the column where this expression happens in the source
	 *                      file. If unknown, use {@code -1}
	 * @param exp1		    left-hand side operand
	 * @param exp2		    right-hand side operand
	 */
	
	public PyOr(CFG cfg, String sourceFile, int line, int col, Expression left, Expression right, String token) {
		super(cfg, sourceFile, line, col, token.equals("or")? "or" : "|", PyBoolType.INSTANCE, left, right); 
	}

	@Override
	protected <H extends HeapDomain<H>, V extends ValueDomain<V>> AnalysisState<H, V> binarySemantics(
			AnalysisState<H, V> computedState, CallGraph callGraph, SymbolicExpression left, SymbolicExpression right)
			throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}
}
