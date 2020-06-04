package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlUtils {
    public static Document readXml(String filePath) throws DocumentException {
        return readXml(new File(filePath));
    }
    public static Document readXml(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(file);
    }
    public static Document readXml(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(inputStream);
    }
    public static String getAttribute(Element element, String attrName){
        return  element.getText();
    }
    public static List<Element> getElements(Element element,String tagName){
        return element.elements();
    }

    public static  String getElementText(String filepath, String qName) throws DocumentException {
        Element root = readXml(filepath).getRootElement();
        String text = root.elementText(qName);
        return text.trim();
    }

    public static String getElementText(String filepath, String qName, String SubQName) throws DocumentException {
        Element root = readXml(filepath).getRootElement();
        String text = root.element(qName).elementText(SubQName);
        return text.trim();
    }

    public static List<String> getElementTextList(String filename, String qName) throws DocumentException {
        List<Element> elementList = readXml(filename).getRootElement().element(qName).elements();
        List<String> list = new ArrayList<>();
        for(Element e: elementList){
            list.add(e.getTextTrim());
        }
        return list;
    }


}
