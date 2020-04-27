package EXONA.OAEI;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

public class CondidatGenerator {
	public CondidatGenerator() {

	}

	public static Instance getIndividual(String word, ArrayList<Instance> tab) {
		for (int i = 0; i < tab.size(); i++) {// System.out.println(word);
												// System.out.println(tab.get(i).getId().equals(word));
			if (tab.get(i).getINdividual().getIRI().toString().contains(word)) {// System.out.println(tab.get(i).getINdividual().toString().contains(word));
				return (tab.get(i));

			}
		}
		return (null);
	}

	public ArrayList<Condidat> GenerateListCondidatFromIndex(ArrayList<Instance> tab, String indexFolder,
			ArrayList<Instance> t) throws IOException, ParseException {

		ArrayList<Condidat> ListCondidat = new ArrayList<Condidat>();
		for (int i = 0; i < tab.size(); i++) {
			ArrayList<String> e = new ArrayList<String>();

			e = SearchEngine.search(tab.get(i).toString(), indexFolder);
			Condidat cond = new Condidat();
			cond.setnstance(tab.get(i));
			ArrayList<Instance> ListCondInst = new ArrayList<Instance>();
			for (int j = 0; j < e.size(); j++) {
				ListCondInst.add(getIndividual(e.get(j), t));
			}
			cond.setListInstance(ListCondInst);
			ListCondidat.add(cond);
		}
		return (ListCondidat);
	}

}
