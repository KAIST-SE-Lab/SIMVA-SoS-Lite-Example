import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;
import model.sos.SmartTransportSoS;

public class Main {
    /**
     * @param args args[0]: isGuiMode (0/others)
     *             args[1]: isOnMape (0/others)
     *             args[2]: is
     */
    public static void main(String[] args) {
        /* Input SimModel */
        //ToySoS toySoS = new ToySoS(); // no id
        SmartTransportSoS smartTransportSoS = new SmartTransportSoS("STSOS1", "STSoS");

        /* Input SimConfiguration */
        //TODO: remove this section after debugging
        SimConfiguration smartTransportConfiguration = new SimConfiguration();
        smartTransportConfiguration.setSimTotalTime(1);

        /* Input SimScenario */
        //TODO: remove this section after debugging
        SimScenario smartTransportScenario = new SimScenario("Scenario01", false);
        // addScenarioEvents(exampleScenario, exampleConfig);

        Execution.main(args, smartTransportSoS, smartTransportConfiguration, smartTransportScenario);

    }
}
