package EXONA.OAEI;

import java.util.ArrayList;

public class Concept {
	String Name = "";
	org.semanticweb.owlapi.model.OWLEntity Class;
	ArrayList<Instance> ListInstance = new ArrayList<Instance>();

	public Concept() {

	}

	public void setOWLClass(org.semanticweb.owlapi.model.OWLEntity cl) {
		this.Class = cl;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public void setListInstance(ArrayList<Instance> cl) {
		this.ListInstance = cl;
	}

	public String getName() {
		return (this.Name);
	}

	public ArrayList<Instance> getListInstance() {
		return (this.ListInstance);
	}

	public org.semanticweb.owlapi.model.OWLEntity getOWLClass() {
		return (this.Class);
	}

}
