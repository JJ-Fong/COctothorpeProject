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
public class Struct {
    String id; 
    Scope scope; 
    Scope innerScope; 
    public Struct() {
        this.id = "";
        this.scope = null;
        this.innerScope = null;  
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope ambito) {
        this.scope = ambito;
    }

    public Scope getInnerScope() {
        return innerScope;
    }

    public void setInnerScope(Scope innerScope) {
        this.innerScope = innerScope;
    }
    
    public boolean equal(Struct other){
        boolean flag = false; 
        
        String id1 = this.getId();
        String id2 = other.getId();
        String scope1 = this.getScope().getName();
        String scope2 = other.getScope().getName();
        
        if (id1.equals(id2) && scope1.equals(scope2)){
            flag = true; 
        }
        return flag; 
    }
    
}
