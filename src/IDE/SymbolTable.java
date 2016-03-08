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
    ArrayList<symbol> table; 
    myNode treeList; 
    
    public SymbolTable(myNode arbolR) { 
        this.treeList = arbolR; 
        table = new ArrayList<symbol>();
        ArrayList<myNode> inicial = new ArrayList<myNode>(); 
        inicial.add(treeList); 
        build(inicial);
    }
    
    public void build(ArrayList<myNode> list) {
        for (int i = 0; i < list.size(); i++){
            myNode nodo = list.get(i);
            ArrayList<myNode> body = nodo.getAllChildren(); 
            String nodeText = nodo.getText();
            nodeText = nodeText.trim();
            if (nodeText.equals("varDeclaration")) {
                if (body.size()==3) {
                    myNode type = body.get(0);
                    myNode id = body.get(1);
                    String tipo = getType(type);
                    
                    symbol sym = new symbol();
                    sym.setId(id.getText());
                    Tipo tp = new Tipo(tipo); 
                    if (tipo.equals("int")||tipo.equals("char")||tipo.equals("boolean")||tipo.equals("void")){
                        tp.setSimple(true);
                    } else {
                        tp.setStructure(true);
                    }
                    sym.setTipo(tp);
                    boolean flag = add(sym); 
                } else if (body.size()==6) {
                    myNode type = body.get(0);
                    myNode id = body.get(1);
                    String tipo = getType(type);
                }
            }
            build(body);
        }
    }
    
    public boolean add(symbol sym) { 
        boolean flag = true; 
        return flag; 
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
    
}
