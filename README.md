# dslink-java-v2-Simulator

* Java - version 1.8 and up.
* [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)


## Overview

Simulator DSLInk to provide a data tree for use in sample applications so dynamic data can be
simulated for visualization within DGLux. This project would consist of a self-contained DSLink
that generates random data with data labels within fixed ranges and exposes these values via
DSA to DGLux. Values would mostly be read only, however several would also allow a nonparsistant
SET capability to allow users using the DSlink to experiment with Setting values in a
DSLink from DDGLux. (Noted as Mock settable)

If you are not familiar with DSA and links, an overview can be found at
[here](http://iot-dsa.org/get-started/how-dsa-works).

This link was built using the DSLink Java SDK which can be found
[here](https://github.com/iot-dsa-v2/sdk-dslink-java-v2).


## Link Architecture

This section outlines the hierarchy of nodes defined by this link.

- _MainNode_ - The root node of the link.
  - _Propellers_ - Propellers.
    - _Pump 1_ :
        - Boolean (On/Off) (Mock settable)
        - Speed (Float: Range value from 1.00-10.00)
     - _Pump 2_ :
        - Boolean (On/Off) (Mock settable)
        - Speed (Float: Range value from 1.00-10.00)
     - _Pump 3_ :
        - Boolean (On/Off) (Mock settable)
        - Speed (Float: Range value from 1.00-10.00)
     - _Pump 4_ :
        - Boolean (On/Off) (Mock settable)
        - Speed (Float: Range value from 1.00-10.00)
  - _Water System_ - Water System.
     - _Heater 1_ :
        - Boolean (On/Off) (Mock settable)
        - Current Temperature (Float: Range value from 60.00-85.00)
        - Target Temperature (Float: Range value from 60.00-85.00) (Mock settable)
        - Wattage (Decimal Range Value from 0-4000)
        - Units: String: Watts
    - _Heater 2_ :
        - Boolean (On/Off) (Mock settable)
        - Current Temperature (Float: Range value from 60.00-85.00)
        - Target Temperature (Float: Range value from 60.00-85.00) (Mock settable)
        - Wattage (Decimal Range Value from 0-4000)
        - Units: String: Watts
     - _Heater 3_ :
        - Boolean (On/Off) (Mock settable)
        - Current Temperature (Float: Range value from 60.00-85.00)
        - Target Temperature (Float: Range value from 60.00-85.00) (Mock settable)
        - Wattage (Decimal Range Value from 0-4000)
        - Units: String: Watts
    - _Heater 4_ :
        - Boolean (On/Off) (Mock settable)
        - Current Temperature (Float: Range value from 60.00-85.00)
        - Target Temperature (Float: Range value from 60.00-85.00) (Mock settable)
        - Wattage (Decimal Range Value from 0-4000)
        - Units: String: Watts
  - _Infrastructure_ - Infrastructure.
    - _BacNet_-
        - _Stat 1_ :
            - Mode (enum: Heat, Cool, Fan, Off) (Mock settable)
            - Current Temp (Float: Range value from 40.00-99.00)
            - Set Point (Decimal: Range value from 40-90) (Mock settable)
            - Humidity (Percentage: 0-100)
            - Model: VT8600
        - _Stat 2_ :
            - Mode (enum: Heat, Cool, Fan, Off) (Mock settable)
            - Current Temp (Float: Range value from 40.00-99.00)
            - Set Point (Decimal: Range value from 40-90) (Mock settable)
            - Humidity (Percentage: 0-100)
            - Model: VT8600
        - _RTU 1_ :
            - Cooling (Boolean) (Mock settable)
            - Heating (Boolean) (Mock settable)
            - SAT: (Float: Range value from 40.00-99.00)
        - _RTU 2_ :
            - Cooling (Boolean) (Mock settable)
            - Heating (Boolean) (Mock settable)
            - SAT: (Float: Range value from 40.00-99.00)
    - _ModBus_-
        - Voltage 1 (Float: Range between 107VAC-130VAC)
        - Voltage 2 (Float: Range between 107VAC-130VAC)
        - Voltage 3 (Float: Range between 107VAC-130VAC)
        - Average Temperature (Decimal Range between 60-80F)
        - Average Humidity (Percentage range between 0-30)
        - Average Energy Consumption (Float Range between 60-110)
        - Total Power (Float Range between 20-100KW)
        - Other Power 1 (Float Range between 20-100KW)
        - Other Power 2 (Float Range between 20-100KW)

## Acknowledgements

SDK-DSLINK-JAVA

This software contains unmodified binary redistributions of 
[sdk-dslink-java-v2](https://github.com/iot-dsa-v2/sdk-dslink-java-v2), which is licensed 
and available under the Apache License 2.0. An original copy of the license agreement can be found 
at https://github.com/iot-dsa-v2/sdk-dslink-java-v2/blob/master/LICENSE

