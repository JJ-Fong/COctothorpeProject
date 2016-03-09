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
public class myNode {
    String type;
    String text; 
    ArrayList<myNode> children; 
    
    public myNode() {
        type = "";
        text = "";
        children = new ArrayList<myNode>(); 
    }
    
    public myNode(String txt){
        type = "void";
        text = txt;
        children = new ArrayList<myNode>(); 
    }
    
    public void addChild(myNode son){
        children.add(son); 
    }
    
    public myNode getChild(int i){
        myNode child; 
        try { 
            child = children.get(i);
        } catch (Exception e){
            child = null; 
        }
        return child; 
    }
    
    public ArrayList getAllChildren(){
        return children; 
    }
    
    public void setText(String text){
        this.text = text; 
    }
    
    public String getText() { 
        return text; 
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
