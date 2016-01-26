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
 * @author GustavoAdolfo
 */
public class TreeBuilder {
    public JTree crearArbol (ParseTree arbol ,expDecaf2Parser parser) {
        DefaultMutableTreeNode cabezaArbol = new DefaultMutableTreeNode(arbol.toStringTree(parser));

        int count = arbol.getChildCount();
        for(int i=0; i<count; i++)
        {
            ParseTree subtree = arbol.getChild(i);
            DefaultMutableTreeNode ch = new DefaultMutableTreeNode(subtree.toStringTree(parser));
            System.out.println(subtree.toStringTree(parser));
            
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
            DefaultMutableTreeNode ch = new DefaultMutableTreeNode(subtree.toStringTree(parser));
            System.out.println(subtree.toStringTree(parser));
                       
            nodoPadre.add(BuildSubTree(ch, subtree, parser));
        }
        return nodoPadre;
    }
}
