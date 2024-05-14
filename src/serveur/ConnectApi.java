package serveur;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectApi {
    private final static String apiUrl = "http://catalogue.bnf.fr/api/SRU?version=1.2&operation=searchRetrieve";

    /**
     * Do a request to BNF API with isbn
     * @param isbn
     * @throws Exception
     */
    public ConnectApi(String isbn) throws Exception {
        //%20 = ' ' and %22 = '"'
        URL url = new URL(apiUrl+"&query=bib.fuzzyISBN%20all%20%22"+isbn+"%22");

        HttpURLConnection api = (HttpURLConnection) url.openConnection();
        api.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(api.getInputStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }

        in.close();
        api.disconnect();

        System.out.println("Réponse de l'API BNF Data :");
        System.out.println(result);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document parsXML = builder.parse(new java.io.ByteArrayInputStream(result.toString().getBytes()));

        parsXML.getDocumentElement().normalize();

        // Extraire les informations nécessaires
        NodeList data = parsXML.getElementsByTagName("mxc:datafield");
        String title = "";
        String author = "";
        String publicationDate = "";

        System.out.println(data.getLength());

        for (int i = 0; i < data.getLength(); i++) {
            Element dataField = (Element) data.item(i);
            String tag = dataField.getAttribute("tag");
            System.out.println(tag);
            if (tag.equals("200")) {
                NodeList subfields = dataField.getElementsByTagNameNS("*", "*");
                title = getSubfield(subfields,"a");
            }
            if (tag.equals("700")){
                NodeList subfields = dataField.getElementsByTagNameNS("*", "*");
                author = getSubfield(subfields,"a");
            }
            if (tag.equals("214")){
                NodeList subfields = dataField.getElementsByTagNameNS("*", "*");
                publicationDate = getSubfield(subfields,"d");
            }
        }
    }

    private String getSubfield(NodeList subfields, String find){
        for (int j = 0; j < subfields.getLength(); j++) {
            Element subfield = (Element) subfields.item(j);
            String code = subfield.getAttribute("code");
            if (code.equals(find)) {
                return subfield.getTextContent().trim();
            }
        }
        return null;
    }


    public static void main(String[] args){
        try {
            ConnectApi test = new ConnectApi("9782810627196");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
