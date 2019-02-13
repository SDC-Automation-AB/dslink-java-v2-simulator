package org.iot.dsa.dslink.simulator;

import org.iot.dsa.dslink.DSMainNode;

public class MainNode extends DSMainNode {

    public MainNode() {
    }

    /**
     * Defines the permanent children of this node type, their existence is guaranteed in all
     * instances.  This is only ever called once per, type per process.
     */
    @Override
    protected void declareDefaults() {
        super.declareDefaults();
        displaySubNodes();
    }

    private void displaySubNodes() {
        int pollRate = 60;
        put(Constants.PROPELLERS, new PropellersNode());
        put(Constants.WATERSYSTEM, new WaterSystemNode(pollRate));
        put(Constants.INFRASTRUCTURE, new InfrastructureNode(pollRate));
    }
}
