# dslink-java-v2-Simulator

* Java - version 1.8 and up.
* [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)


## Overview

Simulator DSLInk to provide a data tree for use in sample applications so dynamic data can be
simulated for visualization within DGLux. This project would consist of a self-contained DSLink
that generates random data with data labels within fixed ranges and exposes these values via
DSA to DGLux. Values would mostly be read only, however several would also allow a nonparsistant
SET capability to allow users using the DSlink to experiment with Setting values in a
DSLink from DDGLux. (Noted as Mock settable, Default values are set. If on setting value which is
not in provided range, then it will set to default value)

If you are not familiar with DSA and links, an overview can be found at
[here](http://iot-dsa.org/get-started/how-dsa-works).

This link was built using the DSLink Java SDK which can be found
[here](https://github.com/iot-dsa-v2/sdk-dslink-java-v2).


## Link Architecture

This section outlines the hierarchy of nodes defined by this link.

- _MainNode_ - Root node of the link has an action to update the poll rate value [Default Value : 10 Sec]). (PollRate in Main node applicable for default Nodes created. Each custom node will be having individual Poll Rate.)

  - _Custom Nodes_ : Root Node of every custom node created will have action to update the poll rate value for that Node.[Default Value : 10 Sec]
    - With "Add Custom Node" option, create the custom Node. After node gets created, Can create node tree structure or create value which displays in metric panel.
    - In the Value field below options are provided
      - _String_ : Able to add string data point in metric panel. If ReadWrite flag is set to true only then added string value is mutable.
      - _Number_ : Able to add number data point in metric panel.
        - In the value area, if only number is specified then just it creates number data point.
        - To set the boundaries to number, in the value area specficy Min:Max(Ex 20:40). If ReadWrite flag is set to true, random values between the provided range will be generated based on poll rate and if flag is set to false, then user will be allowed only set the values in provided range while creating the data point.
      - _Bool_ : Able to add boolen flag in metric panel.
        
  - _Default Nodes_ :
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
          - Target Temperature (Float: Range value from 60.00-85.00) (Default Value : 72.5) (Mock settable)
          - Wattage (Decimal Range Value from 0-4000)
          - Units: String: Watts
      - _Heater 2_ :
          - Boolean (On/Off) (Mock settable)
          - Current Temperature (Float: Range value from 60.00-85.00)
          - Target Temperature (Float: Range value from 60.00-85.00) (Default Value : 72.5) (Mock settable)
          - Wattage (Decimal Range Value from 0-4000)
          - Units: String: Watts
       - _Heater 3_ :
          - Boolean (On/Off) (Mock settable)
          - Current Temperature (Float: Range value from 60.00-85.00)
          - Target Temperature (Float: Range value from 60.00-85.00) (Default Value : 72.5) (Mock settable)
          - Wattage (Decimal Range Value from 0-4000)
          - Units: String: Watts
      - _Heater 4_ :
          - Boolean (On/Off) (Mock settable)
          - Current Temperature (Float: Range value from 60.00-85.00)
          - Target Temperature (Float: Range value from 60.00-85.00) (Default Value : 72.5) (Mock settable)
          - Wattage (Decimal Range Value from 0-4000)
          - Units: String: Watts
    - _Infrastructure_ - Infrastructure.
      - _BacNet_-
          - _Stat 1_ :
              - Mode (enum: Heat, Cool, Fan, Off) (Mock settable)
              - Current Temp (Float: Range value from 40.00-99.00)
              - Set Point (Decimal: Range value from 40-90) (Default Value : 65) (Mock settable)
              - Humidity (Percentage: 0-100)
              - Model: VT8600
          - _Stat 2_ :
              - Mode (enum: Heat, Cool, Fan, Off) (Mock settable)
              - Current Temp (Float: Range value from 40.00-99.00)
              - Set Point (Decimal: Range value from 40-90) (Default Value : 65) (Mock settable)
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

