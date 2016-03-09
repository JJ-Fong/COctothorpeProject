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
                    Type type = getType(typeNode.getAllChildren());
                    
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
                    
                    addToSymbolTable(symbol); 
                    build(temp.getAllChildren(),scope_actual);
                }
                else if (temp.getText().equals("structDeclaration")) { 
                    System.out.println(temp.getText()); 
                    myNode idNode = body.get(1);
                    
                    Struct struct = new Struct(); 
                    
                    struct.setId(idNode.getText());
                    struct.setScope(scope_actual);
                    
                    Scope next_scope = new Scope(String.valueOf(scopes.size())); 
                    next_scope.setAnterior(scope_actual);
                    scope_actual.addNext(next_scope);
                    addScope(next_scope);
                    
                    struct.setInnerScope(next_scope);
                    
                    allGood = addToStructTable(struct);
                    build(temp.getAllChildren(),next_scope);
                }
                else if (temp.getText().equals("methodDeclaration")) { 
                    System.out.println(temp.getText());
                    myNode typeNode = body.get(0);
                    myNode idNode = body.get(1); 
                    
                    Method method = new Method(); 
                    Type type = getType(typeNode.getAllChildren()); 
                    
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
                    Type type = getType(typeNode.getAllChildren());
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
    
    public Type getType(ArrayList<myNode> typeNodeBody){
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
        symbolTable.add(sym); 
        return true;
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
    
    public String getErrors() {
        return errors;
    }
    
    public boolean isAllGood() {
        return allGood;
    }
    
    
    
    
}
