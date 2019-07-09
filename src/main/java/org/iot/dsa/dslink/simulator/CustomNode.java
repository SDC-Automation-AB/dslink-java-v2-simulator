package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.logging.DSLogger;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSMap;
import org.iot.dsa.node.DSNode;
import org.iot.dsa.node.DSValueType;
import org.iot.dsa.node.action.ActionInvocation;
import org.iot.dsa.node.action.ActionResult;
import org.iot.dsa.node.action.DSAction;
import org.iot.dsa.node.action.DSActionValues;

import java.util.HashMap;
import java.util.Map;

public class CustomNode extends DSNode implements Runnable {

    private HashMap<String, String> nodeNameMapSet = new HashMap<>();
    private HashMap<String, String> nodeNameMap = new HashMap<>();
    private DSRuntime.Timer timer;
    public CustomNode() { }

    public CustomNode(int pollRate) {
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.ADDNODE, addCustomNode());
        declareDefault(Constants.VALUE, addCustomValue());
        declareDefault(Constants.POLLRATE, updateNodePollRate());
        declareDefault(Constants.DISPPOLLRATE, DSInt.valueOf("")).setReadOnly(true);
    }

    @Override
    protected void onStable() {
        put(Constants.DISPPOLLRATE, Constants.DEFAULTPOLLRATE);
    }

    private DSAction updateNodePollRate() {
        DSAction act = new DSAction.Parameterless() {
            @Override
            public ActionResult invoke(DSInfo info, ActionInvocation invocation) {
                return ((CustomNode) info.get()).updateNodePR(this,invocation.getParameters());
            }
        };
        act.addParameter(Constants.VALUE, DSValueType.NUMBER, "PollRate");
        return act;
    }

    private ActionResult updateNodePR(DSAction action,DSMap parameters){
        DSActionValues actionResult = new DSActionValues(action);
        stopTimer();
        startTimer(Integer.parseInt(parameters.getString(Constants.VALUE)));
        put(Constants.DISPPOLLRATE, Integer.parseInt(parameters.getString(Constants.VALUE)));
        return actionResult;
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
        log.info("string match");
        DSActionValues actionResult = new DSActionValues(action);
        String valName = parameters.getString(Constants.NAME);
        switch(parameters.getString(Constants.TYPE)) {
            case "string":
                log.info("string match");
                if(!parameters.getBoolean(Constants.READWRITE)) {
                    put(valName, parameters.getString(Constants.VALUE)).setReadOnly(true);
                } else {
                    put(valName, parameters.getString(Constants.VALUE));
                }
                break;
            case "number":
                log.info("number match");
                addNumberValue(valName, parameters.getString(Constants.VALUE), parameters.getBoolean(Constants.READWRITE));
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

    private void addNumberValue(String valName, String value, Boolean rwFlag) {
        Float nodeVal;
        if(value.contains(":")) {
            String[] arrOfVal = value.split(":");
            nodeVal = Util.getFloatRandom(Float.parseFloat(arrOfVal[1]),
                    Float.parseFloat(arrOfVal[0]));
            if(!rwFlag) {
                nodeNameMapSet.put(valName, value);
                put(valName, nodeVal).setReadOnly(true);
            } else {
                nodeNameMap.put(valName, value);
                put(valName, nodeVal);
            }
        } else {
            nodeVal = Float.parseFloat(value);
            if(!rwFlag) {
                put(valName, nodeVal).setReadOnly(true);
            } else {
                put(valName, nodeVal);
            }
        }

    }

    private void setCustomNodeValue() {
        for (Map.Entry<String,String> entry : nodeNameMapSet.entrySet()) {
            String[] arrOfVal = entry.getValue().split(":");
            put(entry.getKey(), Util.getFloatRandom(Float.parseFloat(arrOfVal[1]),
                    Float.parseFloat(arrOfVal[0]))).setReadOnly(true);
        }
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

    @Override
    public void run() {
        setCustomNodeValue();
    }

    private void startTimer(int seconds) {
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onChildChanged(DSInfo info) {
        for (Map.Entry<String,String> entry : nodeNameMap.entrySet()) {
            String[] arrOfVal = entry.getValue().split(":");
            if((info.getName() == entry.getKey()) &&
                    (Util.checkFloatRange(Double.parseDouble(info.getValue().toString()),
                            Float.parseFloat(arrOfVal[1]), Float.parseFloat(arrOfVal[0])))) {
                put(entry.getKey(), Util.getFloatRandom(Float.parseFloat(arrOfVal[1]),
                        Float.parseFloat(arrOfVal[0])));
            }
        }
    }
}