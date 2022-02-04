package it.unive.pylisa.cfg.statement;

import it.unive.lisa.analysis.AbstractState;
import it.unive.lisa.analysis.AnalysisState;
import it.unive.lisa.analysis.SemanticException;
import it.unive.lisa.analysis.StatementStore;
import it.unive.lisa.analysis.heap.HeapDomain;
import it.unive.lisa.analysis.value.TypeDomain;
import it.unive.lisa.analysis.value.ValueDomain;
import it.unive.lisa.interprocedural.InterproceduralAnalysis;
import it.unive.lisa.program.cfg.CFG;
import it.unive.lisa.program.cfg.CodeLocation;
import it.unive.lisa.program.cfg.edge.Edge;
import it.unive.lisa.program.cfg.statement.Statement;
import it.unive.lisa.symbolic.SymbolicExpression;
import it.unive.lisa.symbolic.value.Variable;
import it.unive.lisa.util.datastructures.graph.GraphVisitor;
import it.unive.pylisa.cfg.type.PyLibraryType;
import it.unive.pylisa.symbolic.LibraryIdentifier;

public class Import extends Statement {

	protected final String importedLibrary;
	protected final String name;

	// import <importedLibrary> as <name>
	public Import(String importedLibrary, String name, CFG cfg, CodeLocation loc) {
		super(cfg, loc);
		this.importedLibrary = importedLibrary;
		this.name = name;
	}

	@Override
	public int setOffset(int i) {
		super.offset = i;
		return i;
	}

	@Override
	public <V> boolean accept(GraphVisitor<CFG, Statement, Edge, V> visitor, V tool) {
		return visitor.visit(tool, getCFG(), this);
	}

	@Override
	public String toString() {
		return "import " + importedLibrary + " as " + name;
	}

	@Override
	public <A extends AbstractState<A, H, V, T>,
			H extends HeapDomain<H>,
			V extends ValueDomain<V>,
			T extends TypeDomain<T>> AnalysisState<A, H, V, T> semantics(AnalysisState<A, H, V, T> entryState,
					InterproceduralAnalysis<A, H, V, T> interprocedural, StatementStore<A, H, V, T> expressions)
					throws SemanticException {
		SymbolicExpression libexpr = new LibraryIdentifier(importedLibrary, this.getLocation());
		Variable var = new Variable(new PyLibraryType(importedLibrary), name, this.getLocation());
		return entryState.assign(var, libexpr, this);
	}

}
