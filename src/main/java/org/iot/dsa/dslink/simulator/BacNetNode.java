package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class BacNetNode extends DSNode {

    public BacNetNode() {

    }

    public BacNetNode(int pollRate) {
        createBacNetNodes(pollRate);
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    private void createBacNetNodes(int pollRate) {
        put(Constants.STAT1, new StatNode(pollRate));
        put(Constants.STAT2, new StatNode(pollRate));
        put(Constants.RTU1, new RTUNode(pollRate));
        put(Constants.RTU2, new RTUNode(pollRate));
    }
}
