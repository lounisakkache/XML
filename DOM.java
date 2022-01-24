package MiniProject;
import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
public class CréerXML{
 
   public static void main(String argv[]) {
 
      try {
        File XMLFile = new File("livre-de-compte.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(XMLFile);
        Element root = doc.getDocumentElement();
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList x = doc.getElementsByTagName("valeur-initiale");
        Float valeur_initial=Float.parseFloat(x.item(0).getTextContent());
        System.out.println(valeur_initial);
        NodeList nListRet = doc.getElementsByTagName("retrait");
        NodeList nListDep = doc.getElementsByTagName("depot");
        Float SommeRet=0f;
        Float SommeDep=0f;
        for (int temp = 0; temp < nListRet.getLength(); temp++) {
            Node nNode = nListRet.item(temp);            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               Element montant=(Element) eElement.getElementsByTagName("montant").item(0);
                SommeRet=SommeRet+Float.parseFloat(montant.getTextContent());
            }
         }
         for (int temp = 0; temp < nListDep.getLength(); temp++) {
            Node nNode = nListDep.item(temp);            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               Element montant=(Element) eElement.getElementsByTagName("montant").item(0);
               SommeDep=SommeDep+Float.parseFloat(montant.getTextContent());
            }
         }
         Float nouveau_valeur_initial=valeur_initial+SommeDep-SommeRet;
         x.item(0).setTextContent(String.valueOf(nouveau_valeur_initial));
         System.out.println(x.item(0).getTextContent());

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult resultat = new StreamResult(new File("monFichier.xml"));
 
            transformer.transform(source, resultat);
 
            System.out.println("Fichier sauvegardé avec succès!");
 
     } catch (Exception e) {
        e.printStackTrace();
    }
}
     
  
}