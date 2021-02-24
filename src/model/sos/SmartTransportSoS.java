package model.sos;

import kr.ac.kaist.se.model.sos.EnumOrgType;
import kr.ac.kaist.se.model.sos.SoS;
import model.sos.geo.SmartTransportMap;

import java.sql.Timestamp;

public class SmartTransportSoS extends SoS {

    public SmartTransportSoS(String sosId, String sosName) {
        super(sosId, sosName);
    }

    @Override
    protected void initSoSModel() {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  ===================================================================");

        initMap();
        initOrgsCSs();
        initData();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]  -------------------------------------------------------------------");
        System.out.println("[" + timestamp + "] (SmartTransportSoS:initSoSModel) An SoS model is initialized.");
        System.out.println("[" + timestamp + "]  ===================================================================");

        printModelInfo();
    }

    /**
     * Initialization of a map
     */
    @Override
    protected void initMap() {
        SmartTransportMap smartTransportMap = new SmartTransportMap("MAP01", "STMap");

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoS:initMap) An SoSMap is initialized.");

        this.sosMap = smartTransportMap;
    }

    /**
     * Initialization of organizations, and constituents
     */
    private void initOrgsCSs() {
        // TODO: optimize SoS - Org - CS linking system
        SmartTransportOrgType smartTransportOrg = new SmartTransportOrgType(this, "STOrg01", "STOrganization01");
        TrainsOrgType trainsOrg = new TrainsOrgType(this, "STOrg02", "STOrganization02");

        smartTransportOrg.setOrgType(EnumOrgType.COLLABORATIVE);
        trainsOrg.setOrgType(EnumOrgType.COLLABORATIVE);

        smartTransportOrg.addSubOrg(trainsOrg);
        this.addOrg(smartTransportOrg);

        TrainCSType trainCS = new TrainCSType(this, null, "CSTrain01", "Train01");
        trainsOrg.addCS(trainCS, true);
        this.addCS(trainCS);
        FactoryCSType factoryCS = new FactoryCSType(this, smartTransportOrg, "CSFactory01", "Factory01");
        smartTransportOrg.addCS(factoryCS, true);
        this.addCS(factoryCS);
    }

    /**
     * Initialization of data
     */
    private void initData() {

    }
}
