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
		
		// Query 2: Número de mujeres de 0 a 4 años en municipio con código 903
		System.out.println("\n\nCaso 2: Número de mujeres de 0 a 4 años en municipio con código 903. \n");
		queryString =
				"PREFIX ontologia: <" + ontologia + "> " +
						"SELECT ?Subject ?municipio_codigo ?poblacion_empadronada " +
						"WHERE { ?Subject ontologia:hasGender 'Mujer'. " +
						"?Subject ontologia:hasRange 'municipio/De-0-a-4-años'. " +
						"?municipio_codigo ontologia:hasAgeGender ?Subject. " +
						"?municipio_codigo ontologia:hasCode '903'. " +
						"?Subject ontologia:hasPopulation ?poblacion_empadronada. } ";
		
		query = QueryFactory.create(queryString);
		qexec = QueryExecutionFactory.create(query, model) ;
		results = qexec.execSelect() ;
		
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource municipio = (Resource) binding.get("municipio_codigo");
			Literal poblacion = (Literal) binding.get("poblacion_empadronada");
			System.out.printf("El municipio " + municipio.getLocalName() + " tiene " + poblacion.getInt() + " mujeres de 0 a 4 años." + "\n");
		}
		
		// Query 3: Número de mujeres de diferentes rangos de edad en municipio con código 903
		System.out.println("\nCaso 3: Número de mujeres de diferentes rangos de edad en municipio con código 903. \n");
		queryString =
				"PREFIX ontologia: <" + ontologia + "> " +
						"SELECT ?Subject ?municipio_codigo ?poblacion_empadronada ?rango_edad " +
						"WHERE { ?Subject ontologia:hasGender 'Mujer'. " +
						"?Subject ontologia:hasRange ?rango_edad. " +
						"?municipio_codigo ontologia:hasAgeGender ?Subject. " +
						"?municipio_codigo ontologia:hasCode '903'. " +
						"?Subject ontologia:hasPopulation ?poblacion_empadronada. } ";
		
		query = QueryFactory.create(queryString);
		qexec = QueryExecutionFactory.create(query, model) ;
		results = qexec.execSelect() ;
		
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource municipio = (Resource) binding.get("municipio_codigo");
			Literal rango_de_edad = (Literal) binding.get("rango_edad");
			Literal poblacion = (Literal) binding.get("poblacion_empadronada");
			System.out.printf("El municipio " + municipio.getLocalName() + " tiene " + poblacion.getInt() + " personas empadronadas en el " + rango_de_edad + " ." + "\n");
		}
		
		// Query 4: Número de mujeres en municipio con código 903
		System.out.println("\nCaso 4: Número total de mujeres empadronadas en municipio con código 903. \n");
		queryString =
				"PREFIX ontologia: <" + ontologia + "> " +
						"SELECT (SUM(?poblacion_empadronada) AS ?suma) ?Subject ?municipio_codigo ?rango_edad " +
						"WHERE { ?Subject ontologia:hasGender 'Mujer'. " +
						"?Subject ontologia:hasRange ?rango_edad. " +
						"?municipio_codigo ontologia:hasAgeGender ?Subject. " +
						"?municipio_codigo ontologia:hasCode '903'. " +
						"?Subject ontologia:hasPopulation ?poblacion_empadronada. } ";
		
		query = QueryFactory.create(queryString);
		qexec = QueryExecutionFactory.create(query, model) ;
		results = qexec.execSelect() ;
		
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource municipio = (Resource) binding.get("municipio_codigo");
			Literal poblacion = (Literal) binding.get("suma");
			System.out.printf("El municipio " + municipio.getLocalName() + " tiene " + poblacion.getInt() + " personas empadronadas. " + "\n");
		}
		
	}

}
