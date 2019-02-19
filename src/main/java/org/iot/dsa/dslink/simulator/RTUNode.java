package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSNode;

public class RTUNode extends DSNode implements Runnable {

    public RTUNode() { }

    public RTUNode(int pollRate) {
        setRTUDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.COOLING, DSBool.valueOf(false));
        declareDefault(Constants.HEATING, DSBool.valueOf(false));
    }

    private void setRTUDataNodeMetrics() {
        put(Constants.SAT, Util.getFloatRandom(99.00, 40.00)).setReadOnly(true);
    }

    @Override
    public void run() {
        setRTUDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}
