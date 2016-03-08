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
class Tipo {
    private boolean simple;    //true if is simple
    private boolean structure; //true if is structure
    private boolean param;     //true if is param
    private boolean array;     //true if is an array
    private int array_len;
    
    private String type_name;

    public Tipo(String type_name) {
        this.simple = false;
        this.structure = false;
        this.param = false;
        this.array = false;
        this.type_name = type_name;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    public boolean isStructure() {
        return structure;
    }

    public void setStructure(boolean structure) {
        this.structure = structure;
    }

    public boolean isParam() {
        return param;
    }

    public void setParam(boolean param) {
        this.param = param;
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }
    
    public void setArray(boolean array, int array_len) {
        this.array = array;
        this.array_len = array_len; 
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    
    
    
}
