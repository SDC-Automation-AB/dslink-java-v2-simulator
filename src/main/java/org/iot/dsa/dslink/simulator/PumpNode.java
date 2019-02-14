package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSNode;

public class PumpNode extends DSNode implements Runnable {

    private int pollRate;
    private DSRuntime.Timer timer;

    public PumpNode() {

    }

    public PumpNode(int pollRate) {
        this.pollRate = pollRate;
        put(Constants.BOOLEAN, DSBool.valueOf(false));
        setPumpDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }


//    @Override
//    protected void onStable() {
//        put(Constants.BOOLEAN, DSBool.valueOf(false));
//        setPumpDataNodeMetrics();
//        startTimer(pollRate);
//    }

    private void setPumpDataNodeMetrics() {
        put(Constants.SPEED, Util.getFloatRandom(10.00,1.00));
    }

    /**
     * Called by the timer, increments the counter.
     */
    @Override
    public void run() {
        setPumpDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        System.out.println("Seconds :" + seconds);
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        timer.cancel();
    }
}
