/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal_igorramirez;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



/**
 *
 * @author igor ramirez bonevic
 */
public class sax {
 SAXParser parser;
    File ficheroXML;
    ManejadorSax sh;

    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();                                    //Se crea un objeto SAXParser para interpretar el documento xml
            sh = new ManejadorSax();                                            //Se crea una instancia del manejador que sera el que recorra el 
            //documento xml secuencialmente                                     //documento xml secuencialmente
            ficheroXML = fichero;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    class ManejadorSax extends DefaultHandler {

        int ultimoelement;
        String cadena_resultado = "";

        public ManejadorSax() {
            ultimoelement = 0;
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes atts) throws SAXException {
            if (qName.equals("equipo")) {
                cadena_resultado = cadena_resultado + "\nel id es:"+ atts.getValue(atts.getQName(0));
                cadena_resultado = cadena_resultado + "\nel nombre es:"+ atts.getValue(atts.getQName(1));
                cadena_resultado = cadena_resultado + "\nel puesto es:"+ atts.getValue(atts.getQName(2)) + "\n";
                ultimoelement = 1;
            } else if (qName.equals("presidente")) {
                ultimoelement = 2;
                cadena_resultado = cadena_resultado + "\nEl presidente es";
            } else if (qName.equals("capitan")) {
                ultimoelement = 3;
                cadena_resultado = cadena_resultado + "\nEl capitan es:";
            }
             else if (qName.equals("estadio")) {
                ultimoelement = 4;
                cadena_resultado = cadena_resultado + "\nel estadio es:";
            }
             else if (qName.equals("colores")) {
                ultimoelement = 5;
                cadena_resultado = cadena_resultado + "\nlos colores son:";
            }
              else if (qName.equals("partidos ganados")) {
                ultimoelement = 6;
                cadena_resultado = cadena_resultado + "\n partidos ganados:";
            }
             else if (qName.equals("partidos perdidos")) {
                ultimoelement = 7;
                cadena_resultado = cadena_resultado + "\npartidos perdidos:";
            }
             else if (qName.equals("puntos a favor")) {
                ultimoelement = 8;
                cadena_resultado = cadena_resultado + "\n los puntos a favor son:";
            }
             else if (qName.equals("puntos en contra")) {
                ultimoelement = 9;
                cadena_resultado = cadena_resultado + "\nlos puntos en contra son:";
            }
              
        }

        @Override                                                               //Cuando en esste ejemplo se detecta el final de un elemento<libro>
        public void endElement(String uri, String localName, String qName)      //se pone ina línea dicontinua en la salida
                throws SAXException {

            if (qName.equals("Libro")) {
                System.out.println("He encontrado el final de  un elemento.");
                cadena_resultado = cadena_resultado + "\n ---------";
            }
        }

        @Override                                                               //Cuando se detecta una cadena de texto posterior a uno de los elementos
        public void characters(char[] ch, int start, int length) throws SAXException {//<titulo> o <autor> entonces se guarda ese texto en la variable correspondiente

            if (ultimoelement == 2) {
                for (int i = start; i<length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 3) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }
        }

    }

    public String recorrerSAX(ManejadorSax sh, SAXParser parser) {
        try {
            parser.parse(ficheroXML ,sh);
            return sh.cadena_resultado;
        } catch (SAXException e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        } catch (Exception e) {
              e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }

}


