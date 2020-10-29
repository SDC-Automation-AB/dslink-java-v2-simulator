package org.iot.dsa.dslink.simulator;

import org.iot.dsa.dslink.ActionResults;
import org.iot.dsa.dslink.DSMainNode;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSMap;
import org.iot.dsa.node.DSString;
import org.iot.dsa.node.action.DSAction;
import org.iot.dsa.node.action.DSIActionRequest;

public class MainNode extends DSMainNode {

    public MainNode() { }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
        declareDefault(Constants.POLLRATE, updatePollRate());
        declareDefault(Constants.DISPPOLLRATE, DSInt.valueOf("")).setReadOnly(true);
        declareDefault("Help",
                DSString.valueOf("https://github.com/iot-dsa-v2/dslink-java-v2-simulator"))
                .setTransient(true)
                .setReadOnly(true);
    }

    @Override
    protected void onStable() {
        displaySubNodes(Constants.DEFAULTPOLLRATE);
    }

    private DSAction updatePollRate() {
        DSAction addAction = new DSAction() {
            @Override
            public ActionResults invoke(DSIActionRequest target) {
                ((MainNode) target.getTarget()).updatePR(target.getParameters());
                return null;
            }
        };
        addAction.addParameter(Constants.VALUE, DSInt.NULL, "PollRate");
        return addAction;
    }

    private void updatePR(DSMap parameters){
        displaySubNodes(Integer.parseInt(parameters.getString(Constants.VALUE)));
    }

    private void displaySubNodes(int pollRate) {
        put(Constants.DISPPOLLRATE, pollRate);
        put(Constants.PROPELLERS, new PropellersNode(pollRate));
        put(Constants.WATERSYSTEM, new WaterSystemNode(pollRate));
        put(Constants.INFRASTRUCTURE, new InfrastructureNode(pollRate));
    }
}
