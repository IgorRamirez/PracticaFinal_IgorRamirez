/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal_igorramirez;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.  Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xp
 */
public class dom {

    Document doc = null;

    public int abrir_XML_DOM(File fichero) {                                    //doc representa al arbol dom
        //es public  int porque me realiza comprobaciones
        //public void no devuelve nada  

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//Se crea un objeto DocumentBuider Factory
            factory.setIgnoringElementContentWhitespace(true);                  //Ignora los espacios en blanco
            DocumentBuilder builder = factory.newDocumentBuilder();             //Crea un objeto DocumentBuider para cargar en el la estructura de arbol dom a partir del xml seleccionado
            doc = builder.parse(fichero);                                       //Interpreta (parsear) el documento xml(file) y genera el dom  equivalente
            return 0;                                                           //Ahora doc apunta el arbol dom listo para ser recorrido
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String recorrerDOMyMostrar(Document doc) {

        String datos_nodo[] = null;
        String salida = "";
        Node node;

        Node raiz = doc.getFirstChild();                                        //obtiene el primer nodo del DOM(primer hijo)
        NodeList nodelist = raiz.getChildNodes();                               //obtiene una lista de nodos con todos los nodos hjo del raiz
        
        for (int i = 0; i < nodelist.getLength(); i++) {                        //Procesa los nodos hijo
            node = nodelist.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                datos_nodo = procesarEquipo(node);                               //Es un nodo libro
               
            }
        }
        return salida;

    }

    protected String[] procesarEquipo(Node n) {
        String datos[] = new String[3];
        Node ntemp = null;
        int contador = 8;

        datos[0] = n.getAttributes().item(0).getNodeValue();  
        datos[1] = n.getAttributes().item(0).getNodeValue();   
        datos[2] = n.getAttributes().item(0).getNodeValue();   
        datos[3] = n.getAttributes().item(0).getNodeValue();   
        datos[4] = n.getAttributes().item(0).getNodeValue();   
        datos[5] = n.getAttributes().item(0).getNodeValue();   
        datos[6] = n.getAttributes().item(0).getNodeValue();  
        datos[7] = n.getAttributes().item(0).getNodeValue(); 
        datos[8] = n.getAttributes().item(0).getNodeValue(); 
        //obtiene el valor del primer atributo del nodo(uno en este ejempo)
        NodeList nodos = n.getChildNodes();                                     //obtiene los hijos del libro (titulo y autor) 

        for (int i = 8; i < nodos.getLength(); i++) {
            
            ntemp = nodos.item(i);

            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue(); //IMPORTANTE para obtener el texto con el titulo y el autor se accede al nodo TEXT hijo de ntemp y se saca su valor
                contador++;
            }
        }
        return datos;
    }
    
    public int guardarDOM(File fichero) {

        //Document doc = null;
        try {

            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);

            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(fichero), format);

            serializer.serialize(doc);
            return 0;

        } catch (Exception e) {
            return -1;
        }
    }


    public int annadirDOM(Document doc, String equipo, String capitan,
            String estadio, String colores, String part_ganados,
            String part_perdidos, String puntos_a_favor, String puntos_en_contra ) {
        try {
            Node npresidente = doc.createElement("presidente");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node npresidente_text = doc.createTextNode(equipo);                     //Se crea un nodo tipo texto con el titulo del libro
            npresidente.appendChild(npresidente_text);                                  //Se añade el nodo de texto con el titulo como hijo del elemento titulo

            Node ncapitan = doc.createElement("capitan");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node ncapitan_text = doc.createTextNode(capitan);                     //Se crea un nodo tipo texto con el titulo del libro
            ncapitan.appendChild(ncapitan_text);

            Node nestadio = doc.createElement("estadio");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node nestadio_text = doc.createTextNode(estadio);                     //Se crea un nodo tipo texto con el titulo del libro
            nestadio.appendChild(nestadio_text);

            Node ncolores = doc.createElement("colores");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node ncolores_text = doc.createTextNode(colores);                     //Se crea un nodo tipo texto con el titulo del libro
            ncolores.appendChild(nestadio_text);

            Node npart_ganados = doc.createElement("part_ganados");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node npart_ganados_text = doc.createTextNode(part_ganados);                     //Se crea un nodo tipo texto con el titulo del libro
            npart_ganados.appendChild(npart_ganados_text);

            Node npart_perdidos = doc.createElement("part_perdidos");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node npart_perdidos_text = doc.createTextNode(part_perdidos);                     //Se crea un nodo tipo texto con el titulo del libro
            npart_perdidos.appendChild(npart_perdidos_text);

            Node npuntos_a_favor = doc.createElement("puntos_a_favor");                         //Se crea un nodo tipo element con nombre 'titulo'(<Titulo>)
            Node npuntos_a_favor_text = doc.createTextNode(puntos_a_favor);                     //Se crea un nodo tipo texto con el titulo del libro
            npuntos_a_favor.appendChild(npuntos_a_favor_text);

            Node npuntos_en_contra = doc.createElement("puntos_en_contra");                           //Se ace lo mismo que con titulo a autor(<Autor>)
            Node npuntos_en_contra_text = doc.createTextNode(puntos_en_contra);
            npuntos_en_contra.appendChild(npuntos_en_contra_text);

            Node nequipo = doc.createElement("equipo");                           //Se crea un nodo de tipo elemento(<libro>) 
            ((Element) nequipo).setAttribute("id_equipo", equipo);
            ((Element) nequipo).setAttribute("nombre", equipo);
            ((Element) nequipo).setAttribute("puesto", equipo); //Al nuevo nodo libro se le añade un atributo publicado_en
           
            
            nequipo.appendChild(nequipo);   
            nequipo.appendChild(nequipo); 


//Se añade a libro el nodo autor y titulo creados antes
            

            Node raiz = doc.getChildNodes().item(0);                            //Se obtiene el primer nodo del documento y a el se le añade como hijo el nodo libro que ya tiene colgando todos sus hijos y atributos creados antes
            raiz.appendChild(nequipo);

            return 0; 
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    
}
