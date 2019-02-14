package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSNode;

public class RTUNode extends DSNode implements Runnable {

    private int pollRate;
    private DSRuntime.Timer timer;

    public RTUNode() {

    }

    public RTUNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }


    @Override
    protected void onStable() {
        put(Constants.COOLING, DSBool.valueOf(false));
        put(Constants.HEATING, DSBool.valueOf(false));
        setRTUDataNodeMetrics();
        startTimer(this.pollRate);
    }

    private void setRTUDataNodeMetrics() {
        put(Constants.SAT, Util.getFloatRandom(99.00, 40.00));
    }

    /**
     * Called by the timer, increments the counter.
     */
    @Override
    public void run() {
        setRTUDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        timer.cancel();
    }
}
