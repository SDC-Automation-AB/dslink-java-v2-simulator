package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSNode;

public class ModBusNode extends DSNode implements Runnable {

    private int pollRate;
    private DSRuntime.Timer timer;

    public ModBusNode() {

    }

    public ModBusNode(int pollRate) {
        this.pollRate = pollRate;
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }


    @Override
    protected void onStable() {
        setModBusDataNodeMetrics();
        startTimer(this.pollRate);
    }

    private void setModBusDataNodeMetrics() {
        put(Constants.VOLTAGE1, Util.sample());
        put(Constants.VOLTAGE2, Util.sample());
        put(Constants.VOLTAGE3, Util.sample());
        put(Constants.AVGTEMP, Util.sample());
        put(Constants.AVGHUMIDITY, Util.sample());
        put(Constants.AVGENERGY, Util.sample());
        put(Constants.TOTALPWR, Util.sample());
        put(Constants.OTHERPWR1, Util.sample());
        put(Constants.OTHERPWR2, Util.sample());
    }

    /**
     * Called by the timer, increments the counter.
     */
    @Override
    public void run() {
        setModBusDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        timer = DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    private void stopTimer() {
        timer.cancel();
    }
}
