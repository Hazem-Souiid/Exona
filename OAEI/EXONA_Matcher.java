package EXONA.OAEI;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

public class EXONA_Matcher {
	Properties p = new Properties();

	public EXONA_Matcher(Properties p) throws FileNotFoundException, IOException {
		this.p = p;

	}

	public URL Match(URL path1, URL path2, Properties prr) throws Exception {
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("***************************|                         |******************************");
		System.out.println("************************|        EXONA Process         |****************************");
		System.out.println("***************************|                         |******************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println();
		System.out.println();
		Ontology_Parser op = new Ontology_Parser();
		System.out.println("***************************                         ********************************");
		System.out.println("************************   Parsing and Preprocessing   *****************************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		Ontology onto1 = op.Parser(path1);
		Ontology onto2 = op.Parser(path2);

		System.out.println("***************************                         ********************************");
		System.out.println("************************         Indexation            *****************************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		for (int i = 0; i < onto1.getListConcept().size(); i++) {
			Indexer in = new Indexer();
			String indexFolder = onto1.getListConcept().get(i).getName();
			in.rebuildIndexes(onto1.getListConcept().get(i).getListInstance(), indexFolder);
		}
		System.out.println("***************************                         ********************************");
		System.out.println("************   Crossing and candidate alignments lists computation   ***************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		CondidatGenerator CG = new CondidatGenerator();
		ArrayList<Condidat> ListCondidat = new ArrayList<Condidat>();

		if (prr.getProperty("task").equals("1"))
			ListCondidat = CG.GenerateListCondidatFromIndex(onto2.getListConcept().get(1).getListInstance(), "Person",
					onto1.getListConcept().get(0).getListInstance());
		else
			ListCondidat = CG.GenerateListCondidatFromIndex(onto2.getListConcept().get(0).getListInstance(), "Person",
					onto1.getListConcept().get(1).getListInstance());

		AlignGenerator AG = new AlignGenerator();
		ArrayList<Align> Alignment = AG.GenerateAlignFromCondidat_VERSION1(ListCondidat);
		Align2Rander.ToRdf(path1.toString(), path2.toString(), Alignment, "alignment.rdf");
		System.out.println("***************************                         ********************************");
		System.out.println("************************       Alignment Generation        *************************");
		System.out.println("***************************                         ********************************");
		return (new URL("file:alignment.rdf"));
	}
	/*
	 * public static void main(String[] args) throws Exception {
	 * 
	 * 
	 * 
	 * 
	 * 
	 * URL AlignFile; String
	 * Path_To_Property="/home/hazem/workspace/FST_EXONA/config.properties";
	 * Properties pr = new Properties(); pr.load(new
	 * FileInputStream(Path_To_Property)); EXONA_Matcher em = new
	 * EXONA_Matcher(pr);
	 * 
	 * AlignFile= em.Match(new URL(pr.getProperty("ontology1")), new
	 * URL(pr.getProperty("ontology2")),pr);
	 * 
	 * System.out.println(AlignFile.getFile() +" | "
	 * +AlignFile.getProtocol()+"|"+AlignFile.getPath());
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

}
