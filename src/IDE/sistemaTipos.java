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
public class sistemaTipos {
    ArrayList<Symbol> symbolTable; 
    ArrayList<Method> methodTable;
    ArrayList<Struct> structTable;
    ArrayList<ArrayList<Symbol>> paramsList;
    ArrayList<Scope> scopes; 
    
    Scope actual, lastScope;
    String error; 
    
    myNode tree; 
    
    public sistemaTipos(myNode tree) { 
        symbolTable = new ArrayList<>();
        methodTable = new ArrayList<>(); 
        structTable = new ArrayList<>(); 
        paramsList = new ArrayList<>(); 
        scopes = new ArrayList<>(); 
        
        actual = new Scope("0");
        lastScope = actual; 
        
        error = ""; 
        scopes.add(actual);
        this.tree = typeProgram(tree); 
        
        int i = 0;
        String errorBM = error; 
        String typeBM = tree.getType(); 
        
        boolean mainFound = false;
        while (i < methodTable.size() && !mainFound) {
            Method method = methodTable.get(i);
            if (method.getId().equals("main")) {
                Scope scope = method.getInnerScope(); 
                ArrayList<Symbol> sign = paramsList.get(Integer.parseInt(scope.getName())); 
                if (sign.size() == 0) {
                    Method methodMain = searchMethodByID("main"); 
                    Type mainType = methodMain.getType(); 
                    if (!mainType.getType_name().equals("void")) {
                        tree.setType("type_error");
                        error += "main method must be void" ;
                    } else {
                        mainFound = true; 
                    }
                } else {
                    tree.setType("type_error");
                    error += "main must not have arguments";
                }
            } else {
                tree.setType("type_error");
                error += "main not found" ;
            }
            i++;
        }
        if (mainFound) {
            error = errorBM;
            tree.setType(typeBM);
        }
        if (methodTable.size() == 0) {
            tree.setType("type_error");
            error += "main not found" ;
        }
    }
    
    private myNode typeProgram(myNode nodo) { 
        myNode mulDecl = nodo.getChild(3);
        mulDecl = typeMulDecl(mulDecl);
        nodo.setType(mulDecl.getType());
        return nodo; 
    }
    
    private myNode typeMulDecl(myNode nodo) { 
        boolean isVoid = true; 
        for (int i = 0; i < nodo.getAllChildren().size(); i++) { 
            myNode declaration = nodo.getChild(i);
            declaration = declaration.getChild(0);

            String nodeText = declaration.getText(); 

            if (nodeText.equals("varDeclaration")) {
                declaration = addVarDeclaration(declaration);
            } else if (nodeText.equals("structDeclaration")) {
                declaration = addStructDeclaration(declaration); 
            } else if (nodeText.equals("methodDeclaration")) {
                declaration = addMethodDeclaration(declaration);
            }; 
            
            isVoid = (isVoid && declaration.getType().equals("void")); 
        } 
        if (isVoid) {
            nodo.setType("void");
        } else {
            nodo.setType("type_error");
        }
        return nodo; 
    }
    
    private myNode addVarDeclaration(myNode nodo) {
        boolean goodDec = true;
        myNode varType = nodo.getChild(0);
        myNode id = nodo.getChild(1); 
        
        Type type = getVarType(varType);
        
        
        Symbol symbol = new Symbol();
        
        symbol.setId(id.getText());
        symbol.setScope(actual);
        symbol.setTipo(type);
        
        if (nodo.getAllChildren().size()>3) {
            myNode sizeNode = nodo.getChild(3);
            int size = Integer.parseInt(sizeNode.getText());
            if (size > 0) { 
                type.setArray(true,size);
            } else {
                error += "El tamaño del array \""+symbol.getId()+"\" del ambito \""+symbol.getScope().getName()+"\"debe de ser mayor a 0\n"; 
                goodDec = false;
                nodo.setType("type_error");
            }
        }
        if (!existID(symbol) && goodDec){
            symbolTable.add(symbol);
            nodo.setType("void");
        } else if (goodDec){ 
            error += "ID \""+symbol.getId()+"\" ha sido declarado mas de una vez en el ambito \""+symbol.getScope().getName()+"\"\n";
            nodo.setType("type_error");
        }
        return nodo; 
    }
    
