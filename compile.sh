#!/bin/sh

javac -d out/ src/br/edu/ufpel/cache_simulator/*/*.java src/br/edu/ufpel/cache_simulator/*.java
cd out
jar cvfm ../Simulator.jar Simulator.mf br/edu/ufpel/cache_simulator/*/*.class br/edu/ufpel/cache_simulator/*.class
cd ..
echo '#!/usr/bin/java -jar' > linux/cache_simulator
cat Simulator.jar >> linux/cache_simulator
chmod +x linux/cache_simulator
rm Simulator.jar # Só pra não deixar a "sujeira" da compilação