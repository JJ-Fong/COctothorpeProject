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
public class Scope {
    String name; 
    Scope anterior; 
    ArrayList<Scope> siguientes; 
    
    public Scope(String name) {
        this.name = name; 
        anterior = null;
        siguientes = new ArrayList(); 
    }
    
    public Scope(Scope ant, String name){
        this.name = name; 
        anterior = ant; 
        siguientes = new ArrayList(); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public void addNext(Scope sig){
        siguientes.add(sig);
    }
    
    public Scope getNext(int index){
        return (siguientes.get(index));
    }

    public Scope getAnterior() {
        return anterior;
    }

    public void setAnterior(Scope anterior) {
        this.anterior = anterior;
    }
}
