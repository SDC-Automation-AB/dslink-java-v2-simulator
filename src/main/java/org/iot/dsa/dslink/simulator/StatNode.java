package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSNode;

public class StatNode extends DSNode implements Runnable {

    private int pollRate;
    private DSRuntime.Timer timer;

    public StatNode() {

    }

    public StatNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }


    @Override
    protected void onStable() {
        put(Constants.MODEL, "VT8600");
        put(Constants.MODE, DSBool.valueOf(false));
        put(Constants.SETPOINT, DSInt.valueOf(1));
        setStatDataNodeMetrics();
        startTimer(this.pollRate);
    }

    private void setStatDataNodeMetrics() {
        put(Constants.CURRENTTEMP, Util.sample());
        put(Constants.HUMIDITY, Util.sample());
    }

    /**
     * Called by the timer, increments the counter.
     */
    @Override
    public void run() {
        setStatDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        timer.cancel();
    }
}
