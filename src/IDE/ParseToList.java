/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDE;

import Grammar.expDecaf2Parser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Javier Fong
 */
public class ParseToList {
    ParseTree tree;
    expDecaf2Parser parser;
    myNode cabeza; 
    
    public ParseToList(ParseTree arbol ,expDecaf2Parser parser) {
        this.tree = arbol;
        this.parser = parser; 
        this.cabeza = new myNode(); 
        translateHead();
    }
    
    public void translateHead(){
        String text = tree.toStringTree(parser); 
        text = nodeName(text); 
        cabeza.setText(text);
        int count = tree.getChildCount();
        for (int i = 0; i < count; i++){
            translateChild(cabeza,tree.getChild(i));
        }
    }
    
    public void translateChild(myNode padre,ParseTree arbol){
        myNode hijo = new myNode(); 
        String text = arbol.toStringTree(parser); 
        text = nodeName(text); 
        hijo.setText(text);
        padre.addChild(hijo);
        int count = arbol.getChildCount();
        for (int i = 0; i < count; i++){
            translateChild(hijo,arbol.getChild(i));
        }
    }
    private String nodeName(String name) {
        try {
            name = name.substring(0,name.indexOf(" ")); 
            if ((name.length() > 1)&&(name.startsWith("("))){
                name = name.substring(1); 
            }
            if ((name.length() > 1)&&(name.endsWith(")"))){
                name = name.substring(0,name.length()-1); 
            }    
        } catch (Exception e) {}
        return name; 
    }
    
    public myNode getList(){
        return cabeza;
    }
}
