package EXONA.OAEI;

public class InstanceProp {

	String Sujet;
	String Object;
	String Predicat;

	public InstanceProp() {
	}

	public String getSujet() {
		return this.Sujet;
	}

	public void setSujet(String name) {
		this.Sujet = name;
	}

	public String getObjet() {
		return this.Object;
	}

	public void setObjet(String name) {
		this.Object = name;
	}

	public String getPredicat() {
		return this.Predicat;
	}

	public void setPredicat(String name) {
		this.Predicat = name;
	}

}
