package model.sos;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;

import java.util.ArrayList;

public class SmartTransportOrgType extends Organization {
    public SmartTransportOrgType(SoS simModel, String orgId, String orgName) {
        super(simModel, orgId, orgName);
    }

    public SmartTransportOrgType(SoS simModel, String orgId, String orgName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        super(simModel, orgId, orgName, isStatic, isActivated, isAvailable);
    }

    @Override
    public ArrayList<_SimAction_> selectMoveActions(ArrayList<_SimAction_> possibleMoveActions) {
        return null;
    }
}
