package br.edu.ufpel.cache_simulator.model;

public class Block {
    private boolean valid;
    private int tag;
    private int bsize;

    // Construtor

    public Block(int bsize) {
        this.valid = false;
        this.tag = -1;
        this.bsize = bsize;
    }

    // Getters e Setters

    public boolean isValid() {
        return valid;
    }

    public void setValid() {
        this.valid = true;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getBlockSize() {
        return bsize;
    }
}

// PARECE CERTO
