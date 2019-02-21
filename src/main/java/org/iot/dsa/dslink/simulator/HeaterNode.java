package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSFloat;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSNode;

public class HeaterNode extends DSNode implements Runnable {

    public HeaterNode() { }

    public HeaterNode(int pollRate) {
        setHeaterDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.BOOLEAN, DSBool.valueOf(false));
        declareDefault(Constants.TARGETTEMP, DSFloat.valueOf("72.50"));
    }

    private void setHeaterDataNodeMetrics() {
        put(Constants.CURRENTTEMP, Util.getFloatRandom(85.00, 60.00)).setReadOnly(true);
        put(Constants.WATTAGE, Util.getIntRandom(4000,0)).setReadOnly(true);
    }

    @Override
    public void run() {
        setHeaterDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    @Override
    protected void onChildChanged(DSInfo info) {
        if((info.getName() == Constants.TARGETTEMP) &&
          (Util.checkFloatRange(Double.parseDouble(info.getValue().toString()), 85.00, 60.00))) {
            put(info.getName(), DSFloat.valueOf("72.50"));
        }
    }
}