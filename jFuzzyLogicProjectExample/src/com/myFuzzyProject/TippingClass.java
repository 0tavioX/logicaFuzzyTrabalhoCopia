package com.myFuzzyProject;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class TippingClass {
	public static void main(String[] args) throws Exception {
		String filename = "tipper.fcl";
		FIS fis = FIS.load(filename, true);

		if (fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);

		// Set inputs
		fb.setVariable("defesa", 70); //entre 40 e 110 
		fb.setVariable("peso", 8); // entre 1 e 30

		// Evaluate
		fb.evaluate();

		// Show output variable's chart
		//JFuzzyChart.get().chart(fis);
		fb.getVariable("eficiencia").defuzzify();

		// Print ruleSet
		System.out.println(fb);
		System.out.println("Nota: " + String.format("%.2f", fb.getVariable("eficiencia").getValue()));
		
		double valor = fb.getVariable("eficiencia").getValue();
		if(valor <= 3) {
			System.out.println("Eficiência: Ruim");
		}else if(valor >= 7) {
			System.out.println("Eficiência: Excelente");
		}else {
			System.out.println("Eficiência: Bom");
		}

	}

}
