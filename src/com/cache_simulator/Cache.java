package com.cache_simulator;

public class Cache {

    // Atributos Primários
    private final int nsets; 
    private final int bsize;
    private final int assoc;
    private final String subPolicy;

    // Atributos Derivados
    private final int offsetLenght;
    private final int indexLenght; 
    private int[] valBits;
    private int[] tagBits;
    private LinkedList<Integer>[] queuesFIFO;
    private LinkedList<Integer>[] arrayLRU;

    // ------------------------------------ Construtor ------------------------------------ //

    public Cache(int nsets, int bsize, int assoc, String subPolicy) {
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.subPolicy = subPolicy;
        
        // Definição de valores derivados

        this.offsetLength = (int) (Math.log(bsize) / Math.log(2)); // log2(bsize)
        this.indexLength = (int) (Math.log(nsets) / Math.log(2)); // log2(nsets)

        // Inicializando a fila para FIFO
        this.queusFIFO = new LinkedList[nsets];
        for (int i = 0; i < nsets; i++) {
            queuesFIFO[i] = new LinkedList<>();
        }

        // Inicializando as listas para LRU
        this.arrayLRU = new ArrayList[nsets];
        for (int i = 0; i < nsets; i++) {
            arrayLRU[i] = new ArrayList<>();
        }

        // Inicialização dos arrays de bits de validade e tag
        this.valBits = new int[nsets * assoc];
        this.tagBits = new int[nsets * assoc];
    }
   

    // ------------------------------------ Métodos ------------------------------------ //

    // -> Políticas de Substituição

    public void applyFIFO(int setIndex, int tag) {
        // Se o conjunto já está cheio, remover o bloco mais antigo (primeiro da fila)
        if (queuesFIFO[setIndex].size() == assoc) {
            int blocoRemover = queusFIFO[setIndex].poll();  // Remove o bloco mais antigo
            // Aqui você precisa remover o tag correspondente no cache (atualizar arrays de validade e tag)
            // Exemplo: cacheTagArray[setIndex][...] = -1;
        }
    
        // Adicionar o novo bloco (tag) ao final da fila
        queuesFIFO[setIndex].add(tag);
    }

    public void applyLRU(int setIndex, int tag) {
        // Se o conjunto já está cheio, remover o bloco menos recentemente utilizado (primeiro da lista)
        if (listasLRU[setIndex].size() == assoc) {
            int blocoRemover = listasLRU[setIndex].remove(0);  // Remove o primeiro (menos recentemente usado)
            // Aqui você precisa remover o tag correspondente no cache (atualizar arrays de validade e tag)
            // Exemplo: cacheTagArray[setIndex][...] = -1;
        }
    
        // Adicionar o novo bloco (tag) ao final da lista
        listasLRU[setIndex].add(tag);
    }

    public void refreshLRU(int setIndex, int tag) {
        // Remove o bloco acessado da lista
        arrayLRU[setIndex].remove((Integer) tag);  // Casting para garantir que é o objeto "tag"
        
        // Adiciona o bloco ao final da lista (agora é o mais recentemente usado)
        arrayLRU[setIndex].add(tag);
    }
      

    // -> Getters

    public int getNsets() {
        return nsets;
    }

    public int getBsize() {
        return bsize;
    }

    public int getAssoc() {
        return assoc;
    }

    public String getPolicy() {
        return subPolicy;
    }

    public int getOffset() {
        return offsetLenght;
    }

    public int getIndex() {
        return indexLenght;
    }

    public int getTag(int index) {
        return tagBits[index];
    }

    public int checkValid(int index) {
    return valBits[index];
    }

    // Setters

    public void setValid(int index) {
        this.valBits[index] = 1;
    }

    public void setTag(int index, int tag) {
        this.tagBits[index] = tag;	
    }
}

    // Getters Derivados

    // TODO
    public int getCacheSize() {
        return null;
    }