/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal_igorramirez;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
/**
 *
 * @author xp
 */
public class gestorxPath {
   

    Document doc;
    String salida = "";

    public int EjecutaXPath(File fichero, String consulta) {

        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            Document XMLDoc = factory.newDocumentBuilder().parse(fichero);      //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
           
            XPath xpath = XPathFactory.newInstance().newXPath();                //Crea el objeto XPath
            XPathExpression exp = xpath.compile(consulta);                      //Crea un XPathExpression con la consulta deseada
            
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);       //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá//el resultado como una lista de nodos.
            NodeList nodeList = (NodeList) result;                              //Ahora recorre la lista para sacar los resultados
                if (consulta.equals("/Libros/Libro")) {                         //Recoremos en un bucle el xml de libros para la consulta
                    for (int i = 0; i < nodeList.getLength(); i++) {
                            salida = salida + "\n" + nodeList.item(i).getTextContent(); //obtenemos el contenido de los nodos lista
                            salida = salida + "\n" + nodeList.item(i).getAttributes().item(0).getNodeValue();

                }
            } else {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    salida = salida + "\n" + nodeList.item(i).getTextContent();

                }
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

}

