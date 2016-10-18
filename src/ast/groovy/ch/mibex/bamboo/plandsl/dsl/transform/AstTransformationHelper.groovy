package ch.mibex.bamboo.plandsl.dsl.transform

import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.CSTNode
import org.codehaus.groovy.syntax.Token

class AstTransformationHelper {
    private AstTransformationHelper() {}

    static VariableExpression createBambooFacadeRef(SourceUnit sourceUnit, ClassNode clazz, CSTNode context) {
        if (!clazz.getField('bambooFacade')) {
            sourceUnit.errorCollector.addError("no bambooFacade field in $clazz", context, sourceUnit)
        }
        new VariableExpression('bambooFacade')
    }

    static Token createTokenContext(AnnotationNode annotationNode) {
        Token.newString(annotationNode.text, annotationNode.lineNumber, annotationNode.columnNumber)
    }
}
