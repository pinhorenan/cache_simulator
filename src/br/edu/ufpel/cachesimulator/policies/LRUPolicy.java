package br.edu.ufpel.cachesimulator.policies;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufpel.cachesimulator.model.Block;

public class LRUPolicy implements ReplacementPolicy {
    private List<Block> lruList = new ArrayList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        return lruList.remove(0);  // Remove e retorna o bloco menos recentemente usado (primeiro da lista)
    }

    @Override
    public void update(Block block) {
        lruList.remove(block);  // Remove o bloco da posição atual
        lruList.add(block);     // Reinsere o bloco no final da lista (mais recentemente usado)
    }
}
