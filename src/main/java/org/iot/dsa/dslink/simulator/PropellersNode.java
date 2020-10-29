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
        String[] strArr = createPumpNodes();
        for (int index = 0; index < strArr.length; index++) {
            String linkName = strArr[index];
            put(linkName, new PumpNode(pollRate));
        }
    }

    public String[] createPumpNodes() {
        String[] pumpnodes = new String[16000];
        for(int i = 0; i < 16000; i++) {
            pumpnodes[i] ="Pump " + i;
        }
        return pumpnodes;
    }
}