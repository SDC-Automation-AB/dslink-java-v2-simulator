package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class BacNetNode extends DSNode {

    private int pollRate;

    public BacNetNode() {

    }

    public BacNetNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    @Override
    protected void onStable() {
        createBacNetNodes();
    }

    @Override
    protected void onSubscribed() {
        super.onSubscribed();
    }

    private void createBacNetNodes() {
        put(Constants.STAT1, new StatNode(this.pollRate));
        put(Constants.STAT2, new StatNode(this.pollRate));
        put(Constants.RTU1, new RTUNode(this.pollRate));
        put(Constants.RTU2, new RTUNode(this.pollRate));
    }
}
