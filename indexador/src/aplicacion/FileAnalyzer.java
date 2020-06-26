package aplicacion;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileAnalyzer {
	private static List<String> stopWords;
	
	private FileAnalyzer () {}
	
	public static void fillStopWords() {
		String stopWordsString = "a " + 
				"acá " + 
				"ahí " + 
				"ajena " + 
				"ajenas " + 
				"ajeno " + 
				"ajenos " + 
				"al " + 
				"algo " + 
				"algún " + 
				"alguna " + 
				"algunas " + 
				"alguno " + 
				"algunos " + 
				"allá " + 
				"alli " + 
				"allí " + 
				"ambos " + 
				"ampleamos " + 
				"ante " + 
				"antes " + 
				"aquel " + 
				"aquella " + 
				"aquellas " + 
				"aquello " + 
				"aquellos " + 
				"aqui " + 
				"aquí " + 
				"arriba " + 
				"asi " + 
				"atras " + 
				"aun " + 
				"aunque " + 
				"bajo " + 
				"bastante " + 
				"bien " + 
				"cabe " + 
				"cada " + 
				"casi " + 
				"cierta " + 
				"ciertas " + 
				"cierto " + 
				"ciertos " + 
				"como " + 
				"cómo " + 
				"con " + 
				"conmigo " + 
				"conseguimos " + 
				"conseguir " + 
				"consigo " + 
				"consigue " + 
				"consiguen " + 
				"consigues " + 
				"contigo " + 
				"contra " + 
				"cual " + 
				"cuales " + 
				"cualquier " + 
				"cualquiera " + 
				"cualquieras " + 
				"cuan " + 
				"cuán " + 
				"cuando " + 
				"cuanta " + 
				"cuánta " + 
				"cuantas " + 
				"cuántas " + 
				"cuanto " + 
				"cuánto " + 
				"cuantos " + 
				"cuántos " + 
				"de " + 
				"dejar " + 
				"del " + 
				"demás " + 
				"demas " + 
				"demasiada " + 
				"demasiadas " + 
				"demasiado " + 
				"demasiados " + 
				"dentro " + 
				"desde " + 
				"donde " + 
				"dos " + 
				"el " + 
				"él " + 
				"ella " + 
				"ellas " + 
				"ello " + 
				"ellos " + 
				"empleais " + 
				"emplean " + 
				"emplear " + 
				"empleas " + 
				"empleo " + 
				"en " + 
				"encima " + 
				"entonces " + 
				"entre " + 
				"era " + 
				"eramos " + 
				"eran " + 
				"eras " + 
				"eres " + 
				"es " + 
				"esa " + 
				"esas " + 
				"ese " + 
				"eso " + 
				"esos " + 
				"esta " + 
				"estaba " + 
				"estado " + 
				"estais " + 
				"estamos " + 
				"estan " + 
				"estar " + 
				"estas " + 
				"este " + 
				"esto " + 
				"estos " + 
				"estoy " + 
				"etc " + 
				"fin " + 
				"fue " + 
				"fueron " + 
				"fui " + 
				"fuimos " + 
				"gueno " + 
				"ha " + 
				"hace " + 
				"haceis " + 
				"hacemos " + 
				"hacen " + 
				"hacer " + 
				"haces " + 
				"hacia " + 
				"hago " + 
				"hasta " + 
				"incluso " + 
				"intenta " + 
				"intentais " + 
				"intentamos " + 
				"intentan " + 
				"intentar " + 
				"intentas " + 
				"intento " + 
				"ir " + 
				"jamás " + 
				"junto " + 
				"juntos " + 
				"la " + 
				"largo " + 
				"las " + 
				"lo " + 
				"los " + 
				"mas " + 
				"más " + 
				"me " + 
				"menos " + 
				"mi " + 
				"mía " + 
				"mia " + 
				"mias " + 
				"mientras " + 
				"mio " + 
				"mío " + 
				"mios " + 
				"mis " + 
				"misma " + 
				"mismas " + 
				"mismo " + 
				"mismos " + 
				"modo " + 
				"mucha " + 
				"muchas " + 
				"muchísima " + 
				"muchísimas " + 
				"muchísimo " + 
				"muchísimos " + 
				"mucho " + 
				"muchos " + 
				"muy " + 
				"nada " + 
				"ni " + 
				"ningun " + 
				"ninguna " + 
				"ningunas " + 
				"ninguno " + 
				"ningunos " + 
				"no " + 
				"nos " + 
				"nosotras " + 
				"nosotros " + 
				"nuestra " + 
				"nuestras " + 
				"nuestro " + 
				"nuestros " + 
				"nunca " + 
				"os " + 
				"otra " + 
				"otras " + 
				"otro " + 
				"otros " + 
				"para " + 
				"parecer " + 
				"pero " + 
				"poca " + 
				"pocas " + 
				"poco " + 
				"pocos " + 
				"podeis " + 
				"podemos " + 
				"poder " + 
				"podria " + 
				"podriais " + 
				"podriamos " + 
				"podrian " + 
				"podrias " + 
				"por " + 
				"por qué " + 
				"porque " + 
				"primero " + 
				"primero desde " + 
				"puede " + 
				"pueden " + 
				"puedo " + 
				"pues " + 
				"que " + 
				"qué " + 
				"querer " + 
				"quien " + 
				"quién " + 
				"quienes " + 
				"quienesquiera " + 
				"quienquiera " + 
				"quiza " + 
				"quizas " + 
				"sabe " + 
				"sabeis " + 
				"sabemos " + 
				"saben " + 
				"saber " + 
				"sabes " + 
				"se " + 
				"segun " + 
				"ser " + 
				"si " + 
				"sí " + 
				"siempre " + 
				"siendo " + 
				"sin " + 
				"sín " + 
				"sino " + 
				"so " + 
				"sobre " + 
				"sois " + 
				"solamente " + 
				"solo " + 
				"somos " + 
				"soy " + 
				"sr " + 
				"sra " + 
				"sres " + 
				"sta " + 
				"su " + 
				"sus " + 
				"suya " + 
				"suyas " + 
				"suyo " + 
				"suyos " + 
				"tal " + 
				"tales " + 
				"también " + 
				"tambien " + 
				"tampoco " + 
				"tan " + 
				"tanta " + 
				"tantas " + 
				"tanto " + 
				"tantos " + 
				"te " + 
				"teneis " + 
				"tenemos " + 
				"tener " + 
				"tengo " + 
				"ti " + 
				"tiempo " + 
				"tiene " + 
				"tienen " + 
				"toda " + 
				"todas " + 
				"todo " + 
				"todos " + 
				"tomar " + 
				"trabaja " + 
				"trabajais " + 
				"trabajamos " + 
				"trabajan " + 
				"trabajar " + 
				"trabajas " + 
				"trabajo " + 
				"tras " + 
				"tú " + 
				"tu " + 
				"tus " + 
				"tuya " + 
				"tuyo " + 
				"tuyos " + 
				"ultimo " + 
				"un " + 
				"una " + 
				"unas " + 
				"uno " + 
				"unos " + 
				"usa " + 
				"usais " + 
				"usamos " + 
				"usan " + 
				"usar " + 
				"usas " + 
				"uso " + 
				"usted " + 
				"ustedes " + 
				"va " + 
				"vais " + 
				"valor " + 
				"vamos " + 
				"van " + 
				"varias " + 
				"varios " + 
				"vaya " + 
				"verdad " + 
				"verdadera " + 
				"vosotras " + 
				"vosotros " + 
				"voy " + 
				"vuestra " + 
				"vuestras " + 
				"vuestro " + 
				"vuestros " + 
				"y " + 
				"ya " + 
				"yo";
		String[] stopWordsArray = stopWordsString.split(" ");
		//System.out.println("Primer stopword: " + stopWordsArray[0]);
		stopWords = Arrays.asList(stopWordsArray);
	}
	
	private static String removeStopWords(String input) {
		List<String> textoCompleto = new ArrayList<>(Arrays.asList(input.toLowerCase().split(" ")));
		textoCompleto.removeAll(stopWords);
		String resultado = String.join(" ",textoCompleto);
		return resultado;
		
	}
	
	private static String stemText(String input) {
		String resultado = "";
		return resultado;
	}
	
	private static String sacarSoloPalabras(String input) {
		String pattern = "(?<=\\s|^)[A-Za-zÁÉÍÓÚÜáéíóúüÑñ]*(?=[.,;:]?\\s|$)";
	    Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(input);
	    String result = "";
	    if (m.find( )) {
	    	result += m.group(0)+" ";
	    	int i = 0;
	    	while (m.find()) {
	    	    for (int j = 0; j <= m.groupCount(); j++) {
	    		    result += m.group(j)+" ";
	    		    i++;
	    		}
	    	}
	    }
		return result;
	}
	
	public static String sacarBody(String contenido) {
        Document doc = Jsoup.parse(contenido,"UTF-8");
        String body = doc.body().text();
        return removeStopWords(sacarSoloPalabras(body));
		
	}
	
	public static String sacarTitle(String contenido) {
		Document doc = Jsoup.parse(contenido,"UTF-8");
        String title = doc.title();
        return sacarSoloPalabras(title).toLowerCase();
		
	}
	
	public static String sacarHeaders(String contenido) {
		Document doc = Jsoup.parse(contenido,"UTF-8");
		String headers = doc.select("h1, h2, h3, h4, h5, h6, h7, h8, h9").text();
		return removeStopWords(sacarSoloPalabras(headers));
		
	}

	public static String sacarRefs(String contenido) {
		Document doc = Jsoup.parse(contenido,"UTF-8");
		String refs = doc.select("a").text();
		return sacarSoloPalabras(refs).toLowerCase();
	
	}

}
