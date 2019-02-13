package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSNode;

public class PumpNode extends DSNode implements Runnable {

    private int pollRate = 10;
    private DSRuntime.Timer timer;

    public PumpNode() {

    }

    public PumpNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }


    @Override
    protected void onStable() {
        put(Constants.BOOLEAN, DSBool.valueOf(false));
        setPumpDataNodeMetrics();
        System.out.println("pumpnode pollRate: "+pollRate);
        startTimer(pollRate);
    }

    private void setPumpDataNodeMetrics() {
        put(Constants.SPEED, Util.sample());
    }

    /**
     * Called by the timer, increments the counter.
     */
    @Override
    public void run() {
        setPumpDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        System.out.println("seconds: "+seconds);
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        timer.cancel();
    }
}
