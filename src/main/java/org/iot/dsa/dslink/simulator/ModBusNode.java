package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSElement;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSNode;

public class ModBusNode extends DSNode implements Runnable {

    private final DSInfo voltage1 = getInfo(Constants.VOLTAGE1);
    private final DSInfo voltage2 = getInfo(Constants.VOLTAGE2);
    private final DSInfo voltage3 = getInfo(Constants.VOLTAGE3);
    private final DSInfo avgTemp = getInfo(Constants.AVGTEMP);
    private final DSInfo avgHumidity = getInfo(Constants.AVGHUMIDITY);
    private final DSInfo avgEnergy = getInfo(Constants.AVGENERGY);
    private final DSInfo totalPwr = getInfo(Constants.TOTALPWR);
    private final DSInfo otherPwr1 = getInfo(Constants.OTHERPWR1);
    private final DSInfo otherPwr2 = getInfo(Constants.OTHERPWR2);

    public ModBusNode() { }

    public ModBusNode(int pollRate) {
        setModBusDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.VOLTAGE1, DSElement.make(Util.getIntRandom(130,107))).setReadOnly(true);
        declareDefault(Constants.VOLTAGE2, DSElement.make(Util.getIntRandom(130,107))).setReadOnly(true);
        declareDefault(Constants.VOLTAGE3, DSElement.make(Util.getIntRandom(130,107))).setReadOnly(true);
        declareDefault(Constants.AVGTEMP, DSElement.make(Util.getIntRandom(80,60))).setReadOnly(true);
        declareDefault(Constants.AVGHUMIDITY, DSElement.make(Util.getIntRandom(30,0))).setReadOnly(true);
        declareDefault(Constants.AVGENERGY, DSElement.make(Util.getIntRandom(110,60))).setReadOnly(true);
        declareDefault(Constants.TOTALPWR, DSElement.make(Util.getIntRandom(100,20))).setReadOnly(true);
        declareDefault(Constants.OTHERPWR1, DSElement.make(Util.getIntRandom(100,20))).setReadOnly(true);
        declareDefault(Constants.OTHERPWR2, DSElement.make(Util.getIntRandom(100,20))).setReadOnly(true);
    }

    private void setModBusDataNodeMetrics() {
        put(voltage1.getName(), Util.getIntRandom(130,107));
        put(voltage2.getName(), Util.getIntRandom(130,107));
        put(voltage3.getName(), Util.getIntRandom(130,107));
        put(avgTemp.getName(), Util.getIntRandom(80,60));
        put(avgHumidity.getName(), Util.getIntRandom(30,0));
        put(avgEnergy.getName(), Util.getIntRandom(110,60));
        put(totalPwr.getName(), Util.getIntRandom(100,20));
        put(otherPwr1.getName(), Util.getIntRandom(100,20));
        put(otherPwr2.getName(), Util.getIntRandom(100,20));
    }

    @Override
    public void run() {
        setModBusDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}
