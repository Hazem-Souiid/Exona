package EXONA.OAEI;

import java.io.IOException;
import java.util.ArrayList;

import org.simmetrics.StringMetric;
import org.simmetrics.StringMetrics;

public class AlignGenerator {
	public AlignGenerator() {

	}

	public ArrayList<Align> GenerateAlignFromCondidat_VERSION1(ArrayList<Condidat> listCondidat) throws IOException {

		ArrayList<Align> resultat = new ArrayList<Align>();

		// double sim=0.0;
		for (int i = 0; i < listCondidat.size(); i++) {
			for (int j = 0; j < listCondidat.get(i).getListInstance().size(); j++)
				if (listCondidat.get(i).getListInstance().size() > 0) {
					if (listCondidat.get(i).getInstance() != null
							&& listCondidat.get(i).getListInstance().get(j) != null) {// double
																						// sim
																						// =
																						// InstanceSimilarity(listCondidat.get(i).getInstance(),
																						/// listCondidat.get(i).getListInstance().get(j));
																						// if
																						// (sim
																						// <
																						// 0.5)
																						// {
						Align a = new Align();
						a.setE2(listCondidat.get(i).getInstance().getINdividual());
						a.setE1((listCondidat.get(i).getListInstance().get(j).getINdividual()));
						a.setSim(1.0);
						resultat.add(a);
						// }}
					}
				}

		}
		return (resultat);
	}

	public ArrayList<Align> GenerateAlignFromCondidat_VERSION2(ArrayList<Condidat> listCondidat) throws IOException {

		ArrayList<Align> resultat = new ArrayList<Align>();

		// double sim=0.0;
		for (int i = 0; i < listCondidat.size(); i++) {
			if (listCondidat.size() > 0) {
				Instance bestone = getBestInst(listCondidat.get(i).getInstance(),
						listCondidat.get(i).getListInstance());
				if (bestone != null) {
					Align a = new Align();
					a.setE1(listCondidat.get(i).getInstance().getINdividual());
					a.setE2((bestone.getINdividual()));
					a.setSim(InstanceSimilarity(listCondidat.get(i).getInstance(), bestone));
					resultat.add(a);
				}
			}

		}
		return (resultat);
	}

	private static Instance getBestInst(Instance inst, ArrayList<Instance> listinst) {

		Instance best = new Instance();
		double maxsim = 0.0;
		for (int i = 0; i < listinst.size(); i++) {
			double sim = InstanceSimilarity(inst, listinst.get(i));
			// System.out.println("Sim " + sim);
			if (sim > maxsim) {

				maxsim = sim;
				best = listinst.get(i);
			}
		}
		// System.out.println("MaxSim " + maxsim);
		if (maxsim > 0)
			return best;
		else
			return (null);
	}

	private static double InstanceSimilarity(Instance i1, Instance i2) {

		double sim1 = 0.0;
		double sim2 = 0.0;
		StringMetric metric = StringMetrics.overlapCoefficient();
		for (int i = 0; i < i1.getInstanceProp().size(); i++) {

			for (int j = 0; j < i2.getInstanceProp().size(); j++) {
				if (metric.compare(i1.getInstanceProp().get(i).getPredicat(),
						i2.getInstanceProp().get(j).getPredicat()) < 0.5) {

					double t = metric.compare(i1.getInstanceProp().get(i).getObjet(),
							i2.getInstanceProp().get(j).getObjet());
					if (t > 0)
						sim1 += t;

				} else {
					double t1 = metric.compare(i1.getInstanceProp().get(i).getObjet(),
							i2.getInstanceProp().get(j).getObjet());
					if (t1 > 0.8)
						sim2 += t1;
				}
			}
		}
		// System.out.println("Sim1 "+sim1+" Sim2 "+sim2);
		return (0.5 * sim1) + (0.5 * sim2);
	}

}
