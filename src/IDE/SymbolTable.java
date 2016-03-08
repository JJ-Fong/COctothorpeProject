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
public class SymbolTable {
    ArrayList<Symbol> table; 
    ArrayList<Scope> scopes; 
    myNode treeList; 
    boolean allGood; 
    String error; 
    
    public SymbolTable(myNode arbolR) { 
        this.treeList = arbolR; 
        table = new ArrayList<Symbol>();
        scopes = new ArrayList<Scope>();
        ArrayList<myNode> inicial = new ArrayList<myNode>(); 
        inicial.add(treeList); 
        allGood = true;
        error = "";
        
        Scope global = new Scope("0");
        scopes.add(global); 
        build(inicial,scopes.get(0));
    }
    
    public String getType(myNode type) {
        String tipo = "";
        ArrayList<myNode> body = type.getAllChildren(); 
        tipo = body.get(0).getText(); 
        if (tipo.equals("struct")) {
            tipo = tipo.concat(" "+body.get(1).getText()); 
        }
        return tipo; 
    }
    
    public void build(ArrayList<myNode> list,Scope scope) {
        if (allGood) {
        for (int i = 0; i < list.size(); i++) {
            myNode actual = list.get(i);
            String nodeText = actual.getText();
            if (nodeText.equals("varDeclaration")) {
                ArrayList<myNode> body = actual.getAllChildren();
                myNode typeNode = body.get(0);
                myNode idNode = body.get(1);
                
                String stringType = getType(typeNode);
                
                Symbol sym = new Symbol(); 
                sym.setId(idNode.getText());
                Type type = new Type(stringType); 
                if ((stringType.equals("int"))||(stringType.equals("char"))||(stringType.equals("boolean"))||(stringType.equals("void"))){
                    type.setSimple(true);
                } else {
                    type.setStructure(true);
                }
                if (body.size()==3) {
                    sym.setAmbito(scope);
                    sym.setTipo(type);
                } else {
                    myNode sizeNode = body.get(3);
                    type.setArray(true);
                    type.setArray_len(Integer.parseInt(sizeNode.getText()));
                    
                    sym.setAmbito(scope);
                    sym.setTipo(type);
                }
                allGood = add(sym);
            }
            if (nodeText.equals("block")){
                Scope next_scope = new Scope(String.valueOf(scopes.size()));
                next_scope.setAnterior(scope);
                scope.addNext(next_scope);
                scopes.add(next_scope); 
                build(actual.getAllChildren(),next_scope);
            }
            else if (!nodeText.equals("structDeclaration")) {
                build(actual.getAllChildren(),scope);
            }
        }
        } else {
            System.out.println("Error en declaracion de variables");
        }
    }
    
    public boolean add(Symbol sym) { 
        boolean flag = false;
        for (int i=0; i < table.size(); i++){
            Symbol actual = table.get(i);
            flag = actual.equal(sym);
            if (flag) {
                error = actual.getId()+" already exist in this scope";
            }
        }
        if (!flag) table.add(sym);
        return (!flag);
    }

    public String getError() {
        return error;
    }

    public boolean isAllGood() {
        return allGood;
    }

    public ArrayList<Symbol> getTable() {
        return table;
    }
    
    
    
}
