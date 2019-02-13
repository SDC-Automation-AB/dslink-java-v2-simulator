package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSNode;

public class HeaterNode extends DSNode implements Runnable {

    private int pollRate;
    private DSRuntime.Timer timer;

    public HeaterNode() {

    }

    public HeaterNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }


    @Override
    protected void onStable() {
        put(Constants.BOOLEAN, DSBool.valueOf(false));
        put(Constants.TARGETTEMP, DSInt.valueOf(1));
        setHeaterDataNodeMetrics();
        startTimer(this.pollRate);
    }

    private void setHeaterDataNodeMetrics() {
        put(Constants.CURRENTTEMP, Util.sample());
        put(Constants.WATTAGE, Util.sample());
    }

    /**
     * Called by the timer, increments the counter.
     */
    @Override
    public void run() {
        setHeaterDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        timer.cancel();
    }
}
