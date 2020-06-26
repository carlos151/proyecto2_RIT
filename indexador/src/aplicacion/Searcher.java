package aplicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {
	
	private static Analyzer analyzer = new StandardAnalyzer();
	private static IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

	//private static String index = "C:\\Users\\Carlos\\eclipse-workspace1\\proyecto2_RIT-master\\indexador\\index";
	
	// regular search

	

	private Searcher() {}

	//METODOS
	
	public static  ArrayList<String> searchFile(String _queryString,String index) throws IOException, ParseException {
		
		String field = "texto";
		String queries = null;
		int repeat = 0;
		boolean raw = false;
		String queryString = _queryString;
		System.out.print("Query : "+queryString+"\n");
		int hitsPerPage = 20;
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		//IMPRIME LOS DATO INDEXADOS PRUEBA-----------------------------------------------
		/*for (int i=0; i<reader.maxDoc(); i++) {
			//if (reader.isDeleted(i))
			//continue;

			Document doc = reader.document(i);
			String docId = doc.get("texto");
			//System.out.print("TEXTO : "+docId+"\n");
			// do something with docId here...
		}*/
		//--------------------------------------------------------------------------------
		IndexSearcher searcher = new IndexSearcher(reader);
		BufferedReader in = null;
		QueryParser parser = new QueryParser("texto", analyzer);
		Query query = parser.parse(queryString);
		System.out.println("Searching for: " + query.toString(field));
		System.out.println("Searching field for: " + query.toString());
		//********************************************************************************


		//********************************************************************************
		ArrayList<String> listTitle = new ArrayList<String>();
		listTitle = doSearch(searcher, query, hitsPerPage);    
		reader.close();
		return listTitle;
	}

	static ArrayList<String> doSearch(IndexSearcher searcher, Query query,int hitsPerPage) throws IOException {
		ArrayList<String> listTitle = new ArrayList<String>();
		
		listTitle.clear();//revisar
		
		TopDocs results = searcher.search(query, hitsPerPage);
		System.out.println(" RESULT: "+results.scoreDocs.length);

		ScoreDoc[] hits = results.scoreDocs;

		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");

		int start = 0;
		int end = Math.min(numTotalHits, hitsPerPage);
		System.out.println("END: " + end);

		for (int i = start; i < end; i++) {
			Document doc = searcher.doc(hits[i].doc);
			String title = doc.get("titulo");
			if (title != null) {
				listTitle.add(doc.get("titulo"));
				System.out.println((i+1) + ". TITULO : " + title);
				String text = doc.get("texto");
				if (text != null) {
					//System.out.println("   TEXTO : " + doc.get("texto"));
				}
			} else {
				System.out.println((i+1) + ". " + "No path for this document");
			}
		}
		return listTitle;
	}
}
