package com.dom4j.study;

import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;





public class Dom4jTest {

	public static void main(String[] args) throws IOException, DocumentException {
//		//第一中方式
//		Document document = DocumentHelper.createDocument();
//		//创建根节点并添加进文档
//		Element root = DocumentHelper.createElement("student");
//		
//		document.setRootElement(root);
//		
//		//第二种方式，创建文档并初始化文档的根节点元素
//		Element root2 = DocumentHelper.createElement("student2");
//		Document document2 = DocumentHelper.createDocument(root2);
//		
//		root2.addAttribute("name", "zhangsan");
//		Element helloElement = root2.addElement("hello");
//		Element worldElement = root2.addElement("world");
//
//		helloElement.setText("hello Text");
//		
//		
//		worldElement.setText("world text");
//		
//		XMLWriter xmlWriter = new XMLWriter();
//		xmlWriter.write(document2);
		
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File("student.xml"));
		
		Element root=document.getRootElement();
		System.out.println(root.getName());
		
	}

}
