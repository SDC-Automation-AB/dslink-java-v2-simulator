package org.iot.dsa.dslink.simulator;

import org.iot.dsa.node.DSFlexEnum;
import org.iot.dsa.node.DSIEnum;
import org.iot.dsa.node.DSList;

import java.text.DecimalFormat;

public class Util {

    public static long sample(){
        return Math.round(Math.random()*10);
    }

    public static int getIntRandom(int upper, int lower) {
        int rVal = (int) (Math.random() * (upper - lower)) + lower;
        return rVal;
    }

    public static Float getFloatRandom(double upper, double lower) {
        double rVal = (Math.random() * (upper - lower)) + lower;
        DecimalFormat twoDForm = new DecimalFormat("#.00");
        return Float.parseFloat(twoDForm.format(rVal));
    }

    public static boolean checkIntRange(int val, int upper, int lower) {
        return !(val > lower & val < upper);
    }

    public static boolean checkFloatRange(double val, double upper, double lower) {
        return !(val > lower & val < upper);
    }

    public static DSFlexEnum getStatModes(){
        DSList selectList = new DSList().add("HEAT")
                .add("COOL").add("FAN")
                .add("OFF");
        DSFlexEnum selectEnum = DSFlexEnum.valueOf("OFF",selectList);
        return selectEnum;
    }
}
