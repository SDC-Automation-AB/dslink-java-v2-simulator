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
  - _Water System_ - Water System.
  - _Infrastructure_ - Infrastructure.


## Acknowledgements

SDK-DSLINK-JAVA

This software contains unmodified binary redistributions of 
[sdk-dslink-java-v2](https://github.com/iot-dsa-v2/sdk-dslink-java-v2), which is licensed 
and available under the Apache License 2.0. An original copy of the license agreement can be found 
at https://github.com/iot-dsa-v2/sdk-dslink-java-v2/blob/master/LICENSE

