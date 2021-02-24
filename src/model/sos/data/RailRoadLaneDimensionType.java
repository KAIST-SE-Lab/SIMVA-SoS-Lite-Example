package model.sos.data;

import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

import java.sql.Timestamp;

public class RailRoadLaneDimensionType extends DimensionVar {
    public RailRoadLaneDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue) {
        super(dataId, dataName, dataType, dataDefaultValue);
    }

    public RailRoadLaneDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataDomain);
    }

    public RailRoadLaneDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }

    @Override
    public boolean checkUpdateValid(int diff) {
        //Since MapFloorDimensionType has enumeration dataType,
        //it should have its proper update mechanism.
        if (stringData != null) {
            int enumIndex = -1;
            int index = 0;

            for (String enumItem : varDomain.getDomainEnumVal()) {
//                System.out.print(enumItem + ",");
                if (stringData.equals(enumItem)) {
                    enumIndex = index;
                }
                index++;
            }
            //If current value is not found in the enumValue of its domain
            if (enumIndex == -1) {
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (RailRoadLaneDimensionType:checkUpdateValid) Corresponding enum value is not found.");

                return false;
            }

            //If enumIndex is properly found
            else {
                //If it is out of domain
                if (enumIndex + diff >= varDomain.getDomainEnumVal().size() || enumIndex + diff < 0) {
                    timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println("[" + timestamp + "] (RailRoadLaneDimensionType:checkUpdateValid) Out of domain: not moved.");

                    return false;
                }
            }
        } else {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (RailRoadLaneDimensionType:checkUpdateValid) Current value is not specified yet (stringData == null).");

            return false;
        }

        return true;
    }

    @Override
    public boolean updateValueOfDim(int diff) {
        //TODO: Loop of Enumeration or Min/Max constraints

        int enumIndex = -1;
        int index = 0;

        for (String enumItem : varDomain.getDomainEnumVal()) {
//                System.out.print(enumItem + ",");
            if (stringData.equals(enumItem)) {
                enumIndex = index;
            }
            index++;
        }

        int newEnumIndex;
        newEnumIndex = enumIndex + diff;

//        System.out.println(enumIndex + "," + newEnumIndex + "");

        //Update the value
        this.setDataCurValue(varDomain.getDomainEnumVal().get(newEnumIndex));

        return true;

    }
}
