package MiniProject;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ReadDOMXMLSAX {
        public static <Public> void  main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser saxparser= factory.newSAXParser();
        System.out.println("\n\naffichage des montants et dates des retraits de type distributeur\n\n");

        DefaultHandler handler=new DefaultHandler(){
            boolean type =false;
            boolean montant=false;
            boolean date=false;
            public void startElement(String uri, String localname, String qname, Attributes attribute) throws SAXException{
                if(qname.equalsIgnoreCase("retrait")){
                    if ((attribute.getValue("type")).equalsIgnoreCase("distributeur")){
                        type=true;
                    } 
                }
                if(qname.equalsIgnoreCase("montant"))       montant=true;
                if(qname.equalsIgnoreCase("date"))          date=true;
            }
            public void endElement(){
                System.out.println("Fin du document");
            };
            public void characters(char[] caracteres, int debut, int longueur) {
                String donnees = new String(caracteres, debut, longueur);
              
                if(montant && type) {
                        System.out.println("   Montant \t " + donnees );
                        montant=false;
                    }
                    if(date && type) {
                        System.out.println("   date \t " + donnees );
                        date=false;
                        type=false;
                        System.out.println("---------------------");
                    }
                
            }
        };
            saxparser.parse("livre-de-compte.xml",handler);

    }
}
