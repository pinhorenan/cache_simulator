package com.cache_simulator;

public class Cache {
    private int[] cacheValidade; // Array para armazenar os bits de validade.
    private int[] cacheTag; // Array para armazenar os bits de tag.
    private final int nsets; // Número de conjuntos. 
    private final int bsize; // Número de blocos.
    private final int assoc; // Número de vias de associatividade (Se assoc=1, é mapeamento direto, se assoc=bsize, é totalmente associativo e o nsets deverá ser 1).
    private final int numeroBitsOffset; // Número de bits para o offset.
    private final int numeroBitsIndex; // Número de bits para o índice.
    private final int tamanhoCache; // Tamanho da cache em bytes, calculado a partir dos valores de nsets, bsize e assoc (net * bsize * assoc).
    private final String politicaSubstituicao; // Algoritmo de substituição (LRU, FIFO, RANDOM). Por padrão será RANDOM, a implementação do LRU e FIFO é opcional.

    // Construtor

    public Cache(int nsets, int bsize, int assoc, String politicaSubstituicao) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.politicaSubstituicao = politicaSubstituicao;
        
        // Definição de valores derivados

        this.tamanhoCache = nsets * bsize * assoc;
        this.numeroBitsOffset = (int) (Math.log(bsize) / Math.log(2)); // log2(bsize)
        this.numeroBitsIndex = (int) (Math.log(nsets) / Math.log(2)); // log2(nsets)

        // Inicialização dos arrays de bits de validade e tag

        this.cacheValidade = new int[nsets * assoc]; // Validade (inicialmente, todas as posições estão vazias, ou seja, são 0).
        this.cacheTag = new int[nsets * assoc]; // Tag (inicialmente, todas as posições estão vazias, ou seja, são 0).
    }

    // Getters

    public int getNsets() {
        return nsets;
    }

    public int getBsize() {
        return bsize;
    }

    public int getAssoc() {
        return assoc;
    }

    public int getTamanhoCache() {
        return tamanhoCache;
    }

    public String getPoliticaSubsituticao() {
        return politicaSubstituicao;
    }

    public int getNumeroBitsOffset() {
        return numeroBitsOffset;
    }

    public int getNumeroBitsIndex() {
        return numeroBitsIndex;
    }

    public int getValidade(int index) {
        return cacheValidade[index];
    }

    public int getTag(int index) {
        return cacheTag[index];
    }

    // Setters

    public void setValido(int index) {
        this.cacheValidade[index] = 1;
    }

    public void setTag(int index, int tag) {
        this.cacheTag[index] = tag;	
    }
}
