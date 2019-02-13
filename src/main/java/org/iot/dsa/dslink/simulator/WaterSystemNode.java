package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class WaterSystemNode extends DSNode {

    private int pollRate;

    public WaterSystemNode() {

    }

    public WaterSystemNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    @Override
    protected void onStable() {
        createWaterSystemNodes();
    }

    @Override
    protected void onSubscribed() {
        super.onSubscribed();
    }

    private void createWaterSystemNodes() {
        String[] strArr = {"Heater 1", "Heater 2", "Heater 3", "Heater 4"};
        for (int index = 0; index < strArr.length; index++) {
            String linkName = strArr[index];
            put(linkName, new HeaterNode(this.pollRate));
        }
    }
}