package model.sos;

import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm.EnumMsgType;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.sos.Constituent;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.model.sos.comm.Message;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import model.sos.cap.DropFuncAction;
import model.sos.data.CyclicRailRoadDimensionType;
import model.sos.geo.SmartTransportMap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TrainCSType extends Constituent {
    public TrainCSType(SoS simModel, Organization myOrg, String csId, String csName) {
        super(simModel, myOrg, csId, csName);
    }

    public TrainCSType(SoS simModel, Organization myOrg, String csId, String csName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        super(simModel, myOrg, csId, csName, isStatic, isActivated, isAvailable);
    }

    @Override
    protected void initActions() {
        initMoveActions();
        initFuncActions();
        initCommActions();
    }

    private void initMoveActions() {
        /* Define allowed dimensions from declared dimensions of its map */
        ArrayList<DimensionVar> allowedDims = new ArrayList<>();

        CyclicRailRoadDimensionType cyclicRailRoadDimVar;

        // Deep copy by clone(): they have different references
        cyclicRailRoadDimVar = (CyclicRailRoadDimensionType) SmartTransportMap.cyclicRailRoadDim.clone();

        // Set domains for each dimension variable
        cyclicRailRoadDimVar.setVarDomain(SmartTransportMap.cyclicRailRoadDim.getVarDomain());

        allowedDims.add(cyclicRailRoadDimVar);

        MoveAction TrainCSMoveForwardAction = new MoveAction(this.mySoS,
                this,
                "TrainMoveAction01",
                "TrainCS-MoveAction01",
                1,
                allowedDims,
                new ArrayList<>(Arrays.asList(1)));

        MoveAction TrainCSMoveBackwardAction = new MoveAction(this.mySoS,
                this,
                "TrainMoveAction02",
                "TrainCS-MoveAction02",
                1,
                allowedDims,
                new ArrayList<>(Arrays.asList(1)));

        capableActionList.add(TrainCSMoveForwardAction);
        capableActionList.add(TrainCSMoveBackwardAction);
    }

    /**
     * Initialization of functional actions (FuncAction)
     */
    private void initFuncActions() {
        DropFuncAction dropFuncAction = new DropFuncAction(this.mySoS,
                this,
                "DROP_FUNC_ACTION_01",
                "Drop Func Action - 01",
                1, 3, 100);

        capableActionList.add(dropFuncAction);
    }

    @Override
    public void initCommActions() {
        //TODO: How to set the communication action to some target?
        CommAction commAction01 = new CommAction(mySoS,
                this,
                "COMM_ACTION01",
                "CommunicationAction01",
                null);

        capableActionList.add(commAction01);
    }

    @Override
    public String findReceiverObjIdFromSoS(String condition) {
        return null;
    }

    @Override
    public _SimMessage_ makeMsgForCommAction(CommAction aCommAction) {
        Message message = new Message("MSG01", "MSG01tag", EnumMsgType.GENERAL_MSG);

        message.setSenderId(this.getId());

        //TODO: how to access to target's id?
        message.setReceiverId("ORG01A");

        ArrayList<DataVar> msgDataList = new ArrayList<>();
        msgDataList.add(new DataVar("MSG01_DATAVAR01",
                "Data variable 01 of MSG01",
                "Int",
                "0",
                "30"));

        message.setMsgDataList(msgDataList);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]");

        return message;
    }

    @Override
    public ArrayList<_SimAction_> selectMoveActions(ArrayList<_SimAction_> possibleMoveActions) {
        ArrayList<_SimAction_> selectedMoveActions = new ArrayList<>();

        if (possibleMoveActions != null && possibleMoveActions.size() > 0) {
            Random rand = new Random();
            int selectedMoveActionIndex = rand.nextInt(possibleMoveActions.size());

            selectedMoveActions.add(possibleMoveActions.get(selectedMoveActionIndex));
        }

        if (selectedMoveActions.size() != 0) {
            return selectedMoveActions;
        } else {
            return null;
        }
    }

    @Override
    protected void initObjLocation() {
        ObjectLocation thisObjLocation;

        if (mySoS != null && mySoS.sosMap != null) {
            ArrayList<DimensionVar> mapDims = new ArrayList<>();

            // Deep copy by clone(): they have different references
            for (DimensionVar dimVar : mySoS.getSosMap().getMapDimVars()) {
                DimensionVar clonedDimVar = (DimensionVar) dimVar.clone();

                mapDims.add(clonedDimVar);
            }

            thisObjLocation = new ObjectLocation(mapDims);
            thisObjLocation.getObjLocDimVars().get(0).setDataCurValue("0");
            thisObjLocation.getObjLocDimVars().get(1).setDataCurValue("RAIL");

            this.objLocation = thisObjLocation;
        }
    }
}
