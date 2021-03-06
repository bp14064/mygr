package xml_test;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AnalayzeXML {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\xml_test\\dcndl2.xml");

		 SAXReader reader = new SAXReader();
		    try {
		      Document doc = reader.read(file);
		      Element root = doc.getRootElement();
		      for (Iterator i = root.elementIterator(); i.hasNext();) {
		    	  Element element = (Element) i.next();
		    	    System.out.println(element.getName()+" "+element.getNodeTypeName()+" "+element.nodeCount());
					System.out.println("テキストを取り出す:" + element.getStringValue());

					//子ノードの取得テスト
					if(element.getName().equals("records")) {
						Element e2 = element;
						System.out.println(e2.nodeCount());
						System.out.println(e2.node(1).getName());
						System.out.println(e2.node(1).getNodeTypeName());
						Element e3 = (Element) e2.node(1);
						System.out.println(e3.nodeCount());
						System.out.println(e3.node(5).getName());
						Element e4 = (Element) e3.node(5);
						System.out.println(e4.nodeCount());
						System.out.println(e4.node(1).getName());
						Element e5 = (Element) e4.node(1);
						System.out.println(e5.nodeCount());
						System.out.println(e5.node(11).getName());
						System.out.println(e5.node(11).getStringValue());


						for(int i1=0;i1<e5.nodeCount();i1++) {
							System.out.println("nodenum="+i1+" nodename="+e5.node(i1).getName());
							System.out.println("value="+e5.node(i1).getStringValue());
						}

						Element ndlc = (Element)e5.node(51);
						System.out.println(ndlc.getNodeTypeName()+","+ndlc.nodeCount());
						System.out.println(ndlc.node(0).getStringValue());
					}


					/*for (Iterator j = element.nodeIterator(); j.hasNext();) {
				    	  Node element1 = (Node) j.next();
				    	    System.out.println(element1.getName());
					}*/

				 }


		    }catch (DocumentException e) {
		        e.printStackTrace();
		    }
	}


}
