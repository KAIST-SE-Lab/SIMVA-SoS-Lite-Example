package model.sos.geo;

import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.sos.geo.SimMap;
import model.sos.data.CyclicRailRoadDimensionType;
import model.sos.data.CyclicRailRoadMapDimensionVarDomain;
import model.sos.data.RailRoadLaneDimensionType;
import model.sos.data.RailRoadLaneMapDimensionVarDomain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class SmartTransportMap extends SimMap {

    public static CyclicRailRoadDimensionType cyclicRailRoadDim;
    public static RailRoadLaneDimensionType railRoadLaneDim;

    public SmartTransportMap(String mapId, String mapName) {
        super(mapId, mapName);
        this.buildMap("");
    }

    @Override
    protected void initMapDimensions() {
        /* Definition of domains for each dimension variable */
        CyclicRailRoadMapDimensionVarDomain cyclicRailRoadDomain = new CyclicRailRoadMapDimensionVarDomain(EnumDomainType.VALUE_RANGE, 0, 99);
        RailRoadLaneMapDimensionVarDomain railRoadLaneDomain = new RailRoadLaneMapDimensionVarDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("RAIL", "FACTORY")));

        /* Initialization of every dimension with the domains defined */
        cyclicRailRoadDim = new CyclicRailRoadDimensionType("DIM_ROAD", "RoadDimension", "Int", "0", cyclicRailRoadDomain);
        railRoadLaneDim = new RailRoadLaneDimensionType("DIM_LANE", "LaneDimension", "Enum", "RAIL", railRoadLaneDomain);

        /* Add dimensions to this map */
        this.mapDimVars.add(cyclicRailRoadDim);
        this.mapDimVars.add(railRoadLaneDim);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Dimensions are initialized (size:" + this.mapDimVars.size() + ")");
    }

    @Override
    protected void initMapInformation() {

    }

    @Override
    protected void buildMap(String mapInitInfo) {

    }
}
