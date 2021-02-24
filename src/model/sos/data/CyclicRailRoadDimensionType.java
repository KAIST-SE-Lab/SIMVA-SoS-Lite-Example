package model.sos.data;

import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

import java.sql.Timestamp;


public class CyclicRailRoadDimensionType extends DimensionVar {

    public CyclicRailRoadDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataDomain);
    }

    public CyclicRailRoadDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }

    public CyclicRailRoadDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue) {
        super(dataId, dataName, dataType, dataDefaultValue);
    }

    @Override
    public boolean checkUpdateValid(int diff) {
        // TODO: what if the validity is different based on the other dimensions?
        // ex: only valid if lane == "RAIL" or cyclic < 10
        int newValue = Integer.parseInt(this.getDataCurValue());
        newValue += diff;
        // TODO: Cyclic not working
//        newValue %= 100;

        //Since MapCoordinateDimensionType has integer dataType, it returns false if it is not met.
        if (integerData != null) {
            //If newValue is out of range of varDomain
            if (newValue < varDomain.getDomainMinVal() || newValue > varDomain.getDomainMaxVal()) {
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (CyclicRailRoadDimensionType:checkUpdateValid) Out of domain: not moved.");

                return false;
            }
        } else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (CyclicRailRoadDimensionType:checkUpdateValid) Current value is not specified yet (stringData == null).");

            return false;
        }

        return true;
    }

    @Override
    public boolean updateValueOfDim(int diff) {
        int newValue = Integer.parseInt(this.getDataCurValue());
        newValue += diff;
        // TODO: Cyclic not working
//        newValue %= 100;

        //TODO: Value checking
        //System.out.println(varDomain.isValidValue(newValue));

        //Since MapCoordinateDimensionType has integer dataType, it returns false if it is not met.
        if (integerData != null) {

            //If newValue is out of range of varDomain
            if (newValue < varDomain.getDomainMinVal() || newValue > varDomain.getDomainMaxVal()) {
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (MapCoordinateDimensionType) Out of domain: not moved.");

                return false;
            } else {
                this.setDataCurValue(newValue + "");
            }


        } else {
            return false;
        }
        return true;
    }
}
