package org.iot.dsa.dslink.simulator;

import java.util.Iterator;
import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSElement;
import org.iot.dsa.node.DSFloat;
import org.iot.dsa.node.DSIObject;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSMap;
import org.iot.dsa.node.DSMetadata;
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

        if((info.getName() == Constants.CURRENTTEMP)) {
            System.out.println("Current temp changed - Print metadata");
            System.out.println("isNode: " + currentTemp.isNode());
            System.out.println("name: " + currentTemp.getName());
            System.out.println("value: " + currentTemp.getValue().toString());
            System.out.println("equalsdefault: " + currentTemp.equalsDefaultMetadata());

            final DSMetadata metadata = currentTemp.getMetadata();
            // Thread.dumpStack();
            info.getMetadata().getMap().forEach(entry -> {
                System.out.println("NORMAL THE KEY: " + entry.getKey());
                System.out.println("NORMAL THE VALUE: " + entry.getValue());
            });

            final DSMap entries = new DSMap();
            currentTemp.getMetadata(entries);
            entries.forEach(entry -> {
                System.out.println("THE KEY: " + entry.getKey());
                System.out.println("THE VALUE: " + entry.getValue());
            });

            /*do {
                final boolean hasNext = children.hasNext();
                System.out.println("HAS NEXT IS " + hasNext + " node get name " + info.getName());
                if(hasNext) {
                    System.out.println("NEXT VALUE" + children.next());
                }
            } while(children.hasNext());*/
        }
    }
}