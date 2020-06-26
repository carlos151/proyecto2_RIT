package aplicacion;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

import Controller.PantallaController;
import View.Pantalla;

public class Main{

    public static void main(String[] args) throws IOException {
        
    	String index = "C:\\Users\\Carlos\\eclipse-workspace1\\proyecto2_RIT-master\\indexador\\index";
    	
        //FileAnalyzer.fillStopWords(); //crear la lista estatica de stopwords
        
        //Indexer.createIndex(".\\archivos\\wiki-p1.txt");
    	Pantalla frame= new Pantalla();;
        PantallaController controller = new PantallaController(frame);
        frame.setVisible(true);
    	
        
        System.out.println("hola");
        ArrayList<String> listTitle = new ArrayList<String>();
        
        try {
			//Searcher.searchFile("ref:'tenistas de macedonia'");
        	//listTitle = Searcher.searchFile("magno AND superficie");
        	listTitle = Searcher.searchFile("wikipedia", index);
        	//Searcher.searchFile("amintas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for(int i = 0 ; i<listTitle.size(); i++ ) {
        	System.out.print(i+1+") TITLE : " + listTitle.get(i)+"\n");
        }
        /*String title = FileAnalyzer.sacarTitle(html);
        System.out.println("Title:" + title);
        
        String body = FileAnalyzer.sacarBody(html);
        System.out.println("Body:" + body);
        
        String headers = FileAnalyzer.sacarHeaders(html);
        System.out.println("Headers:" + headers);
        
        String refs = FileAnalyzer.sacarRefs(html);
        System.out.println("Referencias:" + refs);*/
        
        
    }
}
