package EXONA.OAEI;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.AlignmentProcess;
import org.semanticweb.owl.align.AlignmentVisitor;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import fr.inrialpes.exmo.align.impl.method.StringDistAlignment;
import fr.inrialpes.exmo.align.impl.renderer.RDFRendererVisitor;

public class Align2Rander {
	public static void ToRdf(String ont1, String ont2, ArrayList<Align> AlignTab, String filename)
			throws UnknownHostException, OWLOntologyCreationException, AlignmentException, URISyntaxException,
			FileNotFoundException, UnsupportedEncodingException {

		AlignmentProcess alignment = null;
		Properties params = new Properties();
		params.setProperty("noinst", "1");
		alignment = new StringDistAlignment();
		alignment.init(new URI(ont1), new URI(ont2));
		for (Align AlignTab1 : AlignTab) {
			// System.out.println("Rendering: "+AlignTab1.E1+ " ==>
			// "+AlignTab1.E2);
			if (AlignTab1.E1 != null && AlignTab1.E2 != null) {
				alignment.addAlignCell(AlignTab1.E1, AlignTab1.E2, "=", AlignTab1.getSim());
				// System.out.println("Rendering: "+AlignTab1.E1+ " ==>
				// "+AlignTab1.E2);
			}
		}
		FileOutputStream stream = new FileOutputStream(filename);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream, "UTF-8")), true);
		AlignmentVisitor renderer = new RDFRendererVisitor(writer);
		alignment.render(renderer);
		writer.flush();
		writer.close();
	}
}
