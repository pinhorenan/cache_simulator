#!/bin/sh

# Compila todos os arquivos Java e coloca os arquivos .class no diretório out/
javac -d out/ src/cache_simulator/*/*.java src/cache_simulator/*.java

# Muda para o diretório de saída
cd out || exit 1

# Cria o arquivo JAR, especificando o manifest e incluindo as classes compiladas
jar cvfm ../Simulator.jar ../Simulator.mf src/cache_simulator/*/*.class src/cache_simulator/*.class

# Retorna ao diretório anterior
cd .. || exit 1

# Cria um executável para Linux que pode ser usado para rodar o JAR
echo '#!/usr/bin/env java -jar' > linux/cache_simulator
cat Simulator.jar >> linux/cache_simulator
chmod +x linux/cache_simulator

# Limpeza opcional: remove o JAR temporário após criar o executável
rm -f Simulator.jar 

# Opcional: remove o diretório temporário de compilação, se não for mais necessário
rm -rf out/
