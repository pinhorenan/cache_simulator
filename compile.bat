@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

REM Define o diretório de saída para os arquivos .class
set OUTPUT_DIR=out

REM Compila todos os arquivos Java no diretório src
echo Compilando arquivos Java...
if not exist "%OUTPUT_DIR%" (
    mkdir "%OUTPUT_DIR%"
)

REM Lista de arquivos Java
set SOURCE_FILES=
for %%f in (src\cache_simulator\*.java) do (
    set SOURCE_FILES=!SOURCE_FILES! %%f
)
for %%f in (src\cache_simulator\*\*.java) do (
    set SOURCE_FILES=!SOURCE_FILES! %%f
)

javac -d "%OUTPUT_DIR%" %SOURCE_FILES%

REM Verifica se a compilação foi bem-sucedida
if errorlevel 1 (
    echo Erro na compilacao.
    exit /b 1
)

REM Cria o arquivo JAR, se a compilação for bem-sucedida
cd %OUTPUT_DIR%
jar cvfm ../Simulator.jar ../Simulator.mf *.class
cd ..

REM Cria um script executável para Linux
echo #!/usr/bin/env java -jar > linux\cache_simulator
type Simulator.jar >> linux\cache_simulator
chmod +x linux\cache_simulator

REM Limpeza: Remove o JAR temporário após criar o executável
del Simulator.jar

echo Compilacao e empacotamento concluídos.
ENDLOCAL
