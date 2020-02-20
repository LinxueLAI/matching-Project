package com.fxgraph.graph;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;
public class ReadXML {
	Info info;
	public ReadXML(){
		this.info= new Info();
//		this.nListEdge = nListEdge;
	}

    public Info getList(File file){
    	try {
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(file);
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	info.nListCell = doc.getElementsByTagName("Cell");
	info.nListEdge = doc.getElementsByTagName("Edge");
    } catch (Exception e) {
	e.printStackTrace();
    }
    	return info;
  }

}