package org.iot.dsa.dslink.simulator;

import org.iot.dsa.dslink.DSMainNode;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSMap;
import org.iot.dsa.node.DSString;
import org.iot.dsa.node.DSValueType;
import org.iot.dsa.node.action.ActionInvocation;
import org.iot.dsa.node.action.ActionResult;
import org.iot.dsa.node.action.DSAction;
import org.iot.dsa.node.action.DSActionValues;

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
        DSAction act = new DSAction.Parameterless() {
            @Override
            public ActionResult invoke(DSInfo info, ActionInvocation invocation) {
                return ((MainNode) info.get()).updatePR(this,invocation.getParameters());
            }
        };
        act.addParameter(Constants.VALUE, DSValueType.NUMBER, "PollRate");
        return act;
    }

    private ActionResult updatePR(DSAction action,DSMap parameters){
        DSActionValues actionResult = new DSActionValues(action);
        displaySubNodes(Integer.parseInt(parameters.getString(Constants.VALUE)));
        return actionResult;
    }

    private void displaySubNodes(int pollRate) {
        put(Constants.DISPPOLLRATE, pollRate);
        put(Constants.PROPELLERS, new PropellersNode(pollRate));
        put(Constants.WATERSYSTEM, new WaterSystemNode(pollRate));
        put(Constants.INFRASTRUCTURE, new InfrastructureNode(pollRate));
    }
}
