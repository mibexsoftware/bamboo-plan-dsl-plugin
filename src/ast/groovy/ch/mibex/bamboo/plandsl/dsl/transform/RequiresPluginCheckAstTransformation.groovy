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
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static ch.mibex.bamboo.plandsl.dsl.transform.AstTransformationHelper.createBambooFacadeRef
import static ch.mibex.bamboo.plandsl.dsl.transform.AstTransformationHelper.createTokenContext
// inspired by Jenkins job DSL plug-in
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class RequiresPluginCheckAstTransformation implements ASTTransformation {
    private static final ClassNode ANNOTATION = ClassHelper.make('ch.mibex.bamboo.plandsl.dsl.RequiresPlugin')

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        sourceUnit.AST?.classes*.methods.flatten().each { MethodNode method ->
            method.getAnnotations(ANNOTATION).each { AnnotationNode annotationNode ->
                def tokenContext = createTokenContext(annotationNode)
                VariableExpression bambooFacade = createBambooFacadeRef(sourceUnit, method.declaringClass, tokenContext)
                ArgumentListExpression argumentList = new ArgumentListExpression(annotationNode.members.key)

                ConstantExpression methodExpression
                if (annotationNode.members.minimumVersion) {
                    argumentList.addExpression(annotationNode.members.minimumVersion)
                    methodExpression = new ConstantExpression('requireMinimumPluginVersion')
                } else {
                    methodExpression = new ConstantExpression('requirePlugin')
                }

                MethodCallExpression pluginStmt = new MethodCallExpression(bambooFacade, methodExpression, argumentList)
                ((BlockStatement) method.code).statements.add(0, new ExpressionStatement(pluginStmt))
            }
        }
    }

}