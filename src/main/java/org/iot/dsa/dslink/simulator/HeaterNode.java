package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSInt;
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
        declareDefault(Constants.TARGETTEMP, DSInt.valueOf(1));
    }

    private void setHeaterDataNodeMetrics() {
        put(Constants.CURRENTTEMP, Util.getFloatRandom(85.00, 60.00));
        put(Constants.WATTAGE, Util.getIntRandom(4000,0));
    }

    @Override
    public void run() {
        setHeaterDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}
