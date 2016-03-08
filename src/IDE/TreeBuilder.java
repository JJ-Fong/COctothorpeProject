/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDE;

/**
 *
 * @author Javier Fong
 */

import javax.swing.JTree;
import Grammar.expDecaf2Parser;
import javax.swing.tree.DefaultMutableTreeNode;
import org.antlr.v4.runtime.tree.ParseTree;
/**
 *
 */

public class TreeBuilder {
    public JTree crearArbol (ParseTree arbol ,expDecaf2Parser parser) {
        String name = arbol.toStringTree(parser);
        DefaultMutableTreeNode cabezaArbol = new DefaultMutableTreeNode(nodeName(name));

        int count = arbol.getChildCount();
        for(int i=0; i<count; i++)
        {
            ParseTree subtree = arbol.getChild(i);
            name = subtree.toStringTree(parser); 
            DefaultMutableTreeNode ch = new DefaultMutableTreeNode(nodeName(name));
            cabezaArbol.add(BuildSubTree(ch, subtree, parser));
        }
        return new JTree(cabezaArbol);
    }
    
    public DefaultMutableTreeNode BuildSubTree(DefaultMutableTreeNode nodoPadre, ParseTree arbol, expDecaf2Parser parser)
    {
        int count = arbol.getChildCount();
        for(int i=0; i<count; i++)
        {
            ParseTree subtree = arbol.getChild(i);
            String name = subtree.toStringTree(parser); 
            DefaultMutableTreeNode ch = new DefaultMutableTreeNode(nodeName(name));
            nodoPadre.add(BuildSubTree(ch, subtree, parser));
        }
        return nodoPadre;
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
}
