package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class WaterSystemNode extends DSNode {

    public WaterSystemNode() { }

    public WaterSystemNode(int pollRate) {
        createWaterSystemNodes(pollRate);
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    private void createWaterSystemNodes(int pollRate) {
        String[] strArr = {"Heater 1", "Heater 2", "Heater 3", "Heater 4"};
        for (int index = 0; index < strArr.length; index++) {
            String linkName = strArr[index];
            put(linkName, new HeaterNode(pollRate));
        }
    }
}