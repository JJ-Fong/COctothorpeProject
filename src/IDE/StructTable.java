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
public class StructTable {
    myNode tree; 
    ArrayList<Struct> table; 
    
    public StructTable(myNode arbolR) { 
        tree = arbolR;
        table = new ArrayList<>(); 
        
    }
}
