package EXONA.OAEI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;

import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;

import org.semanticweb.owlapi.model.OWLNamedObject;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class Ontology_Parser {

	public Ontology_Parser() {

	}

	public Instance GetInstanceFromIndividual(OWLIndividual in, org.semanticweb.owlapi.model.OWLOntology ontology,
			OWLNamedObject entity) {
		ArrayList<InstanceProp> listProp = new ArrayList<InstanceProp>();
		java.util.Map<OWLDataPropertyExpression, Set<OWLLiteral>> label = in.getDataPropertyValues(ontology);
		Instance inst = new Instance();
		for (OWLDataPropertyExpression key : label.keySet()) {

			Iterator<OWLEntity> pr = key.getSignature().iterator();
			Iterator<OWLLiteral> obj = label.get(key).iterator();
			while (pr.hasNext() && obj.hasNext()) {
				OWLLiteral obj1 = obj.next();
				OWLEntity pr1 = pr.next();
				InstanceProp propriete = new InstanceProp();
				propriete.setObjet(obj1.getLiteral());
				propriete.setPredicat(pr1.getIRI().getFragment());
				propriete.setSujet(((OWLNamedObject) in).getIRI().getFragment());
				listProp.add(propriete);
			}
		}
		@SuppressWarnings("unused")
		String Label = "";
		IRI cIRI = in.asOWLNamedIndividual().getIRI();
		for (OWLAnnotationAssertionAxiom a : ontology.getAnnotationAssertionAxioms(cIRI)) {
			if (a.getProperty().isLabel()) {
				if (a.getValue() instanceof OWLLiteral) {
					OWLLiteral val = (OWLLiteral) a.getValue();
					Label += val.getLiteral() + " ";

				}
			}
		}
		// if(Label.length()>2){
		inst.setId(((OWLNamedObject) in).getIRI().getFragment());
		@SuppressWarnings("unused")
		ArrayList<String> listclass = new ArrayList<String>();
		@SuppressWarnings("unused")
		Iterator<OWLClass> ListClass = in.getClassesInSignature().iterator();

		inst.setClas(entity.getIRI().getFragment());

		inst.setINdividual(in.asOWLNamedIndividual());
		inst.setInstanceProp(listProp);
		return (inst);
	}

	public Ontology Parser(URL path) throws Exception {

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		org.semanticweb.owlapi.model.OWLOntology ontology = null;
		try {
			ontology = manager.loadOntologyFromOntologyDocument(new File(path.getFile()));
		} catch (OWLOntologyCreationException e) {
			System.out.println("Cannot Parse This Ontology Format !! ");
		}
		Ontology o = new Ontology();
		ArrayList<Concept> ListConcept = new ArrayList<Concept>();
		for (org.semanticweb.owlapi.model.OWLEntity entity : ontology.getSignature()) {
			if (entity.isOWLClass()) {
				ArrayList<Instance> ListInstance = new ArrayList<Instance>();
				Concept C = new Concept();
				C.setName(entity.getIRI().getFragment());
				C.setOWLClass(entity);
				Set<OWLIndividual> indiv = entity.asOWLClass().getIndividuals(ontology);
				Iterator<OWLIndividual> indiv_it = indiv.iterator();
				while (indiv_it.hasNext()) {

					OWLIndividual indiv1 = indiv_it.next();
					Instance inst = GetInstanceFromIndividual(indiv1, ontology, entity);

					ArrayList<Instance> ListNeighbor = new ArrayList<Instance>();
					Map<OWLObjectPropertyExpression, Set<OWLIndividual>> prop = indiv1
							.getObjectPropertyValues(ontology);

					for (OWLObjectPropertyExpression key : prop.keySet()) {
						Iterator<OWLIndividual> obj = prop.get(key).iterator();
						while (obj.hasNext())

							ListNeighbor.add(
									GetInstanceFromIndividual(obj.next().asOWLNamedIndividual(), ontology, entity));
					}

					inst.setListInstNeighbor(ListNeighbor);
					ListInstance.add(inst);
				}
				C.setListInstance(ListInstance);
				ListConcept.add(C);
				// }
			}
		}
		o.setListConcept(ListConcept);
		return (o);
	}

}
