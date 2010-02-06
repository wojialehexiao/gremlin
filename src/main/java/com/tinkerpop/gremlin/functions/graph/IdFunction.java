package com.tinkerpop.gremlin.functions.graph;

import com.tinkerpop.gremlin.functions.FunctionHelper;
import com.tinkerpop.gremlin.functions.GremlinFunctions;
import com.tinkerpop.gremlin.models.pgm.Graph;
import com.tinkerpop.gremlin.statements.EvaluationException;
import org.apache.commons.jxpath.ExpressionContext;
import org.apache.commons.jxpath.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class IdFunction implements Function {

    public static final String FUNCTION_NAME = "id";

    public Object invoke(final ExpressionContext context, final Object[] parameters) {

        if (null != parameters) {
            Graph graph = GraphFunctionHelper.getGraph(context, parameters);
            Object[] objects = FunctionHelper.nodeSetConversion(parameters);
            if (objects.length == 1) {
                return graph.getVertex(objects[0]);
            } else if (objects.length == 2 && objects[0] instanceof Graph) {
                return graph.getVertex(objects[1]);
            }
        }

        throw EvaluationException.createException(FunctionHelper.makeFunctionName(GremlinFunctions.NAMESPACE_PREFIX, FUNCTION_NAME), EvaluationException.EvaluationErrorType.UNSUPPORTED_PARAMETERS);

    }
}
