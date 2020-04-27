package EXONA.OAEI;

import java.util.ArrayList;

public class Ontology {
	int Size;
	ArrayList<Concept> ListConcept = new ArrayList<Concept>();

	public Ontology() {

	}

	public ArrayList<Concept> getListConcept() {
		return (this.ListConcept);
	}

	public void setListConcept(ArrayList<Concept> l) {
		this.ListConcept = l;
	}

	public int GetSize() {
		int S = 0;

		for (int i = 0; i < this.ListConcept.size(); i++)
			S += this.ListConcept.get(i).getListInstance().size();

		return (S);
	}

	public ArrayList<Instance> getListNeighbor(Instance inst) {
		ArrayList<Instance> Listnbi = new ArrayList<Instance>();

		for (int i = 0; i < this.ListConcept.size(); i++) {
			ArrayList<Instance> listinst = new ArrayList<Instance>();
			for (int j = 0; j < listinst.size(); j++) {
				Instance inst2 = listinst.get(j);
				for (int k = 0; k < inst2.getInstanceProp().size(); k++)
					if (EditDistance.getSimilarity(inst.toString(), inst2.getInstanceProp().get(k).getObjet()) > 0.8)
						Listnbi.add(inst2);

			}
		}

		return (Listnbi);

	}

	public void Print() {
		for (int i = 0; i < this.ListConcept.size(); i++)
			for (int j = 0; j < this.ListConcept.get(i).ListInstance.size(); j++) {
				System.out.println("_________________________________________________________");
				System.out.println("Instance:  " + this.ListConcept.get(i).ListInstance.get(j).toString());
				System.out.println("Concept:  " + this.ListConcept.get(i).getName());
				System.out.println("---------------------------------------------------------");
				ArrayList<Instance> nbghr = this.getListConcept().get(i).getListInstance().get(j).ListInstNeighbor;
				System.out.println("Size:  " + nbghr.size());
				for (int k = 0; k < nbghr.size(); k++)
					System.out.println("Instance Neighbor:  " + nbghr.get(k).toString());
			}
	}

}
