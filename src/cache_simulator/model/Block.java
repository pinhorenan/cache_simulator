package cache_simulator.model;

public class Block {
    private boolean valid;
    private int tag;
    private int size;

    // Construtor

    public Block(int size) {
        this.valid = false;
        this.tag = -1;
        this.size = size;
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

    public int getSize() {
        return size;
    }
}

// PARECE CERTO
