package EXONA.OAEI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Enumeration;

import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.Cell;
import org.semanticweb.owl.align.Evaluator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

import fr.inrialpes.exmo.align.impl.BasicParameters;
import fr.inrialpes.exmo.align.impl.eval.PRecEvaluator;
import fr.inrialpes.exmo.align.parser.AlignmentParser;

/**
 *
 * @author shazem
 */
public class Evaluate {
	public static void Evale(String al, String ref) throws AlignmentException, MalformedURLException {
		AlignmentParser aparser = new AlignmentParser(0);
		// Changed by Angel for Windows
		// Alignment reference = aparser.parse( "file://"+(new File (
		// "refalign.rdf" ) . getAbsolutePath()) );
		Alignment reference = aparser.parse((new File(ref)).toURI());
		Alignment match = aparser.parse((new File(al)).toURI());
		Evaluator evaluator = new PRecEvaluator(reference, match);
		evaluator.eval(new BasicParameters());
		System.out.println(evaluator.getResults());

	}

	public static void ShowInst(Individual ind) throws IOException {

		System.out.println("Label: " + ind.getLabel(null));

		StmtIterator it = ind.listProperties();
		while (it.hasNext()) {
			Statement s = (Statement) it.next();
			if (s.getObject().isLiteral()) {
				System.out.println(
						s.getPredicate().getLocalName() + " : " + (s.getLiteral().getLexicalForm()).toLowerCase());

			}
		}

	}

	public static void Test(String ref, OntModel inf, OntModel inf1) throws AlignmentException, IOException {
		AlignmentParser aparser = new AlignmentParser(0);
		Alignment reference = aparser.parse((new File(ref)).toURI());
		Enumeration<Cell> elements = reference.getElements();
		Cell el = null;
		while (elements.hasMoreElements()) {
			System.out.println("===============================================================><map>");
			System.out.println("***************************************************************><cell>");
			el = elements.nextElement();
			System.out.println("Entity1: " + inf.getIndividual(el.getObject1().toString()).getLocalName());
			ShowInst(inf.getIndividual(el.getObject1().toString()));
			System.out.println("***************************************************************></cell>");
			System.out.println();
			System.out.println("***************************************************************><cell>");
			System.out.println("Entity2: " + inf1.getIndividual(el.getObject2().toString()).getLocalName());
			ShowInst(inf1.getIndividual(el.getObject2().toString()));
			System.out.println("***************************************************************</cell>");
			System.out.println("===============================================================></map>");
			System.out.println();
			System.out.println();
		}

	}

	public static void main(String[] args) throws AlignmentException, IOException {
		String inputFileName = "a.owl";
		String inputFileName1 = "b.owl";

		System.out.println("Debut");
		// create the reasoning model using the base
		OntModel inf = ModelFactory.createOntologyModel();
		OntModel inf1 = ModelFactory.createOntologyModel();

		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(inputFileName);
		InputStream in1 = FileManager.get().open(inputFileName1);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}

		inf.read(in, "");
		inf1.read(in1, "");

		Test("ref.rdf", inf, inf1);
	}
}