    private myNode addStructDeclaration(myNode nodo) {
        myNode id = nodo.getChild(1); 
        
        Struct struct = new Struct(); 
        struct.setId(id.getText());
        struct.setScope(actual);
        if (!existID(struct)) {  
            lastScope = actual;
            actual = newScope(actual);
            struct.setInnerScope(actual);
            structTable.add(struct);
            for (int i = 0; i < nodo.getAllChildren().size(); i++){
                myNode node = nodo.getChild(i); 
                if (node.getText().equals("varDeclaration")) {
                    addVarDeclaration(node); 
                }
            }
            actual = lastScope; 
            nodo.setType("void");
        } else { 
            error += "ID \""+id.getText()+"\" ha sido declarado mas de una vez en el ambito "+struct.getScope().getName()+"\n";
            nodo.setType("type_error"); 
        }
        return nodo; 
    }
    
    
    
    private myNode addMethodDeclaration(myNode nodo) {
        myNode typeNode = nodo.getChild(0);
        myNode idNode = nodo.getChild(1);
        
        Method method = new Method(); 
        Type type = getVarType(typeNode);
        method.setId(idNode.getText());
        method.setType(type);
        method.setScope(actual);
        boolean goodDec = true; 
        if (!existID(method)) {
            myNode parameters = nodo.getChild(3);
            lastScope = actual;
            actual = newScope(actual); 
            method.setInnerScope(actual);
            if (parameters.getAllChildren().size()>0) {
                goodDec = addParameters(parameters.getChild(0));
            }
            actual = lastScope; 
            if (goodDec) {
                methodTable.add(method);
                getParams(method);
                lastScope = actual; 
                actual = method.getInnerScope();
                myNode blockNode = nodo.getChild(5); 
                blockNode = typeBlock(blockNode);
                actual = lastScope; 
                nodo.setType(blockNode.getType());
                if (!nodo.getType().equals(type.getType_name())) {
                    error += "Invalid METHOD DECLARATION "+method.getId()+"- Return must be the same type as header\n";
                    nodo.setType("type_error"); 
                }
            } else { 
                eraseMethodTrack(method); 
            }
        } else {
            error += "ID \""+idNode.getText()+"\" ha sido declarado mas de una vez en el ambito "+method.getScope().getName()+"\n";
            nodo.setType("type_error"); 
        }
        
        return nodo; 
    }
    
    private myNode typeBlock(myNode nodo) {
        ArrayList<myNode> body = nodo.getAllChildren();
        nodo.setType("void");
        for (int i = 0; i < body.size(); i++) {
            myNode temp = body.get(i); 
            if (temp.getText().equals("varDeclaration")) {
                temp = addVarDeclaration(temp); 
            } else if (temp.getText().equals("statement")) { 
                temp = typeStatement(temp);
            }
            if (temp.getType().equals("int")||temp.getType().equals("char")||temp.getType().equals("boolean")) {
                nodo.setType(temp.getType());
            } 
            if (temp.getType().equals("type_error")) {
                nodo.setType(temp.getType());
            }
        }
        return nodo; 
    }
    
    private myNode typeStatement(myNode nodo) {
        myNode statement = nodo.getChild(0);
        if (statement.getText().equals("statement_cerrado")) {
            statement = typeStatementCerrado(statement); 
        } else if (statement.getText().equals("statement_abierto")) {
            statement = typeStatementAbierto(statement);
        } 
        nodo.setType(statement.getType());
        return nodo; 
    }
    
