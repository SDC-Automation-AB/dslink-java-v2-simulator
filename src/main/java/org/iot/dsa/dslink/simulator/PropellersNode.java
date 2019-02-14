package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class PropellersNode extends DSNode {

    private int pollRate;

    public PropellersNode() {

    }

    public PropellersNode(int pollRate) {
        this.pollRate = pollRate;
        createPumpNodes();
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

//    @Override
//    protected void onStable() {
//        createPumpNodes();
//    }

    @Override
    protected void onSubscribed() {
        super.onSubscribed();
    }

    private void createPumpNodes() {
        String[] strArr = {"Pump 1", "Pump 2", "Pump 3", "Pump 4"};
        for (int index = 0; index < strArr.length; index++) {
            String linkName = strArr[index];
            System.out.println("createPumpNodes - pollRate :" + pollRate);
            put(linkName, new PumpNode(pollRate));
        }
    }
}