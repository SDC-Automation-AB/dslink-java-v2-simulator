package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.logging.DSLogger;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSMap;
import org.iot.dsa.node.DSNode;
import org.iot.dsa.node.DSValueType;
import org.iot.dsa.node.action.ActionInvocation;
import org.iot.dsa.node.action.ActionResult;
import org.iot.dsa.node.action.DSAction;
import org.iot.dsa.node.action.DSActionValues;

public class CustomNode extends DSNode implements Runnable {

    private float maxValue = 50;
    private float minValue = 60;
    private String strNodeName;
    public CustomNode() { }

    public CustomNode(int pollRate) {
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.POLLRATE, updatePollRate());
        declareDefault(Constants.ADDNODE, addCustomNode());
        declareDefault(Constants.VALUE, addCustomValue());
    }

    private DSAction addCustomValue() {
        DSAction act = new DSAction.Parameterless() {
            @Override
            public ActionResult invoke(DSInfo info, ActionInvocation invocation) {
                return ((CustomNode) info.get()).custValue(this,invocation.getParameters());
            }
        };
        act.addParameter(Constants.NAME, DSValueType.STRING, "Name");
        act.addParameter(Constants.TYPE, Util.getTypeList(), "Type");
        act.addParameter(Constants.VALUE, DSValueType.STRING, "Default Value");
        act.addParameter(Constants.READWRITE, DSValueType.BOOL, "");
        return act;
    }

    private ActionResult custValue(DSAction action,DSMap parameters){
        DSLogger log = new DSLogger();
        DSActionValues actionResult = new DSActionValues(action);
        String valName = parameters.getString(Constants.NAME);
        switch(parameters.getString(Constants.TYPE)) {
            case "string":
                log.info("string match");
                put(valName, parameters.getString(Constants.VALUE));
                break;
            case "number":
                log.info("number match");
                addNumberValue(valName, parameters.getString(Constants.VALUE));
                break;
            case "bool":
                log.info("bool match");
                put(valName, DSBool.valueOf(false));
                break;
            default:
                log.info("Not in List");
        }
        return actionResult;
    }

    private void addNumberValue(String valName, String value) {
        if(!(value.contains(":"))) {
            put(valName, Integer.parseInt(value)).setReadOnly(true);
            return;
        }
        setNodeName(valName);
        String[] arrOfVal = value.split(":");
        setMinVal(Float.parseFloat(arrOfVal[0]));
        setMaxVal(Float.parseFloat(arrOfVal[1]));

        setCustomNodeValue();
    }

    private void setNodeName(String valName) {
        strNodeName = valName;
    }

    private String getNodeName() {
        return strNodeName;
    }

    private void setMaxVal(float maxVal) {
        maxValue = maxVal;
    }

    private float getMaxVal() {
        return maxValue;
    }

    private void setMinVal(float minVal) {
        minValue = minVal;
    }

    private float getMinVal() {
        return minValue;
    }

    private void setCustomNodeValue() {
        DSLogger log = new DSLogger();
        log.info("Name : " + getNodeName());
        put(getNodeName(), Util.getFloatRandom(getMaxVal(), getMinVal()));
    }

    private DSAction addCustomNode() {
        DSAction act = new DSAction.Parameterless() {
            @Override
            public ActionResult invoke(DSInfo info, ActionInvocation invocation) {
                return ((CustomNode) info.get()).custNode(this,invocation.getParameters());
            }
        };
        act.addParameter(Constants.NAME, DSValueType.STRING, "Name");
        return act;
    }

    private ActionResult custNode(DSAction action,DSMap parameters){
        DSActionValues actionResult = new DSActionValues(action);
        put(parameters.getString(Constants.NAME), new CustomNode(Constants.DEFAULTPOLLRATE));
        return actionResult;
    }

    private DSAction updatePollRate() {
        DSAction act = new DSAction.Parameterless() {
            @Override
            public ActionResult invoke(DSInfo info, ActionInvocation invocation) {
                return ((CustomNode) info.get()).updatePR(this,invocation.getParameters());
            }
        };
        act.addParameter(Constants.VALUE, DSValueType.NUMBER, "PollRate");
        return act;
    }

    private ActionResult updatePR(DSAction action,DSMap parameters){
        DSActionValues actionResult = new DSActionValues(action);
        put(Constants.DISPPOLLRATE, Integer.parseInt(parameters.getString(Constants.VALUE)));
        return actionResult;
    }

    @Override
    public void run() {
        setCustomNodeValue();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}