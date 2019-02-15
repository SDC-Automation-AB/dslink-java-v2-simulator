package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSNode;
import org.iot.dsa.node.DSString;

public class StatNode extends DSNode implements Runnable {

    public StatNode() { }

    public StatNode(int pollRate) {
        setStatDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.MODEL, DSString.valueOf("VT8600"));
        declareDefault(Constants.MODE, Util.getStatModes());
        put(Constants.SETPOINT, DSInt.valueOf(1));
    }

    private void setStatDataNodeMetrics() {
        put(Constants.CURRENTTEMP, Util.getFloatRandom(99.00, 40.00));
        put(Constants.HUMIDITY, Util.getIntRandom(100, 0));
    }

    @Override
    public void run() {
        setStatDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}
