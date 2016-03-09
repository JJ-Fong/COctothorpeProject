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
public class Symbol {
    String id;
    Scope scope; 
    Type tipo; 
    
    public Symbol() {
        id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope ambito) {
        this.scope = ambito;
    }
    
    public boolean equal(Symbol other){
        boolean flag = true; 
        String id1 = this.getId(); 
        String id2 = other.getId();
        
        String ambito1 = this.getScope().getName();
        String ambito2 = other.getScope().getName();
        
        if (id1.equals(id2)&&ambito1.equals(ambito2)) {
            flag = true; 
        } else {
            flag = false; 
        }
        return flag; 
    }
    
    
}