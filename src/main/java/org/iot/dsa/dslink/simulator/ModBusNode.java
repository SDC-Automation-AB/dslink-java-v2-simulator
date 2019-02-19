package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSNode;

public class ModBusNode extends DSNode implements Runnable {

    public ModBusNode() { }

    public ModBusNode(int pollRate) {
        setModBusDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        super.declareDefaults();
    }

    private void setModBusDataNodeMetrics() {
        put(Constants.VOLTAGE1, Util.getIntRandom(130,107)).setReadOnly(true);
        put(Constants.VOLTAGE2, Util.getIntRandom(130,107)).setReadOnly(true);
        put(Constants.VOLTAGE3, Util.getIntRandom(130,107)).setReadOnly(true);
        put(Constants.AVGTEMP, Util.getIntRandom(80,60)).setReadOnly(true);
        put(Constants.AVGHUMIDITY, Util.getIntRandom(30,0)).setReadOnly(true);
        put(Constants.AVGENERGY, Util.getIntRandom(110,60)).setReadOnly(true);
        put(Constants.TOTALPWR, Util.getIntRandom(100,20)).setReadOnly(true);
        put(Constants.OTHERPWR1, Util.getIntRandom(100,20)).setReadOnly(true);
        put(Constants.OTHERPWR2, Util.getIntRandom(100,20)).setReadOnly(true);
    }

    @Override
    public void run() {
        setModBusDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}
