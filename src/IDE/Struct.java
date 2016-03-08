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
    Scope ambito; 
    ArrayList<Symbol> cuerpo;

    public Struct() {
        this.id = "";
        this.ambito = null;
        cuerpo = new ArrayList<>(); 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Scope getAmbito() {
        return ambito;
    }

    public void setAmbito(Scope ambito) {
        this.ambito = ambito;
    }

    public ArrayList<Symbol> getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(ArrayList<Symbol> cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
}
