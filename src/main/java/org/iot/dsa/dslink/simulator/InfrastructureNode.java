package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class InfrastructureNode extends DSNode {

    private int pollRate;

    public InfrastructureNode() {

    }

    public InfrastructureNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    @Override
    protected void onStable() {
        createInfrastructureNodes();
    }

    @Override
    protected void onSubscribed() {
        super.onSubscribed();
    }

    private void createInfrastructureNodes() {
        put(Constants.BACNET, new BacNetNode(pollRate));
        put(Constants.MODBUS, new ModBusNode(pollRate));
    }
}