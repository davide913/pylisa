package it.unive.pylisa.symbolic;

import it.unive.lisa.caches.Caches;
import it.unive.lisa.symbolic.SymbolicExpression;
import it.unive.lisa.symbolic.value.UnaryExpression;
import it.unive.lisa.symbolic.value.operator.unary.UnaryOperator;
import it.unive.lisa.type.Type;
import it.unive.lisa.util.collections.externalSet.ExternalSet;
import it.unive.pylisa.libraries.pandas.PyDataframeType;

public class StructuralInfo implements UnaryOperator, DataframeOperatorWithSideEffects {

	public static final StructuralInfo INSTANCE = new StructuralInfo();

	private StructuralInfo() {
	}

	@Override
	public String toString() {
		return "struc_info";
	}

	@Override
	public ExternalSet<Type> typeInference(ExternalSet<Type> arg) {
		if (arg.noneMatch(t -> t.equals(PyDataframeType.REFERENCE)))
			return Caches.types().mkEmptySet();
		return Caches.types().mkSingletonSet(PyDataframeType.INSTANCE);
	}

	@Override
	public SymbolicExpression getDataFrame(SymbolicExpression container) {
		return ((UnaryExpression) container).getExpression();
	}
}
