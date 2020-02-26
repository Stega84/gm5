package WildCodeSchoolProject.GiftMeFive.util;

	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLConnection;

	import org.jdom.JDOMException;
	import org.jdom.input.SAXBuilder;
	import org.jdom.output.XMLOutputter;

	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.pdf.PdfWriter;
	import com.itextpdf.tool.xml.XMLWorkerHelper;
	 
	public class WebPageToPdf{
		
	public static void saveHTML(String weblink, String fileName) throws DocumentException, IOException {
		
		URL url;
		 
		  try {
		   //----------------------- HTML CREATION ------------------------
		   // get URL content
		   url = new URL(weblink);
		   URLConnection conn = url.openConnection();
		 
		   // open the stream and put it into BufferedReader
		   BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		   String inputLine;
		 
		   //save to this filename
		   File file = new File(fileName);
		   if (!file.exists()) {
		    file.createNewFile();
		   }
		   //use FileWriter to write file
		   FileWriter fw = new FileWriter(file.getAbsoluteFile());
		   BufferedWriter bw = new BufferedWriter(fw);
		   while ((inputLine = br.readLine()) != null) {
		    bw.write(inputLine);
//		    System.out.println(inputLine);
		   }
		   bw.close();
		   br.close();
		   System.out.println("Html Creation Done");
		  } catch (MalformedURLException e) {
			   e.printStackTrace();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		   //----------------------- HTML CREATION ------------------------
		  
// Skip transformation to PDF because errors are caused by nested HTML tags in Page
//		    
//			String filename = "temp_html.html";
//			Document document = new Document();
//		    PdfWriter writer = PdfWriter.getInstance(document,
//		      new FileOutputStream("html.pdf"));
//		    document.open();
//		    XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//		      new FileInputStream(filename));
//		    document.close();
	}
	
}

