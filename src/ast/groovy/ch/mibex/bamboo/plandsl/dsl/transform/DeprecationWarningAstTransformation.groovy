package ch.mibex.bamboo.plandsl.dsl.transform

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.CSTNode
import org.codehaus.groovy.syntax.Token
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

// inspired by Jenkins job DSL plug-in
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class DeprecationWarningAstTransformation implements ASTTransformation {
    private static final String LOG_DEPRECATION_METHOD_NAME = 'logDeprecationWarning'
    private static final ClassNode DEPRECATED_ANNOTATION = ClassHelper.make(Deprecated.name)
    private static final ClassNode BAMBOO_ELEMENT = ClassHelper.make('ch.mibex.bamboo.plandsl.dsl.BambooElement')

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        List<ClassNode> classes = sourceUnit.AST.classes.findAll {
            !it.interface && it.implementsInterface(BAMBOO_ELEMENT)
        }

        classes.methods.flatten().each { MethodNode method ->
            List<AnnotationNode> annotations = method.getAnnotations(DEPRECATED_ANNOTATION)
            if (annotations) {
                Token tokenContext = AstTransformationHelper.createTokenContext(annotations[0])
                ConstantExpression deprecationRef = createLogDeprecationRef(sourceUnit, method.declaringClass, tokenContext)
                ExpressionStatement deprecationStmt = new ExpressionStatement(new MethodCallExpression(
                    new VariableExpression("this"),
                    deprecationRef,
                    new ArgumentListExpression()
                ))
                ((BlockStatement) method.code).statements.add(0, deprecationStmt)
            }
        }
    }

    static ConstantExpression createLogDeprecationRef(SourceUnit sourceUnit, ClassNode clazz, CSTNode context) {
        if (!clazz.getMethod(LOG_DEPRECATION_METHOD_NAME)) {
            sourceUnit.errorCollector.addError("no $LOG_DEPRECATION_METHOD_NAME field in $clazz", context, sourceUnit)
        }
        new ConstantExpression(LOG_DEPRECATION_METHOD_NAME)
    }
}