package br.edu.ufpel.cache_simulator.model;

public class Block {
    private boolean valid;
    private int tag;

    // Construtor
    public Block() {
        this.valid = false;
        this.tag = -1; // Tag inválido por padrão
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
}
