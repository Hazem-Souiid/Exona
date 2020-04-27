package EXONA.OAEI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shazem
 */
public class Align {

	org.semanticweb.owlapi.model.OWLEntity E1;
	org.semanticweb.owlapi.model.OWLEntity E2;
	double Sim;
	String Rel;

	public Align() {
	}

	public Align(org.semanticweb.owlapi.model.OWLEntity E1, org.semanticweb.owlapi.model.OWLEntity E2, double Sim,
			String Rel) {
		this.E1 = E1;
		this.E2 = E2;
		this.Sim = Sim;
		this.Rel = Rel;
	}

	public org.semanticweb.owlapi.model.OWLEntity getE1() {
		return this.E1;
	}

	public void setE1(org.semanticweb.owlapi.model.OWLEntity name) {
		this.E1 = name;
	}

	public org.semanticweb.owlapi.model.OWLEntity getE2() {
		return this.E2;
	}

	public void setE2(org.semanticweb.owlapi.model.OWLEntity Label) {
		this.E2 = Label;
	}

	public double getSim() {
		return this.Sim;
	}

	public void setSim(double Sim) {
		this.Sim = Sim;
	}

	public String getRel() {
		return this.Rel;
	}

	public void setRel(String name) {
		this.Rel = name;
	}

}
