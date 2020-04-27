package EXONA.OAEI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author shazem
 */
public class Instance {

	String Id;
	ArrayList<InstanceProp> Instance_Prop = new ArrayList<InstanceProp>();
	String Label;
	org.semanticweb.owlapi.model.OWLEntity ind;
	String Class;
	ArrayList<Instance> ListInstNeighbor = new ArrayList<Instance>();

	public Instance() {
	}

	public org.semanticweb.owlapi.model.OWLEntity getINdividual() {
		return this.ind;
	}

	public void setINdividual(org.semanticweb.owlapi.model.OWLEntity entity) {
		this.ind = entity;
	}

	public String getId() {
		return this.Id;
	}

	public void setId(String name) {
		this.Id = name;
	}

	public String getLabel() {
		return this.Label;
	}

	public void setClas(String name) {
		this.Class = name;
	}

	public String getClas() {
		return this.Class;
	}

	public void setLabel(String name) {
		this.Label = name;
	}

	public ArrayList<InstanceProp> getInstanceProp() {
		return this.Instance_Prop;
	}

	public void setInstanceProp(ArrayList<InstanceProp> list) {
		this.Instance_Prop = list;
	}

	public ArrayList<Instance> getListInstNeighbor(ArrayList<Instance> l) {
		return (this.ListInstNeighbor);
	}

	public void setListInstNeighbor(ArrayList<Instance> l) {
		this.ListInstNeighbor = l;
	}

	public String toString() {
		String res = " ";
		for (int i = 0; i < this.Instance_Prop.size(); i++)
			res += this.Instance_Prop.get(i).getObjet() + " ";
		for (int x = 0; x < this.ListInstNeighbor.size(); x++)
			res += this.ListInstNeighbor.get(x).toString();
		return (res);
	}

	public void Print() {
		System.out.println("Id " + this.getId());
		System.out.println("Label " + this.getLabel());
		for (int j = 0; j < this.Instance_Prop.size(); j++) {
			System.out.println("*************************Prop*************************************");
			System.out.println("Sujet : " + this.getInstanceProp().get(j).getSujet());
			System.out.println("Object : " + this.getInstanceProp().get(j).getObjet());
			System.out.println("Pridicat : " + this.getInstanceProp().get(j).getPredicat());

		}
		System.out.println("Neighbor");
		for (int x = 0; x < this.ListInstNeighbor.size(); x++)
			this.ListInstNeighbor.get(x).Print();
		System.out.println("///////////////////////////////////////////////////////////////////");

	}
}
