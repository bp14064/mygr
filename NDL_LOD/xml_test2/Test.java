package xml_test2;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/*
 * XPathのAnalayzeXMLの結果を基にして、もう一度やってみたがだめだった
 *
 * やはり、AnalayzeXMLでやったように地道に調べて階層を下げていったほうがよさそう
 * しかもあれならなんかメソッドにして、まわしていけば面倒だが単純にできそうな気がする
 */
public class Test {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\xml_test\\samp.xml");
		org.dom4j.XPath xpath = DocumentHelper.createXPath("/searchRetrieveResponse/records/record/recordData/dc/title");

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		List comics = xpath.selectNodes(document);
		for(Object nodeObj: comics){
		    Element comic = (Element)nodeObj;
		    System.out.println(comic.attributeValue("title"));
		}

		/*SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			List nodes = document.selectNodes("/dcndl_simple:dc/dc:title");

			for(Iterator i = nodes.iterator(); i.hasNext();) {
				System.out.println("1");
				Node node = (Node) i.next();
				System.out.println("title:" + node.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}*/
	}
}
