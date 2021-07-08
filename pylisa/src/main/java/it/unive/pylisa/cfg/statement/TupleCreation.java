package it.unive.pylisa.cfg.statement;

import it.unive.lisa.analysis.*;
import it.unive.lisa.analysis.heap.HeapDomain;
import it.unive.lisa.analysis.value.ValueDomain;
import it.unive.lisa.callgraph.CallGraph;
import it.unive.lisa.program.cfg.CFG;
import it.unive.lisa.program.cfg.CodeLocation;
import it.unive.lisa.program.cfg.edge.Edge;
import it.unive.lisa.program.cfg.statement.Expression;
import it.unive.lisa.program.cfg.statement.Statement;
import it.unive.lisa.util.datastructures.graph.GraphVisitor;

import java.util.Arrays;
import java.util.List;

public class TupleCreation extends Expression {
    private List<Expression> values;

    public TupleCreation(List<Expression> values, CFG cfg, CodeLocation loc) {
        super(cfg, loc);

        this.values = values;
    }

    @Override
    public int setOffset(int i) {
        super.offset = i;
        return i;
    }

    @Override
    public <V> boolean accept(GraphVisitor<CFG, Statement, Edge, V> visitor, V tool) {
        return false;
    }

    @Override
    public String toString() {
        return "("+ Arrays.toString(values.toArray())+")";
    }

    @Override
    public <A extends AbstractState<A, H, V>, H extends HeapDomain<H>, V extends ValueDomain<V>> AnalysisState<A, H, V> semantics(AnalysisState<A, H, V> entryState, CallGraph callGraph, StatementStore<A, H, V> expressions) throws SemanticException {
        throw new SemanticException("Not yet supported");
    }


}
