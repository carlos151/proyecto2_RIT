package aplicacion;

import org.apache.lucene.LucenePackage;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.tartarus.snowball.ext.SpanishStemmer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Indexer {
	private static String indexPath ;
	private static Directory dir;
	//private static Analyzer analyzer = new StandardAnalyzer();
	private static Analyzer analyzer = new SpanishAnalyzer();
	private static IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
	private static IndexWriter writer;
	
    private Indexer() {}

    /** Indexes a single document */
    static void createIndex(String path,String index) throws IOException {
    	indexPath =  index;
    	Path file = Paths.get(path);
    	dir = FSDirectory.open(Paths.get(indexPath));
    	writer = new IndexWriter(dir, iwc);
    	
    	//Separar el contenido de las páginas en un array
    	InputStream is = new FileInputStream(path); 
    	BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
    	String line = buf.readLine(); 
    	StringBuilder sb = new StringBuilder(); 
    	while(line != null){ 
    		sb.append(line).append("\n"); line = buf.readLine(); 
    	} 
    	String docToString = sb.toString();
    	String[] paginasSeparadas = docToString.split("<html");
    	String paginaActual;
    	
    	try (InputStream stream = Files.newInputStream(file)) {
    		for(int i = 0; i < paginasSeparadas.length; i++) {
	            Document doc = new Document();
	            
	            paginaActual = paginasSeparadas[i];
	            Field bodyField = new TextField("texto",FileAnalyzer.sacarBody(paginaActual),Field.Store.YES);
	            Field refsField = new TextField("ref",FileAnalyzer.sacarRefs(paginaActual),Field.Store.YES);
	            Field headersField = new TextField("encab",FileAnalyzer.sacarHeaders(paginaActual),Field.Store.YES);
	            Field titleField = new TextField("titulo",FileAnalyzer.sacarTitle(paginaActual),Field.Store.YES);
	            
	            doc.add(bodyField);
	            System.out.println("bodyField : "+bodyField.toString()+"\n");
	            doc.add(refsField);
	           // System.out.println("refsField : "+refsField.toString()+"\n");
	            doc.add(headersField);
	          //  System.out.println("headersField : "+headersField.toString()+"\n");
	            doc.add(titleField);
	            System.out.println("titleField : "+titleField.toString()+"\n");
	        
	            
	            writer.addDocument(doc);
    		}
        }
    	writer.close();
    }
    
    static void updateIndex(String path) throws IOException {
    	Path file = Paths.get(path);
    	dir = FSDirectory.open(Paths.get(indexPath));
    	writer = new IndexWriter(dir, iwc);
    	writer.close();
    }
}