    private myNode typeStatementCerrado(myNode nodo) {
        myNode firstNode = nodo.getChild(0);
        String nodeText = firstNode.getText(); 
        if (nodeText.equals("if")){
            myNode expression = nodo.getChild(2);
            myNode stmtCerrado1 = nodo.getChild(4);
            myNode stmtCerrado2 = nodo.getChild(6);
            
            expression = typeExpression(expression);
            stmtCerrado1 = typeStatementCerrado(stmtCerrado1); 
            stmtCerrado2 = typeStatementCerrado(stmtCerrado2); 
            
            if (expression.getType().equals("boolean") && stmtCerrado1.getType().equals("void")&& stmtCerrado2.getType().equals("void")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            
            if (!expression.getType().equals("boolean")) {
                error += "Boolean expression expected in IF declaration";
            }
        } else if (nodeText.equals("while")) {
            myNode expression = nodo.getChild(2);
            myNode block = nodo.getChild(4);
             
            expression = typeExpression(expression);
            block = typeBlock(block);
            
            if (expression.getType().equals("boolean") && block.getType().equals("void")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            
            if (!expression.getType().equals("boolean")) {
                error += "Boolean expression expected in WHILE declaration";
            }
        } else if (nodeText.equals("return")) {
            myNode expressionA = nodo.getChild(1);
            
            expressionA = typeExpressionA(expressionA); 
            
            nodo.setType(expressionA.getType());
        }else if (nodeText.equals("methodCall")) {
            firstNode = typeMethodCall(firstNode);
            
            if (!firstNode.getType().equals("type_error")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
        }else if (nodeText.equals("block")) {
            firstNode = typeBlock(firstNode);
            
            if (!firstNode.getType().equals("type_error")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
        }else if (nodeText.equals("location")) {
            myNode location = firstNode; 
            myNode expression = nodo.getChild(2);
            
            location = typeLocation(location);
            expression = typeExpression(expression); 
            
            if (location.getType().equals(expression.getType())) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
                error += expression.getType()+" cannot be store in "+location.getType()+" variable\n";
            }
        }else if (nodeText.equals("expression")) {
            firstNode = typeExpression(firstNode); 
            
            if (!firstNode.getType().equals("type_error")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
        }else if (nodeText.equals(";")) {
            nodo.setType("void");
        } 
        return nodo; 
    }
    
    private myNode typeStatementAbierto(myNode nodo) {
        if (nodo.getAllChildren().size() == 5) {
            myNode expression = nodo.getChild(2);
            myNode statement = nodo.getChild(4);
            
            expression = typeExpression(expression);
            statement = typeStatement(statement); 
            
            if (expression.getType().equals("boolean") && !statement.getType().equals("type_error")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            if (!expression.getType().equals("boolean")) {
                error += "Boolean expression expected in IF declaration";
            }
        } else {
            myNode expression = nodo.getChild(2);
            myNode statementCerrado = nodo.getChild(4);
            myNode statementAbierto = nodo.getChild(6); 
            
            expression = typeExpression(expression);
            statementCerrado = typeStatementCerrado(statementCerrado); 
            statementAbierto = typeStatementAbierto(statementAbierto); 
            
            if (expression.getType().equals("boolean") && !statementCerrado.getType().equals("type_error") && !statementAbierto.getType().equals("type_error")) {
                nodo.setType("void");
            } else {
                nodo.setType("type_error");
            }
            if (!expression.getType().equals("boolean")) {
                error += "Boolean expression expected in IF declaration";
            }
        }
        return nodo; 
    }
    
    private myNode typeExpressionA (myNode nodo) {
        if (nodo.getAllChildren().size() > 0) {
            myNode expression = nodo.getChild(0);
            expression = typeExpression(expression);
            nodo.setType(expression.getType()); 
        } else {
            nodo.setType("void");
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
            
            if (relExp.getType().equals("boolean") && expression.getType().equals("boolean")) {
                nodo.setType("boolean");
            } else {
                error += "\""+ nodo.getChild(1).getChild(0).getText()+"\" needs 2 boolean expressions\n";
                nodo.setType("type_error");
            }
        }
        return nodo; 
    }
    
    private myNode typeRelExp(myNode nodo) {
        if (nodo.getAllChildren().size() == 1) {
            myNode eqExp = nodo.getChild(0);
            
            eqExp = typeEqExp(eqExp);
            
            nodo.setType(eqExp.getType());
        } else {
            myNode relExp = nodo.getChild(2);
            myNode eqExp = nodo.getChild(0);
            
            relExp = typeRelExp(relExp);
            eqExp = typeEqExp(eqExp); 
            
            if (relExp.getType().equals("int")&&eqExp.getType().equals("int")) {
                nodo.setType("int");
            } else {
                error += "\""+ nodo.getChild(1).getChild(0).getText() +"\" needs 2 int expressions\n";
                nodo.setType("type_error");
            }
        }
        return nodo; 
    }
    
    private myNode typeEqExp(myNode nodo ){
        if (nodo.getAllChildren().size() == 1) {
            myNode addExp = nodo.getChild(0);
            
            addExp = typeAddExp(addExp);
            
            nodo.setType(addExp.getType());
        } else {
            myNode addExp = nodo.getChild(0);
            myNode eqExp = nodo.getChild(2);
            
            addExp = typeAddExp(addExp);
            eqExp = typeEqExp(eqExp); 
            
            if (addExp.getType().equals(eqExp.getType())&&!(addExp.getType().equals("type_error")||addExp.getType().equals("void"))) {
                nodo.setType("boolean");
            } else {
                error += "\""+ nodo.getChild(1).getChild(0).getText() +"\" needs 2 expressions of the same type\n";
                
                nodo.setType("type_error");
            }
        }
        return nodo;  
    }
    
    private myNode typeAddExp(myNode nodo ){
        if (nodo.getAllChildren().size() == 1) {
            myNode mulExp = nodo.getChild(0);
            
            mulExp = typeMultExp(mulExp);
            
            nodo.setType(mulExp.getType());
        } else {
            myNode addExp = nodo.getChild(2);
            myNode mulExp = nodo.getChild(0);
            
            addExp = typeAddExp(addExp);
            mulExp = typeMultExp(mulExp); 
            
            if (mulExp.getType().equals("int")&&addExp.getType().equals("int")) {
                nodo.setType("int");
            } else {
                error += "\""+ nodo.getChild(1).getChild(0).getText() +"\" needs 2 int expressions\n";
                
                nodo.setType("type_error");
            }
        }
        return nodo;  
    }
    
    private myNode typeMultExp(myNode nodo ){
        if (nodo.getAllChildren().size() == 1) {
            myNode negExp = nodo.getChild(0);
            
            negExp = typeNegateExp(negExp);
            
            nodo.setType(negExp.getType());
        } else {
            myNode negExp = nodo.getChild(0);
            myNode mulExp = nodo.getChild(2);
            
            negExp = typeNegateExp(negExp);
            mulExp = typeMultExp(mulExp); 
            
            if (mulExp.getType().equals("int")&&negExp.getType().equals("int")) {
                nodo.setType("int");
            } else {
                error += "\""+ nodo.getChild(1).getChild(0).getText() +"\" needs 2 int expressions\n";
                nodo.setType("type_error");
            }
        }
        return nodo;  
    }
    
    private myNode typeNegateExp(myNode nodo) {
        myNode ind = nodo.getChild(0); 
        if (ind.getText().equals("value")) {
            ind = typeValue(ind);
            nodo.setType(ind.getType());
        } else if (ind.getText().equals("!")) {
            ind = typeValue(ind);
            if (ind.getType().equals("boolean")) {
                nodo.setType(ind.getType());
            } else {
                error += "\"!\" needs a boolean expression\n";
                
                nodo.setType("type_error");
            }
        } else if (ind.getText().equals("-")) {
            ind = typeValue(ind);
            if (ind.getType().equals("int")) {
                nodo.setType(ind.getType());
            } else {
                error += "\"-\" needs a int expression\n";
                nodo.setType("type_error");
            }
        }  
        return nodo; 
    }
    
    private myNode typeValue(myNode nodo) {
        myNode ind = nodo.getChild(0);
        if (ind.getText().equals("literal")) {
            ind = typeLiteral(ind);
            nodo.setType(ind.getType());
        } else if (ind.getText().equals("(")) {
            ind = nodo.getChild(1);
            ind = typeExpression(ind);
            nodo.setType(ind.getType());
        } else if (ind.getText().equals("methodCall")) {
            ind = typeMethodCall(ind);
            nodo.setType(ind.getType());
        } else if (ind.getText().equals("location")) {
            ind = typeLocation(ind);
            nodo.setType(ind.getType());
        } 
        return nodo; 
    }
    
    
    private myNode typeLocation(myNode nodo) {
        ArrayList<myNode> body = nodo.getAllChildren(); 
        myNode idNode = nodo.getChild(0); 
        Symbol symbol = searchSymbolByName(idNode.getText());
        if (symbol != null) {
            Type symbolType = symbol.getTipo(); 
            if (body.size() == 1) {
                if (symbolType.isArray()) {
                    error += "ID "+idNode.getText()+" es un array en el ambito "+actual.getName()+", falta el indice necesario\n";
                    nodo.setType("type_error");
                } else if (symbolType.isStructure()) {
                    error += "ID "+idNode.getText()+" es un struct en el ambito "+actual.getName()+", falta el atributo necesario\n";
                    nodo.setType("type_error");
                } else {
                    nodo.setType(symbol.getTipo().getType_name());
                }  
            } else if (body.size() == 3) {
                if (symbolType.isArray()) {
                    error += "ID "+idNode.getText()+" es un array en el ambito "+actual.getName()+", falta el indice necesario\n";
                    nodo.setType("type_error");
                } else if (!symbolType.isStructure()) {
                    error += "ID "+idNode.getText()+" no es un struct en el ambito "+actual.getName()+"\n";
                    nodo.setType("type_error");
                } else {
                    String structName = symbolType.getType_name(); 
                    Struct struct = searchStructByName(structName); 
                    lastScope = actual; 
                    actual = struct.getInnerScope();
                    myNode location = nodo.getChild(2);
                    location = typeLocation(location);
                    nodo.setType(location.getType());
                    actual = lastScope; 
                }
            } else if (body.size() == 4) {
                if (!symbolType.isArray()) {
                    error += "ID "+idNode.getText()+" no es un array en el ambito "+actual.getName()+"\n";
                    nodo.setType("type_error");
                } else if (symbolType.isStructure()) {
                    error += "ID "+idNode.getText()+"es un struct en el ambito "+actual.getName()+", falta el atributo necesario\n";
                    nodo.setType("type_error");
                } else {
                    myNode expression = nodo.getChild(2);
                    expression = typeExpression(expression); 
                    if (expression.getType().equals("int")) {       
                        nodo.setType(symbol.getTipo().getType_name());
                    } else {
                        nodo.setType("type_error");
                        error += "Expression in a array index must be int\n";
                    }
                }  
            } else if (body.size() == 6) {
                if (!symbolType.isArray()) {
                    error += "ID "+idNode.getText()+" no es un array en el ambito "+actual.getName()+"\n";
                    nodo.setType("type_error");
                } else if (!symbolType.isStructure()) {
                    error += "ID "+idNode.getText()+"no es un struct en el ambito "+actual.getName()+", falta el atributo necesario\n";
                    nodo.setType("type_error");
                } else {
                    myNode expression = nodo.getChild(2);
                    expression = typeExpression(expression); 
                    if (expression.getType().equals("int")) {       
                        String structName = symbolType.getType_name(); 
                        Struct struct = searchStructByName(structName); 
                        lastScope = actual; 
                        actual = struct.getInnerScope();
                        myNode location = nodo.getChild(5);
                        location = typeLocation(location);
                        nodo.setType(location.getType());
                        actual = lastScope;
                    }else {
                        nodo.setType("type_error");
                        error += "Expression in a array index must be int\n";
                    }
                }
            }
        } else {
                error += "Variable "+idNode.getText()+" no existe en el ambito "+actual.getName()+"\n";
                nodo.setType("type_error"); 
        }
        return nodo;
    }
    
    private Struct searchStructByName (String id) {
        Struct struct = null;
        int i = 0; 
        boolean found = false; 
        while (i < structTable.size() && !found) {
            Struct temp = structTable.get(i);
            if (temp.getId().equals(id)) {
                Scope scope = temp.getScope();
                ArrayList reach = new ArrayList(); 
                reach = scopeReach(reach,scope);
                if (reach.contains(actual.getName())) {
                    found = true; 
                    struct = temp; 
                }
            }
            i++; 
        }
    return struct;
    }
    private Symbol searchSymbolByName(String id){
        Symbol symbol = null;
        int i = 0; 
        boolean found = false; 
        while (i < symbolTable.size() && !found) {
            Symbol temp = symbolTable.get(i);
            if (temp.getId().equals(id)) {
                Scope scope = temp.getScope();
                ArrayList reach = new ArrayList(); 
                reach = scopeReach(reach,scope);
                if (reach.contains(actual.getName())) {
                    found = true; 
                    symbol = temp; 
                }
            }
            i++; 
        }
        return symbol; 
    }
    private myNode typeMethodCall(myNode nodo) {
        myNode idNode = nodo.getChild(0);
        myNode args = nodo.getChild(2);
        ArrayList params = paramsDecl(args); 
        Method method = searchMethodByID(idNode.getText()); 
        if (method != null) {
            ArrayList<Symbol> sign = paramsList.get(Integer.parseInt(method.getInnerScope().getName()));

            if (params.size() == sign.size()) {
                    String sign1 = "";
                    String sign2 = ""; 
                    for (int i = 0; i < params.size(); i++) {
                        sign1 += params.get(i)+",";
                        sign2 += sign.get(i).getTipo().getType_name()+",";
                    }
                    sign1 = sign1.substring(0,sign1.length()-1);
                    sign2 = sign2.substring(0,sign2.length()-1);
                    if (sign1.equals(sign2)) {
                        nodo.setType(method.getType().getType_name());
                    } else {
                        error += "Firma esperada para el metodo \""+method.getId()+"\":"+sign2+". Encontrada: "+sign1;
                        nodo.setType("type_error");
                    }
            } else {
                error += "Cantidad de parametros en llamada a metodo \""+method.getId()+"\" incorrecta. ";
                nodo.setType("type_error");
            }
        } else { 
            error += "Metodo \""+method.getId()+"\" no existe. ";
            nodo.setType("type_error");
        } 
        return nodo; 
    }
    
    private Method searchMethodByID(String id) {
        Method method = null;
        boolean found = false; 
        int i = 0; 
      
        while ((i < methodTable.size())&&(!found)) {
            method = methodTable.get(i);
            if (method.getId().equals(id)) {
                ArrayList alcance = new ArrayList();
                alcance = scopeReach(alcance, method.getScope()); 
                if (!alcance.contains(actual.getName())) { 
                   method = null;
                } else {
                    found = true;
                }
            } else {
                method = null;
            }
            i++; 
        }
        return method;
    }
    
    private ArrayList scopeReach(ArrayList reach, Scope scope) {
        if (scope.getSiguientes().size() == 0) {
            reach.add(scope.getName());
        } else {
            reach.add(scope.getName());
            ArrayList<Scope> next = scope.getSiguientes(); 
            for (int i = 0; i < next.size(); i++) {
                reach = scopeReach(reach,next.get(i)); 
            }
        }
        return reach; 
    }
    
    private ArrayList paramsDecl(myNode nodo) { 
        ArrayList firma = new ArrayList(); 
        if (nodo.getAllChildren().size() > 0) {
            myNode arg = nodo.getChild(0);
            ArrayList<myNode> args = arg.getAllChildren();
            for (int i = 0; i < args.size(); i++) {
                myNode temp = args.get(i); 
                if (temp.getText().equals("arg")){
                    myNode expression = temp.getChild(0);
                    expression = typeExpression(expression);
                    temp.setType(expression.getType());
                    firma.add(temp.getType());
                }
            }
        }
        return firma;
    }
    
    private myNode typeLiteral(myNode nodo) {
        myNode typeNode = nodo.getChild(0); 
        if (typeNode.getText().equals("int_literal")) {
            nodo.setType("int");
        } else if (typeNode.getText().equals("char_literal")) {
            nodo.setType("char");
        } else if (typeNode.getText().equals("bool_literal")) {
            nodo.setType("boolean");
        } 
        return nodo; 
    }
    
    private boolean addParameters(myNode nodo) {
        boolean goodDec = true; 
            ArrayList<myNode> parameterList = nodo.getAllChildren(); 
            for (int i = 0; i < parameterList.size(); i++) {
                myNode param = parameterList.get(i);
                if (param.getText().equals("parameter")) {
                    myNode typeNode = param.getChild(0);
                    myNode idNode = param.getChild(1); 
                    
                    Type type = getVarType(typeNode); 
                    type.setParam(true);
                    Symbol symbol = new Symbol(); 
                    
                    symbol.setId(idNode.getText());
                    symbol.setTipo(type);
                    symbol.setScope(actual);
                   
                    if (!existID(symbol)) {
                        symbolTable.add(symbol); 
                    } else {
                        error += "ID \""+symbol.getId()+"\" ha sido declarado mas de una vez en el ambito \""+symbol.getScope().getName()+"\"\n";
                        nodo.setType("type_error");
                        goodDec = false; 
                    }
                } else if (param.getText().equals("parameter_list")){
                    goodDec = addParameters(param);
                }
            }    
        return goodDec; 
    }
    
    public void getParams(Method method) {
        Scope scope = method.getInnerScope(); 
        ArrayList<Symbol> list = new ArrayList<>(); 
        for (int i = 0; i < symbolTable.size(); i++ ){
            Symbol symbol = symbolTable.get(i);
            Scope symScope = symbol.getScope(); 
            if (symScope.getName().equals(scope.getName())) {
                list.add(symbol); 
            }
        }
        paramsList.add(Integer.parseInt(scope.getName()),list);
    }
    
    private void eraseMethodTrack(Method method) {
        Scope scope = method.getInnerScope(); 
        int i = 0;
        while ( i < symbolTable.size()) {
            Symbol temp = symbolTable.get(i);
            if (temp.getScope().getName().equals(scope.getName())) { 
                symbolTable.remove(i); 
            } else  {
                i++; 
            }
        }
    }
    
    private Scope newScope(Scope currentScope) {
        Scope newScope = new Scope(String.valueOf(scopes.size())); 
        newScope.setAnterior(currentScope);
        currentScope.addNext(newScope);
        scopes.add(newScope);
        ArrayList<Symbol> n = new ArrayList<>();
        paramsList.add(n); 
        return newScope;
    }
    
    private Type getVarType(myNode nodo){
        myNode typeNode = nodo.getChild(0);
        String nodeText = typeNode.getText(); 
        String typeName = ""; 
        
        boolean simple = false; 
        boolean struct = false; 
        if (nodeText.equals("int")||nodeText.equals("char")||nodeText.equals("boolean")||nodeText.equals("void")) {
            typeName = nodeText; 
            simple = true;
        }  else if (nodeText.equals("struct")) {
            typeName = nodo.getChild(1).getText();
            struct = true;
        } else if (nodeText.equals("structDeclaration")) { 
            struct = true; 
            myNode id = typeNode.getChild(1); 
            typeName = "struct "+id.getText();
            addStructDeclaration(typeNode); 
        }
        Type type = new Type(typeName);
        type.setSimple(simple);
        type.setStructure(struct);
        return type;
    }
    
    private boolean existSymbol(Symbol symbol){
        boolean found = false;
        for (int i = 0; i < symbolTable.size(); i++){
            Symbol temp = symbolTable.get(i);
            if (temp.getId().equals(symbol.getId()) && temp.getScope().getName().equals(symbol.getScope().getName())) {
                return true; 
            }
        }
        return found;
    }
    
    private boolean existStruct(Struct struct){
        boolean found = false;
        for (int i = 0; i < structTable.size(); i++){
            Struct temp = structTable.get(i);
            if (temp.getId().equals(struct.getId()) && temp.getScope().getName().equals(struct.getScope().getName())) {
                return true; 
            }
        }
        return found;
    }
    
    private boolean existID(Symbol symbol) { 
        boolean found = false;
        for (int i = 0; i < symbolTable.size(); i++){
            Symbol temp = symbolTable.get(i);
            if (temp.getId().equals(symbol.getId()) && temp.getScope().getName().equals(symbol.getScope().getName())) {
                found = true; 
            }
        }
        for (int i = 0; i < structTable.size(); i++){
            Struct temp = structTable.get(i);
            if (temp.getId().equals(symbol.getId()) && temp.getScope().getName().equals(symbol.getScope().getName())) {
                found = true; 
            }
        }
        for (int i = 0; i < methodTable.size(); i++){
            Method temp = methodTable.get(i);
            if (temp.getId().equals(symbol.getId()) && temp.getScope().getName().equals(symbol.getScope().getName())) {
                found = true; 
            }
        }
        return found; 
    }
    
    private boolean existID(Struct struct) { 
        boolean found = false;
        for (int i = 0; i < symbolTable.size(); i++){
            Symbol temp = symbolTable.get(i);
            if (temp.getId().equals(struct.getId()) && temp.getScope().getName().equals(struct.getScope().getName())) {
                found = true; 
            }
        }
        for (int i = 0; i < structTable.size(); i++){
            Struct temp = structTable.get(i);
            if (temp.getId().equals(struct.getId()) && temp.getScope().getName().equals(struct.getScope().getName())) {
                found = true; 
            }
        }
        for (int i = 0; i < methodTable.size(); i++){
            Method temp = methodTable.get(i);
            if (temp.getId().equals(struct.getId()) && temp.getScope().getName().equals(struct.getScope().getName())) {
                found = true; 
            }
        }
        return found; 
    }
    
    private boolean existID(Method method) { 
        boolean found = false;
        for (int i = 0; i < symbolTable.size(); i++){
            Symbol temp = symbolTable.get(i);
            if (temp.getId().equals(method.getId()) && temp.getScope().getName().equals(method.getScope().getName())) {
                found = true; 
            }
        }
        for (int i = 0; i < structTable.size(); i++){
            Struct temp = structTable.get(i);
            if (temp.getId().equals(method.getId()) && temp.getScope().getName().equals(method.getScope().getName())) {
                found = true; 
            }
        }
        for (int i = 0; i < methodTable.size(); i++){
            Method temp = methodTable.get(i);
            if (temp.getId().equals(method.getId()) && temp.getScope().getName().equals(method.getScope().getName())) {
                found = true; 
            }
        }
        return found; 
    }
    
    public ArrayList<Symbol> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(ArrayList<Symbol> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ArrayList<Method> getMethodTable() {
        return methodTable;
    }

    public void setMethodTable(ArrayList<Method> methodTable) {
        this.methodTable = methodTable;
    }

    public ArrayList<Struct> getStructTable() {
        return structTable;
    }

    public void setStructTable(ArrayList<Struct> structTable) {
        this.structTable = structTable;
    }

    public ArrayList<ArrayList<Symbol>> getParamsList() {
        return paramsList;
    }

    public void setParamsList(ArrayList<ArrayList<Symbol>> paramsList) {
        this.paramsList = paramsList;
    }

    public ArrayList<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(ArrayList<Scope> scopes) {
        this.scopes = scopes;
    }

    public Scope getActual() {
        return actual;
    }

    public void setActual(Scope actual) {
        this.actual = actual;
    }

    public String getError() {
        if (error.equals("")) {
            error = "No hay errores semanticos"; 
        }
        return error;
    }

    public myNode getTree() {
        return tree;
    }
     
}
