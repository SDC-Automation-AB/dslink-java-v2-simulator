package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class PropellersNode extends DSNode {


    public PropellersNode() { }

    public PropellersNode(int pollRate) {
        createPumpNodes(pollRate);
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    private void createPumpNodes(int pollRate) {
        String[] strArr = {"Pump 1", "Pump 2", "Pump 3", "Pump 4"};
        for (int index = 0; index < strArr.length; index++) {
            String linkName = strArr[index];
            put(linkName, new PumpNode(pollRate));
        }
    }
}