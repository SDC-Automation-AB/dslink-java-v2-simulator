package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSNode;
import org.iot.dsa.node.DSString;

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
        put(Constants.MODEL, DSString.valueOf("VT8600"));
        put(Constants.MODE, Util.getStatModes());
        put(Constants.SETPOINT, DSInt.valueOf(1));
        setStatDataNodeMetrics();
        startTimer(this.pollRate);
    }

    private void setStatDataNodeMetrics() {
        put(Constants.CURRENTTEMP, Util.getFloatRandom(99.00, 40.00));
        put(Constants.HUMIDITY, Util.getIntRandom(100, 0));
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
