/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDE;

import java.util.ArrayList;

/**
 *
 * @author Javier Fong
 */
public class typeSetter {
    myNode tree;
    String error; 
    
    ArrayList<Struct> structTable; 
    ArrayList<Symbol> symbolTable;
    ArrayList<Method> methodTable; 
    ArrayList<ArrayList<Symbol>> parameterList; 
    ArrayList<Scope> scopes; 
    
    Scope actual,scope_memory; 
    public typeSetter(myNode tree, tableBuilder tableB){
        this.tree = tree;
        this.structTable = tableB.getStructTable();
        this.symbolTable = tableB.getSymbolTable();
        this.methodTable = tableB.getMethodTable();
        this.scopes = tableB.getScopes(); 
        this.error = ""; 
        tree = typeProgram(tree); 
    }
    
    private myNode typeProgram(myNode cabeza){
        myNode mulDecl = cabeza.getChild(3);
        typeMulDecl(mulDecl);
        cabeza.setType(mulDecl.getType());
        return cabeza;
    }

    private myNode typeMulDecl(myNode mulDecl) {
        ArrayList<myNode> children = mulDecl.getAllChildren();
        boolean isVoid = true; 
        for (int i = 0; i < children.size(); i++) {
            myNode child = children.get(i);
            if (child.getText().equals("structDeclaration")) child = typeStructDeclaration(child);
            else if (child.getText().equals("varDeclaration")) child = typeVarDeclaration(child);
            else if (child.getText().equals("methodDeclaration")) child = typeMethodDeclaration(child);
            isVoid = (isVoid && (child.getType().equals("void"))); 
        }
        if (isVoid) {
            mulDecl.setType("void");
        } else {
            mulDecl.setType("type_error");
        }
        return mulDecl; 
    }
    
    private myNode typeStructDeclaration(myNode nodo) {
        nodo.setType("void");
        return nodo;
    }
    
    private myNode typeVarDeclaration(myNode nodo) {
        nodo.setType("void");
        return nodo;
    }
    
    private myNode typeMethodDeclaration(myNode nodo) {
        myNode id = nodo.getChild(2);
        myNode block = nodo.getChild(4);
        actual = nextUnchecked(actual);
        block = typeBlock(block);
        actual.setChecked();
        actual = actual.getAnterior();
        
        Method method = getMethod(id.getText());
        if (method.getType().getType_name().equals(block.getType())) {
            nodo.setType("void");
        } else {
            nodo.setType("type_error");
        }
        return nodo;
    }
    
    private myNode typeBlock(myNode nodo) {
        ArrayList<myNode> body = nodo.getAllChildren();
        boolean isVoid = true; 
        for (int i = 0; i < body.size(); i++) {
            myNode child = body.get(i);
            if (child.getText().equals("varDeclaration")) child = typeVarDeclaration(child);
            else if (child.getText().equals("statement")) child = typeStatement(child);
            isVoid = (isVoid && (!child.getType().equals("type_erro"))); 
        }
        if (isVoid) {
            nodo.setType("void");//FALTA
        } else {
            nodo.setType("type_error");
        }
        return nodo;
    }
    
    private myNode typeStatement(myNode nodo) {
        myNode statement = nodo.getChild(0); 
        if (statement.getText().equals("statement_cerrado")) statement = typeStatementCerrado(statement);
        else if (statement.getText().equals("statement")) statement = typeStatementAbierto(statement);
        nodo.setType(statement.getType());
        return nodo; 
    }
    
