package config;

import org.dom4j.DocumentException;
import utils.XmlUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServletMappingConfig {
    public static Map<String,Object> servletMap = new HashMap<>();

//    static {
//        File[] projects = new File("src/main/web/").listFiles(filename->filename.isDirectory());
//        try {
//            List<String> list = XmlUtils.getElementTextList("src/main/web2.xml","servlet");
//            System.out.println(list);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        servletMap.put("/", "com.servlet.testServlet");
//        servletMap.put("/test", "com.servlet.testServlet");
//    }

}
