package dcndl_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DcndlTest {

	private static final int[] targetNodeNum = {11, 15, 21, 45, 47, 51, 53, 55};


	public static void main(String[] args) {
		//String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=title%3d%22%e3%81%93%e3%81%93%e3%82%8d%22%20AND%20creator%3d%22%e5%a4%8f%e7%9b%ae%e6%bc%b1%e7%9f%b3%22%20AND%20from%3d%222011%22%20AND%20until%3d%222013%22&recordSchema=dcndl_simple";
		String request = "http://iss.ndl.go.jp/api/sru?operation=searchRetrieve&query=dpid%3D%22iss-ndl-opac%22+AND+title%3D%22%E3%81%93%E3%81%93%E3%82%8D%22+AND+creator%3D%22%E5%A4%8F%E7%9B%AE%E6%BC%B1%E7%9F%B3%22+AND+from%3D%222011%22+AND+until%3D%222013%22&recordSchema=dcndl&onlyBib";
		String tmp="";
		try {
		      URL url = new URL(request);
		      URLConnection con = url.openConnection();
		      try (BufferedReader reader = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), "UTF-8"))) {
		    	  while (reader.ready()) {
		    	    //System.out.println(reader.readLine());
		    		  tmp+=reader.readLine();
		    	  }
		      }
		}catch (Exception e) {
		      e.printStackTrace();
		    }
		 try {
		 File filetmp = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.xml");


			BufferedWriter bw = new BufferedWriter(new FileWriter(filetmp));
			bw.write(tmp);
			bw.close();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		//File file = new File("C:\\Users\\Shingo\\git\\MyGRR\\NDL_LOD\\dcdnl_test\\tmp.xml");
		 File file = new File("C:\\Users\\AILab08\\git\\MyGRR\\NDL_LOD\\dcndl_test\\tmp.xml");

		System.out.println("一時作成されたXMLファイルから以下の取得対象のものを抽出する");
		System.out.println("取得対象：タイトル、著者名、出版社、ページ数、ISBN、NDLC（請求記号）、NDC9、資料種別");

		SAXReader reader = new SAXReader();
	    try {
	      Document doc = reader.read(file);
	      Element root = doc.getRootElement();
	      Element element;
	      for (Iterator i = root.elementIterator(); i.hasNext();) {
	    	  element = (Element) i.next();
	    	  System.out.println(element.getName()+" "+element.getNodeTypeName()+" "+element.nodeCount());
			  System.out.println("テキストを取り出す:" + element.getStringValue());
	      }


	    }catch (DocumentException e) {
	        e.printStackTrace();
	    }

	    //if(file.delete())
	    	System.out.println("xml file deleted");
	}

	static boolean eqaulTarget(int comp) {
		for(int i=0;i<targetNodeNum.length;i++) {
			if(comp==targetNodeNum[i])
				return true;
		}
		return false;
	}

	private static boolean checkBeforeWritefile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canWrite()){
	        return true;
	      }
	    }

	    return false;
	  }


}
