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
public class tableBuilder {
    myNode tree; 
    
    ArrayList<Struct> structTable; 
    ArrayList<Symbol> symbolTable;
    ArrayList<Method> methodTable; 
    ArrayList<ArrayList<Symbol>> parameterList; 
    ArrayList<Scope> scopes; 
    
    ArrayList<Struct> checkedByType; 
    boolean allGood;
    String errors; 
    
    public tableBuilder(myNode arbolR){
        tree = arbolR; 
        
        //VAR INITIALLIZATION
        structTable = new ArrayList<>();
        symbolTable = new ArrayList<>();
        methodTable = new ArrayList<>();
        parameterList = new ArrayList<>(); 
        scopes = new ArrayList<>();
        allGood = true;
        errors = "";
        
        checkedByType = new ArrayList<>(); 
        //FIRST SCOPE -> GLOBAL
        Scope global = new Scope("0");
        addScope(global);
        
        //ADD TREE TO A LIST (BUILD REQUIERMENT)
        ArrayList<myNode> inicial = new ArrayList<>();
        inicial.add(tree);
        
        //BUILD
        build(inicial,scopes.get(0)); 
    }
    
    public void build(ArrayList<myNode> nodo,Scope scope_actual) { 
        if (isAllGood()) {
            for (int i = 0; i < nodo.size(); i++){
                myNode temp = nodo.get(i); 
                ArrayList<myNode> body = temp.getAllChildren(); 
                if (temp.getText().equals("varDeclaration")) { 
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1);
                    
                    Symbol symbol = new Symbol();
                    Type type = getType(typeNode.getAllChildren(),scope_actual);
                    
                    symbol.setId(idNode.getText());
                    symbol.setScope(scope_actual);
                    
                    if (body.size() > 3) {
                        int size = Integer.parseInt(body.get(3).getText()); 
                        if (size > 0) {
                            type.setArray(true);
                            type.setArray_len(size);
                        } else {
                            errors = "Array "+idNode.getText()+" size must be greater that 0";
                            allGood = false; 
                        }
                    } else { 
                        symbol.setTipo(type);
                    
                        allGood = addToSymbolTable(symbol); 
                        build(temp.getAllChildren(),scope_actual);
                    }    
                }
                else if (temp.getText().equals("structDeclaration")) { 
                    System.out.println(temp.getText()); 
                    myNode idNode = body.get(1);
                    
                    Struct struct = new Struct(); 
                    
                    struct.setId(idNode.getText());
                    struct.setScope(scope_actual);
                    if (!existStructInScope(struct)) {
                        Scope next_scope = new Scope(String.valueOf(scopes.size())); 
                        next_scope.setAnterior(scope_actual);
                        scope_actual.addNext(next_scope);
                        addScope(next_scope);

                        struct.setInnerScope(next_scope);

                        allGood = addToStructTable(struct);
                        build(temp.getAllChildren(),next_scope);
                    } else {
                        boolean inByType = false;
                        int k = 0; 
                        while (!inByType && (k < checkedByType.size())) {
                            Struct temp2 = checkedByType.get(k); 
                            inByType = temp2.equal(struct); 
                            k++;
                        }
                        if (!inByType) {
                            allGood = false;
                            errors = struct.getId()+" id defined multiple time in the same scope"; 
                        }
                    }
                }
                else if (temp.getText().equals("methodDeclaration")) { 
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1); 
                    
                    Method method = new Method(); 
                    Type type = getType(typeNode.getAllChildren(),scope_actual); 
                    
                    method.setId(idNode.getText());
                    method.setType(type);
                    method.setScope(scope_actual);
                    Scope next_scope = new Scope(String.valueOf(scopes.size())); 
                    next_scope.setAnterior(scope_actual);
                    scope_actual.addNext(next_scope);
                    addScope(next_scope);
                    build(temp.getAllChildren(),next_scope);
                    method.setInnerScope(next_scope);
                    if (!existMethodInScope(method)) {
                        allGood = addToMethodTable(method);
                    } else {
                        eraseMethodTrack(method);
                        allGood = false;
                        errors = method.getId() + " method, already defined i this scope"; 
                    }
                }
                else if (temp.getText().equals("parameter")) {
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1);
                    
                    Symbol symbol = new Symbol();
                    Type type = getType(typeNode.getAllChildren(),scope_actual);
                    type.setParam(true);
                    
                    symbol.setId(idNode.getText());
                    symbol.setTipo(type);
                    symbol.setScope(scope_actual);
                    
                    allGood = addToSymbolTable(symbol); 
                    addToParamList(symbol,scope_actual);
                    build(temp.getAllChildren(),scope_actual);
                }
                
