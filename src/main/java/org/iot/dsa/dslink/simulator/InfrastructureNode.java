package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSNode;

public class InfrastructureNode extends DSNode {

    public InfrastructureNode() { }

    public InfrastructureNode(int pollRate) {
        createInfrastructureNodes(pollRate);
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    private void createInfrastructureNodes(int pollRate) {
        put(Constants.BACNET, new BacNetNode(pollRate));
        put(Constants.MODBUS, new ModBusNode(pollRate));
    }
}