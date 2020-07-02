package aplicacion;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.tartarus.snowball.ext.SpanishStemmer;

import Controller.PantallaController;
import View.Pantalla;

public class Main{

    public static void main(String[] args) throws IOException {
    	
    	
    	
        FileAnalyzer.fillStopWords(); //crear la lista estatica de stopwords
        
    	/*String indexp1 = ".\\indexp1";//".\\index"
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