    private myNode typeStatementCerrado(myNode nodo){
        myNode indicador = nodo.getChild(0); 
        if (indicador.getText().equals("if")) {
            myNode expression = nodo.getChild(2); 
            myNode stmtCerrado1 = nodo.getChild(4);
            myNode stmtCerrado2 = nodo.getChild(6);
            
            expression = typeExpression(expression);
            stmtCerrado1 = typeStatementCerrado(stmtCerrado1);
            stmtCerrado2 = typeStatementCerrado(stmtCerrado2);
            if (expression.getType().equals("boolean")&&(!stmtCerrado1.getType().equals("type_error"))&&(!stmtCerrado2.getType().equals("type_error"))) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            if (!expression.getType().equals("boolean")) {
                error = error + "boolean expression condition expected in IF \n"; 
            }
        } else if (indicador.getText().equals("while")) {
            myNode expression = nodo.getChild(2); 
            myNode block = nodo.getChild(4);
            
            expression = typeExpression(expression);
            actual = nextUnchecked(actual);
            block = typeBlock(block);
            actual.setChecked();
            actual = actual.getAnterior();
            if (expression.getType().equals("boolean")&&(!block.getType().equals("type_error"))) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            
            if (!expression.getType().equals("boolean")) {
                error = error + "boolean expression condition expected in WHILE\n"; 
            }
        } else if (indicador.getText().equals("methodCall")) {
            myNode methodCall = indicador; 
            methodCall = typeMethodCall(methodCall); 
            nodo.setType(methodCall.getType());
        } else if (indicador.getText().equals("block")) {
            myNode block = indicador; 
            actual = nextUnchecked(actual);
            block = typeBlock(block);
            actual.setChecked();
            actual = actual.getAnterior();
            nodo.setType(block.getType());
        } else if (indicador.getText().equals("location")) {
            myNode location = indicador; 
            myNode expression = nodo.getChild(2); 
            
            location = typeLocation(location);
            expression = typeExpression(expression);
            
            if (location.getType().equals(expression.getType())) {
                nodo.setType("void");
            } else {
                error = expression.getType()+" cannot be stored in a "+location.getType()+"\n"; 
            }
        } else if (indicador.getText().equals("expressionA")){
            myNode expression = nodo.getChild(2); 
            
            expression = typeExpressionA(expression);
            
            nodo.setType(expression.getType()); 
        } else {
            nodo.setType("void");
        }
        
        return nodo; 
    }
    
