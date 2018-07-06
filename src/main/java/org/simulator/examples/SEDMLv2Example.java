package org.simulator.examples;

import de.binfalse.bflog.LOGGER;
import org.jfree.ui.RefineryUtilities;
import org.jlibsedml.*;
import org.jlibsedml.execution.IProcessedSedMLSimulationResults;
import org.jlibsedml.execution.IRawSedmlSimulationResults;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.simulator.math.odes.MultiTable;
import org.simulator.plot.PlotMultiTable;
import org.simulator.sedml.SedMLSBMLSimulatorExecutor;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * This test class shows how a SED-ML file can be interpreted and executed using
 *  SBML Simulator Core solvers. <br/> It makes extensive use of jlibsedml's 
 *  Execution framework which performs boiler-plate code for operations such as 
 *  post-processing of results, etc., This is main test file L1V2 SED-ML elements
 *  such as repeatedTasks and FunctionalRange
 * 
 * @author Shalin Shah
 * @since 1.5
 */
public class SEDMLv2Example {
	private static SedML sedml = null;

	public static void main(String[] args) throws XMLException, OWLOntologyCreationException {
		if(args[0] == null) {
			LOGGER.warn("Please give file file name as argument.");
			return;
		}
		File file = new File(args[0]);
		String sedmlDir = file.getAbsoluteFile().getParentFile().getAbsolutePath();


		LOGGER.warn("Opening file: "+ file + " Collecting tasks...");
		sedml = Libsedml.readDocument(file).getSedMLModel();

		// in this SED-ML file there's just one output. If there were several,
		// we could either iterate or get user to  decide what they want to run.
		Output wanted = sedml.getOutputs().get(0);
		SedMLSBMLSimulatorExecutor exe = new SedMLSBMLSimulatorExecutor(sedml, wanted, sedmlDir);
		// This gets the raw simulation results - one for each Task that was run.
		LOGGER.warn("Collecting tasks...");
		Map<AbstractTask, List<IRawSedmlSimulationResults>> res = exe.run();
		if (res==null ||res.isEmpty() || !exe.isExecuted()) {
			fail ("Simulatation failed: " + exe.getFailureMessages().get(0));
			return;
		}
		// now process.In this case, there's no processing performed - we're displaying the
		// raw results.
		LOGGER.warn("Outputs wanted: " + wanted.getAllDataGeneratorReferences());
		IProcessedSedMLSimulationResults mt = exe.processSimulationResults(wanted, res);
	}

}
