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
    
    boolean checked; 
    
    public Scope(String name) {
        this.name = name; 
        anterior = null;
        siguientes = new ArrayList();
        checked = false;
    }
    
    public Scope(Scope ant, String name){
        this.name = name; 
        anterior = ant; 
        siguientes = new ArrayList();
        checked = false;
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

    public ArrayList<Scope> getSiguientes() {
        return siguientes;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        if (siguientes.size() == 0) {
            this.checked = true; 
        } else {
            boolean allChecked = true; 
            for (int i = 0; i < siguientes.size(); i++) {
                Scope actual = siguientes.get(i);
                allChecked = (allChecked  && actual.isChecked()); 
            }
            this.checked = allChecked; 
        }
    }
    
    public Scope getAnterior() {
        return anterior;
    }

    public void setAnterior(Scope anterior) {
        this.anterior = anterior;
    }
}