    private myNode typeStatementAbierto(myNode nodo) {
        ArrayList<myNode> body = nodo.getAllChildren(); 
        if (body.size() == 3) {
            myNode expression = body.get(2);
            myNode statement = body.get(4); 
            
            expression = typeExpression(expression);
            statement = typeStatement(statement); 
            
            if (expression.getType().equals("boolean")&&(!statement.getType().equals("type_error"))){
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            if (!expression.getType().equals("boolean")) {
                error = error + "boolean expression condition expected in IF \n"; 
            }
        } else {
            myNode expression = body.get(2);
            myNode stmtCerrado = body.get(4); 
            myNode stmtAbierto = body.get(6); 
            
            expression = typeExpression(expression);
            stmtCerrado = typeStatement(stmtCerrado);
            stmtAbierto = typeStatement(stmtAbierto); 
            
            if (expression.getType().equals("boolean")&&(!stmtCerrado.getType().equals("type_error"))&&(!stmtAbierto.getType().equals("type_error"))){
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            if (!expression.getType().equals("boolean")) {
                error = error + "boolean expression condition expected in IF \n"; 
            }
        }
        return nodo; 
    }
    
    private myNode typeExpressionA(myNode nodo) {
        if (nodo.getAllChildren().size() > 0) {
            myNode expression = nodo.getChild(0); 
            
            expression = typeExpression(expression); 
            
            nodo.setType(expression.getType());
        } else {
            nodo.setType("void"); 
        }
        return nodo; 
    }
    
    private myNode typeLocation(myNode nodo) {
        ArrayList<myNode> body = nodo.getAllChildren(); 
        
        switch (body.size()) {
            case 1: {
                myNode id = body.get(0); 
                Symbol symbol = getSymbol(id.getText());
                if (symbol == null) {
                    error = id.getText() + " does not exist"; 
                    nodo.setType("type_error");
                } else {
                    nodo.setType(symbol.getTipo().getType_name());
                }
            } break; 
            case 3: {
                myNode id = body.get(0);
                Symbol symbol = getSymbol(id.getText()); 
                if (symbol == null) {
                    error = id.getText() + " does not exist"; 
                    nodo.setType("type_error");
                } else {
                    if (symbol.getTipo().isStructure()) {
                        Struct struct = getStruct(symbol.getTipo().getType_name());
                        if (struct == null) {
                            error = symbol.getTipo().getType_name() + " does not exist"; 
                            nodo.setType("type_error");
                        } else {
                            scope_memory = actual; 
                            actual = struct.getInnerScope(); 
                            myNode location = body.get(2); 
                            actual = scope_memory; 
                            location = typeLocation(location);
                            nodo.setType(location.getType());
                        }
                    } else {
                        error = id.getText() + " is not structure"; 
                        nodo.setType("type_error");
                    }
                }
            } break; 
            case 4: {
                myNode id = body.get(0); 
                myNode expression = body.get(2); 
                Symbol symbol = getSymbol(id.getText());
                if (symbol == null) {
                    error = id.getText() + " does not exist"; 
                    nodo.setType("type_error");
                } else {
                    if (symbol.getTipo().isArray()) {
                        expression = typeExpression(expression);
                        if (expression.getType().equals("int")) {
                            nodo.setType(symbol.getTipo().getType_name());
                        } else {
                            error = "integer type needed in array index"; 
                            nodo.setType("type_error");
                        }
                    } else {
                        error = id.getText() + " is not an Array"; 
                        nodo.setType("type_error");
                    }
                }
            } break; 
            case 6: {
                myNode id = body.get(0); 
                myNode expression = body.get(2); 
                Symbol symbol = getSymbol(id.getText());
                if (symbol == null) {
                    error = id.getText() + " does not exist"; 
                    nodo.setType("type_error");
                } else {
                    if (symbol.getTipo().isArray()) {
                        expression = typeExpression(expression);
                        if (expression.getType().equals("int")) {
                            if (symbol.getTipo().isStructure()) { 
                                Struct struct = getStruct(symbol.getTipo().getType_name()); 
                                if (struct == null) { 
                                    error = symbol.getTipo().getType_name() + " struct does nor exist"; 
                                    nodo.setType("type_error"); 
                                } else {
                                    scope_memory = actual; 
                                    actual = struct.getInnerScope();
                                    myNode location = body.get(5);
                                    location = typeLocation(location);
                                    actual = scope_memory;
                                    nodo.setType(location.getType());
                                }
                            } else { 
                                error = symbol.getId() + "is not a struct"; 
                                nodo.setType("type_error");
                            }
                        } else {
                            error = "integer type needed in array index"; 
                            nodo.setType("type_error");
                        }
                    } else {
                        error = id.getText() + " is not an Array"; 
                        nodo.setType("type_error");
                    }
                }
            } break;     
        }
        return nodo;
    }
    private myNode typeExpression(myNode nodo) {
        if (nodo.getAllChildren().size() == 1) {
            myNode relExp = nodo.getChild(0);
            relExp = typeRelExp(relExp);
            nodo.setType(relExp.getType());
        } else {
            myNode relExp = nodo.getChild(0);
            myNode expression = nodo.getChild(2); 
            
            relExp = typeRelExp(relExp); 
            expression = typeExpression(expression);
            
            if (relExp.getType().equals("boolean") && expression.getType().equals("boolean" )) {
                nodo.setType("boolean");
            } else { 
                nodo.setType("type_error");
                error = "Boolean expressions expected at both sides of conditionalop"; 
            }
        }
        return nodo;
    }
    
    private myNode typeRelExp(myNode nodo) {
        if (nodo.getAllChildren().size() == 1) {
            myNode asgExp = nodo.getChild(0);
            asgExp = typeAsgExp(asgExp);
            nodo.setType(asgExp.getType());
        } else {
            myNode asgExp = nodo.getChild(0);
            myNode relExp = nodo.getChild(2); 
            
            asgExp = typeAsgExp(asgExp); 
            relExp = typeRelExp(relExp);
            
            if (relExp.getType().equals("int") && relExp.getType().equals("int" )) {
                nodo.setType("boolean");
            } else { 
                nodo.setType("type_error");
                error = "INT expressions expected at both sides of relOp op"; 
            }
        }
        
        return nodo; 
    }
    
    
    private myNode typeAsgExp(myNode nodo) {
        if (nodo.getAllChildren().size() == 1) {
            myNode addExp = nodo.getChild(0);
            addExp = typeAddExp(addExp);
            nodo.setType(addExp.getType());
        } else {
            myNode addExp = nodo.getChild(0);
            myNode asgExp = nodo.getChild(2); 
            
            addExp = typeAddExp(addExp); 
            asgExp = typeAsgExp(asgExp);
            
            if (!asgExp.getType().equals("void") && !addExp.getType().equals("void") && !asgExp.getType().equals("type_error") && !addExp.getType().equals("type_error") && addExp.getType().equals(asgExp.getType())) {
                nodo.setType("boolean");
            } else { 
                nodo.setType("type_error");
                error = "same type expressions expected at both sides of eqOp op"; 
            }
        }
        return nodo; 
    }
    
    private myNode typeAddExp(myNode nodo) {
        if (nodo.getAllChildren().size() == 1) {
            myNode mulExp = nodo.getChild(0);
            mulExp = typeMulExp(mulExp);
            nodo.setType(mulExp.getType());
        } else {
            myNode mulExp = nodo.getChild(0);
            myNode addExp = nodo.getChild(2); 
            
            mulExp = typeMulExp(mulExp); 
            addExp = typeAddExp(addExp);
            
            if (addExp.getType().equals("int") && mulExp.getType().equals("int" )) {
                nodo.setType("int");
            } else { 
                nodo.setType("type_error");
                error = "INT expressions expected at both sides of relOp op"; 
            }
        }
        
        return nodo; 
    }
    
    private myNode typeMulExp(myNode nodo) {
        if (nodo.getAllChildren().size() == 1) {
            myNode negExp = nodo.getChild(0);
            negExp = typeNegExp(negExp);
            nodo.setType(negExp.getType());
        } else {
            myNode negExp = nodo.getChild(0);
            myNode mulExp = nodo.getChild(2); 
            
            mulExp = typeMulExp(mulExp); 
            negExp = typeNegExp(negExp);
            
            if (negExp.getType().equals("int") && mulExp.getType().equals("int" )) {
                nodo.setType("int");
            } else { 
                nodo.setType("type_error");
                error = "INT expressions expected at both sides of relOp op"; 
            }
        }
        
        return nodo; 
    }
    
    private myNode typeNegExp(myNode nodo) {
        
        
        return nodo; 
    }
    
    private Scope nextUnchecked(Scope scope) {
        boolean found = false;
        int i = 0;
        Scope next = null; 
        while ((i < scope.getSiguientes().size())&&(!found)){
            next = scope.getNext(i);
            found = !(next.isChecked());
            i++; 
        }
        return next;
    }
    
    private Method getMethod(String id) {
        boolean found = false;
        int i = 0;
        Method proto = new Method();
        proto.setId(id);
        proto.setScope(actual);
        Method method = null; 
        while ((i < methodTable.size())&&(!found)){
            method = methodTable.get(i);
            found = (method.equal(proto)); 
        }
        return method;
    }
    
    private Struct getStruct(String id) {
        boolean found = false;
        int i = 0;
        Struct proto = new Struct();
        proto.setId(id);
        proto.setScope(actual);
        Struct struct = null; 
        while ((i < methodTable.size())&&(!found)){
            struct = structTable.get(i);
            found = (struct.equal(proto)); 
        }
        return struct;
    }
    
    private Symbol getSymbol(String id) {
        boolean found = false;
        int i = 0;
        Symbol proto = new Symbol();
        proto.setId(id);
        proto.setScope(actual);
        Symbol symbol = null; 
        while ((i < methodTable.size())&&(!found)){
            symbol = symbolTable.get(i);
            found = (symbol.equal(proto)); 
        }
        return symbol;
    }
}
