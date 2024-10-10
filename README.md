# cache_simulator
 Simulador de Cache em Java, desenvolvido como tarefa avaliada na disciplina de Arquitetura e Organização de Computadores II.

## Instruções de Compilação e Utilização

### Linux
- Execute `$ ./compile.sh` dentro do diretório do código fonte
- Após a compilação, o arquivo executável poderá ser encontrado dentro do diretório "linux". Execute com ```$ ./cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> arquivo_de_entrada```

### Windows
- WIP

## Parâmetros de execução para a simulação?

### < nsets >: Define o número de conjuntos da cache, deve ser um inteiro positivo.
### < bsize >: Define o tamanho do bloco, deve ser um inteiro positivo.
### < assoc >: Define o nível de associatividade da cache, deve ser um inteiro positivo.
### < substituição >: Define a política de substituição utilizada para substituir um bloco quando ocorre um miss. Os valores possíveis são: "R" para Random, "L" para LRU e "F" para FIFO.
### < flag_saída >: Define a verbosidade da saída. Valor booleano, com "0" retornando uma saída com formatação livre e "1" retornando uma formatação padronizada e concisa.
### arquivo_de_entrada: O arquivo de entrada para a simulação. Deve ser um arquivo binário no formato Big Endian.

## Problemas conhecidos:

 As políticas de substituição FIFO e LRU geram os mesmos resultados, aparentemente a LRU está se comportando como FIFO.
