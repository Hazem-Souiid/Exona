package EXONA.OAEI;

import java.util.ArrayList;

public class Condidat {

	Instance instance;
	ArrayList<Instance> ListInstance = new ArrayList<Instance>();

	public Condidat() {
	}

	public Instance getInstance() {
		return this.instance;
	}

	public void setnstance(Instance name) {
		this.instance = name;
	}

	public ArrayList<Instance> getListInstance() {
		return this.ListInstance;
	}

	public void setListInstance(ArrayList<Instance> name) {
		this.ListInstance = name;
	}

}
