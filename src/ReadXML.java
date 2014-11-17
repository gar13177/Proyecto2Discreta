/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author manuelgomez
 */
public class ReadXML {
    private TreeSet palabras = new TreeSet();
    public ReadXML(){
        try {
            File fXmlFile = new File("diccionario.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            NodeList nList = doc.getElementsByTagName("diccionario");
            NodeList List = doc.getElementsByTagName("palabra");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    for(int i = 0; i<List.getLength(); i++)
                       palabras.add(eElement.getElementsByTagName("palabra").item(i).getTextContent()); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TreeSet getPalabras(){
        return palabras;
    }
    
    public String getNear(String cadena){
        boolean continuar = true;
        TreeSet _comparator = new TreeSet(new OrdenarPorLongitud(cadena) );
        Iterator<String> it = palabras.iterator();
        String temp;
        while (continuar){
            if (cadena.length()!= 0){
                cadena = cadena.substring(0,cadena.length()-1);
                System.out.println(cadena);
                while (it.hasNext()){
                    temp = it.next();
                    if (temp.contains(cadena))
                        _comparator.add(temp);
                }   
            }else{
                continuar = false;
            }  
        } 
        
        it = _comparator.iterator();
        String retorno = "";
        for (int i = 0; i<4; i++){
            if (it.hasNext())
                retorno += it.next()+", ";
        }
        if (retorno.length()==0)
            retorno = "No hay predicciones";
        return retorno;
    }
    
}