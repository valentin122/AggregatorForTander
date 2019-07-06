package ru.zhigalov.xml;

import ru.zhigalov.TestRow;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Zhigalov on 04.07.19
 */

public class AllXmlOperations {
    public static HashSet<TestRow> readDataFromXML(String fileName){
        HashSet<TestRow> temp = new HashSet<>();
        try{
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);
            Node root = document.getDocumentElement();
            NodeList rows = root.getChildNodes();
            for (int i = 0; i < rows.getLength(); i++) {
                Node row = rows.item(i);
                if (Node.ELEMENT_NODE == row.getNodeType()) {
                    Element element = (Element) row;
                    temp.add(new TestRow(element.getElementsByTagName("start_page").item(0).getTextContent(),
 //  см. TestRow            element.getElementsByTagName("referer").item(0).getTextContent(),
                            element.getElementsByTagName("user").item(0).getTextContent(),
                            element.getElementsByTagName("ts").item(0).getTextContent(),
                            element.getElementsByTagName("depth").item(0).getTextContent(),
                            element.getElementsByTagName("duration").item(0).getTextContent(),
                            element.getElementsByTagName("transmit").item(0).getTextContent(),
                            element.getElementsByTagName("type").item(0).getTextContent()));
                }
            }
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }catch (SAXException saxe){
            saxe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return temp;
    }
    public static void writeToXML(Map<String, String> finalData, String column1, String column2, String agr, String fileName){
        String newFileName = new StringBuffer(fileName).insert(fileName.lastIndexOf("\\")+1,"new_").toString();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element root = doc.createElement("root");
            doc.appendChild(root);
            for (Map.Entry<String,String> entry:finalData.entrySet()
            ) {
                Element row = doc.createElement("row");
                Element col1 = doc.createElement(column1);
                col1.setTextContent(entry.getKey());
                row.appendChild(col1);
                Element col2 = doc.createElement(agr+"_"+column2);
                col2.setTextContent(entry.getValue());
                row.appendChild(col2);
                root.appendChild(row);
            }
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            FileOutputStream fos = new FileOutputStream(newFileName);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }catch (TransformerConfigurationException tce){
            tce.printStackTrace();
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch (TransformerException te){
            te.printStackTrace();
        }
        System.out.println("Данные записаны в файл " + newFileName);
    }
}
