package Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XMLHelper {

	static public void WriteAllPhones(String Path, ArrayList<TreeMap<String,String>> PhoneList){ 
		try {

		Element phonesXML = new Element("phones");
		Document doc = new Document(phonesXML);
		doc.setRootElement(phonesXML);

		for(Integer i = 0; i < PhoneList.size(); i++){
			
			Element phoneXML = new Element("phone");
			phoneXML.setAttribute(new Attribute("id", i.toString()));
			
			for(String key : PhoneList.get(i).keySet()){
				phoneXML.setAttribute(new Attribute(key, PhoneList.get(i).get(key)));
			}
			doc.getRootElement().addContent(phoneXML);
		}
		
		XMLOutputter xmlOutput = new XMLOutputter();

		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter(Path));
		
	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  }
	}
	
	static public ArrayList<TreeMap<String,String>> ReadAllPhones(String Path){
		ArrayList<TreeMap<String,String>> phoneslist = new ArrayList<TreeMap<String,String>>();
		
		try {
			File xmlFile = new File(Path);
			SAXBuilder builder = new SAXBuilder();

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
	        List list = rootNode.getChildren("phone");
			
			for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i);

					TreeMap<String,String> phone = new TreeMap<String,String>();
					
					for(Object key : node.getAttributes()){
							String KEY = ((Attribute) key).getName();
							String VALUE = ((Attribute) key).getValue();
						    phone.put(KEY,VALUE);
					   }
					phoneslist.add(phone);
			}
			
		  return phoneslist;
		  
		  } catch (Exception ex) {
			System.out.println(ex.getMessage());
		  }
		return null;
	}
}