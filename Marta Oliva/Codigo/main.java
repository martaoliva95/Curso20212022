package jenaProject;

import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

/**
 * 
 * @author moliva
 *
 */
public class main {

	public static String ontologia = "http://ejemplo-empradronados.es/CM/municipio#";
	public static String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static String owl = "http://www.w3.org/2002/07/owl#";
	public static String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
	public static String foaf = "http://xmlns.com/foaf/0.1/";

	
	public static void main(String[] args) {
		
		// Archivo de datos
		String filename = "cm-csv.ttl";
		
		// Creación de un modelo vacío
		Model model = ModelFactory.createDefaultModel();
		
		// Uso de Filemanager para localizar el archivo de entrada
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");

		// Lectura del archivo en la serialización 'TTL'
		model.read(in, null, "TTL");
		
		// Variables a usar
		String queryString;
		Query query;
		QueryExecution qexec;
		ResultSet results;
		
		// CASOS DE APLICACIÓN
		// Query 1: Mostrar el municipio con código 903
		
		System.out.println("Caso 1: Municipio de la Comunidad de Madrid con código 903\n");
		queryString =
				"PREFIX ontologia: <" + ontologia + "> " +
				"SELECT ?municipio_codigo " +
				"WHERE { ?municipio_codigo ontologia:hasCode '903'.} ";
				
		query = QueryFactory.create(queryString);
		qexec = QueryExecutionFactory.create(query, model) ;
		results = qexec.execSelect() ;
		
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource resource = (Resource) binding.get("municipio_codigo");
			System.out.printf(resource.getURI());
		}
		
	}

}