                else {
                    build(temp.getAllChildren(),scope_actual);
                } 
            }
        } 
    } 
    
    public void specialBuild(ArrayList<myNode> nodo,Scope scope_actual) { 
        if (isAllGood()) {
            for (int i = 0; i < nodo.size(); i++){
                myNode temp = nodo.get(i); 
                ArrayList<myNode> body = temp.getAllChildren(); 
                if (temp.getText().equals("varDeclaration")) { 
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1);
                    
                    Symbol symbol = new Symbol();
                    Type type = getType(typeNode.getAllChildren(),scope_actual);
                    
                    symbol.setId(idNode.getText());
                    symbol.setScope(scope_actual);
                    
                    if (body.size() > 3) {
                        int size = Integer.parseInt(body.get(3).getText()); 
                        if (size > 0) {
                            type.setArray(true);
                            type.setArray_len(size);
                        } else {
                            errors = "Array "+idNode.getText()+" size must be greater that 0";
                            allGood = false; 
                        }
                    }
                    symbol.setTipo(type);
                    
                    allGood = addToSymbolTable(symbol); 
                    build(temp.getAllChildren(),scope_actual);
                }
                else if (temp.getText().equals("structDeclaration")) { 
                    System.out.println(temp.getText()); 
                    myNode idNode = body.get(1);
                    
                    Struct struct = new Struct(); 
                    
                    struct.setId(idNode.getText());
                    struct.setScope(scope_actual);
                    if (!existStructInScope(struct)) {
                        Scope next_scope = new Scope(String.valueOf(scopes.size())); 
                        next_scope.setAnterior(scope_actual);
                        scope_actual.addNext(next_scope);
                        addScope(next_scope);

                        struct.setInnerScope(next_scope);

                        allGood = addToStructTable(struct);
                        checkedByType.add(struct);
                        build(temp.getAllChildren(),next_scope);
                    } else {
                        boolean inByType = false;
                        int k = 0; 
                        while (!inByType && (k < checkedByType.size())) {
                            Struct temp2 = checkedByType.get(k); 
                            inByType = temp2.equal(struct); 
                            k++;
                        }
                        if (!inByType) {
                            allGood = false;
                            errors = struct.getId()+" id defined multiple time in the same scope"; 
                        
                        }
                    }
                }
                else if (temp.getText().equals("methodDeclaration")) { 
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1); 
                    
                    Method method = new Method(); 
                    Type type = getType(typeNode.getAllChildren(),scope_actual); 
                    
                    method.setId(idNode.getText());
                    method.setType(type);
                    method.setScope(scope_actual);
                    
                    Scope next_scope = new Scope(String.valueOf(scopes.size())); 
                    next_scope.setAnterior(scope_actual);
                    scope_actual.addNext(next_scope);
                    addScope(next_scope);
                            
                    method.setInnerScope(next_scope);
                    allGood = addToMethodTable(method);
                    build(temp.getAllChildren(),next_scope);
                }
                else if (temp.getText().equals("parameter")) {
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1);
                    
                    Symbol symbol = new Symbol();
                    Type type = getType(typeNode.getAllChildren(),scope_actual);
                    type.setParam(true);
                    
                    symbol.setId(idNode.getText());
                    symbol.setTipo(type);
                    symbol.setScope(scope_actual);
                    
                    allGood = addToSymbolTable(symbol); 
                    addToParamList(symbol,scope_actual);
                    build(temp.getAllChildren(),scope_actual);
                }
                else {
                    build(temp.getAllChildren(),scope_actual);
                } 
            }
        } 
    } 
    
    public Type getType(ArrayList<myNode> typeNodeBody,Scope scope){
        Type type = null; 
        myNode typeNode = typeNodeBody.get(0);
        if (typeNode.getText().equals("int")||typeNode.getText().equals("char")||typeNode.getText().equals("boolean")||typeNode.getText().equals("void")) {
            type = new Type(typeNode.getText());
            type.setSimple(true);
        } else if (typeNode.getText().equals("struct")) {
            type = new Type(typeNodeBody.get(1).getText());
            type.setStructure(true);
        } else if (typeNode.getText().equals("structDeclaration")) {
            ArrayList<myNode> structDeclaration = typeNode.getAllChildren(); 
            type = new Type(structDeclaration.get(1).getText());
            type.setStructure(true);
            specialBuild(typeNodeBody,scope);
            
        }
        return type; 
    }
    
    public boolean addScope(Scope scope){
        scopes.add(scope); 
        ArrayList<Symbol> list = new ArrayList<>(); 
        parameterList.add(list);
        return true;
    }
    
    public boolean addToSymbolTable(Symbol sym) {
        boolean exist = false;
        boolean existS = true;
        int i = 0; 
        while ((!exist) && (i < symbolTable.size())) {
            Symbol actual = symbolTable.get(i);
            exist = actual.equal(sym); 
            i++;
        }
        if (!exist) {
            if (sym.getTipo().isStructure()) {
                Struct struct = new Struct(); 
                struct.setId(sym.getTipo().getType_name());
                struct.setScope(sym.getScope());
                existS = existStruct(struct);
                if (existS) {
                    symbolTable.add(sym);
                } else {
                    errors = sym.getTipo().getType_name() + " type not defined";
                }
            } else {
                symbolTable.add(sym);
            }
        } 
        else errors = sym.getId() + " id is define multiple time in the same scope";
        return (!exist && existS);
    }
    
    public boolean addToStructTable(Struct struct){
        structTable.add(struct);
        return true;
    }
    
    public boolean addToMethodTable(Method method) {
        methodTable.add(method); 
        return true; 
    }
    
    public boolean addToParamList(Symbol sym, Scope scope) {
        int index = Integer.parseInt(scope.getName());
        parameterList.get(index).add(sym);
        return true; 
    }
    
    public boolean existStruct(Struct other) {
        boolean exist = false;
        int i = 0; 
        while ((!exist) && (i < structTable.size())) {
            Struct actual = structTable.get(i);
            String id = actual.getId();
            ArrayList alcance = getAlcanceScope(actual.getScope());
            if (id.equals(other.getId()) && alcance.contains(other.getScope().getName())) {
                exist = true; 
            }
            i++;
        }
        return exist; 
    }
    
    public ArrayList getAlcanceScope(Scope scope) {
        ArrayList alcance = new ArrayList(); 
        alcance.add(scope.getName());
        alcance = getAlcanceHijos(alcance,scope);
        return alcance; 
    }
    
    public ArrayList getAlcanceHijos(ArrayList alcancePadre, Scope scopeActual) {
        ArrayList<Scope> hijos = scopeActual.getSiguientes(); 
        for (int i = 0; i < hijos.size(); i++){
            Scope hijo = hijos.get(i);
            alcancePadre.add(hijo.getName());
            alcancePadre = getAlcanceHijos(alcancePadre,hijo);
        }
        return alcancePadre; 
    }
    
    public boolean existStructInScope(Struct struct){
        boolean exist = false;
        int i = 0;
        while (!exist && (i<structTable.size())){
            Struct actual = structTable.get(i);
            exist = actual.equal(struct);
            i++;
        }
        return exist;
    }
    
    public boolean existMethodInScope(Method method){
        boolean exist = false; 
        for (int i = 0; i < methodTable.size(); i++) {
            Method actual = methodTable.get(i);
            
            String firmaActual = getFirma(actual);
            String firmaMethod = getFirma(method);
            if ((actual.equal(method))&&(firmaActual.equals(firmaMethod))){
                exist = true; 
            }
        }
        return exist;
    }
    
    public String getFirma(Method method){
        String firma = "";
        ArrayList<Symbol> params = parameterList.get(Integer.parseInt(method.getInnerScope().getName()));
        for (int i = 0; i < params.size(); i++){
            Symbol temp = params.get(i);
            Type tempType = temp.getTipo(); 
            firma = firma.concat(tempType.getType_name()+"-");
        }
        return firma; 
    }
    
    public boolean symbolInScope(Symbol symbol, Scope scope){
        boolean exist = false;
        Scope symbolScope = symbol.getScope();
        if (symbolScope.getName().equals(scope.getName())) {
            exist = true; 
        }
        return exist;
    }
    
    public void eraseMethodTrack(Method method){
        Scope scope = method.getInnerScope();
        for (int i = 0; i  < symbolTable.size(); i++){
            Symbol actual = symbolTable.get(i);
            if (symbolInScope(actual,scope)){
                symbolTable.remove(i);
            }
        }
    }
    
    public String getErrors() {
        return errors;
    }
    
    public boolean isAllGood() {
        return allGood;
    }

    public ArrayList<Struct> getStructTable() {
        return structTable;
    }

    public ArrayList<Symbol> getSymbolTable() {
        return symbolTable;
    }

    public ArrayList<Method> getMethodTable() {
        return methodTable;
    }

    public ArrayList<Scope> getScopes() {
        return scopes;
    }
    
    
    
    
}
