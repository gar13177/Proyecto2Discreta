
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kevin
 */
public class noGUI {
    public static void main(String[] args){
        ReadXML archivo = new ReadXML();
        TreeSet palabras = new TreeSet();
        palabras = archivo.getPalabras();
        System.out.println(palabras);
    }
    
}