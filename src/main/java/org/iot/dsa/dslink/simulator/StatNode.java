package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSElement;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSInt;
import org.iot.dsa.node.DSNode;
import org.iot.dsa.node.DSString;

public class StatNode extends DSNode implements Runnable {

    private final DSInfo currentTemp = getInfo(Constants.CURRENTTEMP);
    private final DSInfo humidity = getInfo(Constants.HUMIDITY);

    public StatNode() { }

    public StatNode(int pollRate) {
        setStatDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.MODEL, DSString.valueOf("VT8600")).setReadOnly(true);
        declareDefault(Constants.MODE, Util.getStatModes());
        declareDefault(Constants.SETPOINT, DSInt.valueOf(65));
        declareDefault(Constants.CURRENTTEMP, DSElement.make(Util.getFloatRandom(99.00, 40.00))).setReadOnly(true);
        declareDefault(Constants.HUMIDITY, DSElement.make(Util.getIntRandom(100, 0))).setReadOnly(true);
    }

    private void setStatDataNodeMetrics() {
        put(currentTemp.getName(), Util.getFloatRandom(99.00, 40.00));
        put(humidity.getName(), Util.getIntRandom(100, 0));
    }

    @Override
    public void run() {
        setStatDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }

    @Override
    protected void onChildChanged(DSInfo info) {
        if((info.getName() == Constants.SETPOINT) &&
                (Util.checkIntRange(Integer.parseInt(info.getValue().toString()), 90, 40))) {
            put(info.getName(), DSInt.valueOf(65));
        }
    }
}
