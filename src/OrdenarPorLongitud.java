
import static java.lang.Math.abs;
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

    String cadena;
    public OrdenarPorLongitud(String cadena) {
        this.cadena = cadena;
    }

    @Override
    public int compare(String n1, String n2){
        n1 = n1.replace(cadena,"");
        n2 = n2.replace(cadena,"");
        
        int n1L = abs(n1.length()-cadena.length());
        int n2L = abs(n2.length()-cadena.length());
        
        if (n1L-n2L!=0){
            return n1L-n2L;
        }
        return 1;
    }
    
}
