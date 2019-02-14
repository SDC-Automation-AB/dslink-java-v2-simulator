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
        put(Constants.VOLTAGE1, Util.getIntRandom(130,107));
        put(Constants.VOLTAGE2, Util.getIntRandom(130,107));
        put(Constants.VOLTAGE3, Util.getIntRandom(130,107));
        put(Constants.AVGTEMP, Util.getIntRandom(80,60));
        put(Constants.AVGHUMIDITY, Util.getIntRandom(30,0));
        put(Constants.AVGENERGY, Util.getIntRandom(110,60));
        put(Constants.TOTALPWR, Util.getIntRandom(100,20));
        put(Constants.OTHERPWR1, Util.getIntRandom(100,20));
        put(Constants.OTHERPWR2, Util.getIntRandom(100,20));
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
