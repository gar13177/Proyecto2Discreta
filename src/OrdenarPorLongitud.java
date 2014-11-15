
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kevin
 */
public class OrdenarPorLongitud implements Comparator<String> {

    public OrdenarPorLongitud(String cadena) {
    }

    @Override
    public int compare(String n1, String n2){
        
        if (n1.length()-n2.length()!=0){
            return n1.length()-n2.length();
        }
        return -1;
    }
    
}
