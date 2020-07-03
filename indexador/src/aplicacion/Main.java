package aplicacion;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.tartarus.snowball.ext.SpanishStemmer;

import Controller.PantallaController;
import View.Pantalla;

public class Main{
	
	/*public static void limpiarPaginas() {
		File dir = new File(".\\");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      String[] extensiones = child.getAbsolutePath().split("\\.");
		      String extension = extensiones[extensiones.length-1];
		      if(extension.contentEquals("html")) {
		    	  child.delete();
		      }
		    }
		  }
	}
	
	public static void abrirPagina(String titulo,String refs,String coleccion) throws IOException {
		InputStream is = new FileInputStream(".\\archivos\\" + coleccion); 
    	BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
    	String line = buf.readLine(); 
    	StringBuilder sb = new StringBuilder(); 
    	while(line != null){ 
    		sb.append(line).append("\n"); line = buf.readLine(); 
    	} 
    	String docToString = sb.toString();
    	String[] paginasSeparadas = docToString.split("<html");
    	for(int i=0; i < paginasSeparadas.length;i++) {
    		paginasSeparadas[i] = "<html" + paginasSeparadas[i];
    		String[] separarLineas = paginasSeparadas[i].split("\n");
    		String lineaCache = "";
    		for(int j=0; j < separarLineas.length-1;j++) {
    			lineaCache += separarLineas[j]+"\n";
    		}
    		paginasSeparadas[i] = lineaCache;
    	}
    	String paginaBuscada = "";
    	for(String pagina:paginasSeparadas) {
    		if(FileAnalyzer.sacarTitle(pagina).equals(titulo+" ") && FileAnalyzer.sacarRefs(pagina).equals(refs)) {
    			paginaBuscada = pagina;
    			break;
    		}
    	}
    	File htmlFile = new File(".\\" + titulo + ".html");
    	FileWriter fileWriter = new FileWriter(titulo+".html");
    	fileWriter.write(paginaBuscada);
    	fileWriter.close();
    	Desktop desktop = Desktop.getDesktop();
    	desktop.open(htmlFile);
	}*/

    public static void main(String[] args) throws IOException {
    	
    	//abrirPagina("alon harazi wikipedia la enciclopedia libre","refs","wiki-p1.txt");
    	
        /*FileAnalyzer.fillStopWords(); //crear la lista estatica de stopwords
        
    	String indexp1 = ".\\indexp1";//".\\index"
    	String indexp2 = ".\\indexp2";
    	String indexg1 = ".\\indexg1";
    	String indexg2 = ".\\indexg2";
    	Indexer.createIndex(".\\archivos\\wiki-p1.txt",indexp1);
    	Indexer.createIndex(".\\archivos\\wiki-p2.txt",indexp2);
    	Indexer.createIndex(".\\archivos\\wiki-g1.txt",indexg1);
    	Indexer.createIndex(".\\archivos\\wiki-g2.txt",indexg2);*/
        
        Pantalla frame= new Pantalla();
        
        PantallaController controller = new PantallaController(frame);
        frame.setVisible(true);
    	
    	
    	//System.out.println("STEMMER : ->>>"+FileAnalyzer.stemming("consultas caries ingeniero"));
 /*
        try {
			//Searcher.searchFile("ref:'tenistas de macedonia'");
        	//listTitle = Searcher.searchFile("magno AND superficie");
        	listTitle = Searcher.searchFile("wikipedia", index,20);
        	//Searcher.searchFile("amintas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
    }
}
