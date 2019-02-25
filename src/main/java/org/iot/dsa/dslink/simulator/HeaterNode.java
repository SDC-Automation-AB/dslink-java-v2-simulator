package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSElement;
import org.iot.dsa.node.DSFloat;
import org.iot.dsa.node.DSIObject;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSNode;

public class HeaterNode extends DSNode implements Runnable {

    private final DSInfo currentTemp = getInfo(Constants.CURRENTTEMP);
    private final DSInfo wattage = getInfo(Constants.WATTAGE);

    public HeaterNode() { }

    public HeaterNode(int pollRate) {
        setHeaterDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.BOOLEAN, DSBool.valueOf(false));
        declareDefault(Constants.TARGETTEMP, DSFloat.valueOf("72.50"));
        declareDefault(Constants.CURRENTTEMP, DSElement.make(Util.getFloatRandom(85.00, 60.00))).setReadOnly(true);
        declareDefault(Constants.WATTAGE, DSElement.make(Util.getIntRandom(4000,0))).setReadOnly(true);
    }

    private void setHeaterDataNodeMetrics() {
        put(currentTemp.getName(), Util.getFloatRandom(85.00, 60.00));
        put(wattage.getName(), Util.getIntRandom(4000,0));
    }

    @Override
    public void run() {
        setHeaterDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    @Override
    protected void onChildChanged(DSInfo info) {
        if((info.getName() == Constants.TARGETTEMP) &&
          (Util.checkFloatRange(Double.parseDouble(info.getValue().toString()), 85.00, 60.00))) {
            put(info.getName(), DSFloat.valueOf("72.50"));
        }
    }
}