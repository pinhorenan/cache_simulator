package br.edu.ufpel.cache_simulator.interfaces;

import java.util.ArrayList;
import java.util.List;
import br.edu.ufpel.cache_simulator.model.Block;

public class LRUReplacement implements ReplacementPolicy {
    private List<Block> lruList = new ArrayList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        // Verifica se a lista LRU está vazia
        if (lruList.isEmpty()) {
            throw new IllegalStateException("LRU list is empty. No block to replace.");
        }
        // Remove e retorna o bloco menos recentemente usado (primeiro da lista)
        return lruList.remove(0);
    }

    @Override
    public void update(Block block) {
        // Remove o bloco da lista se ele já estiver presente
        lruList.remove(block);
        // Adiciona o bloco ao final da lista (mais recentemente usado)
        lruList.add(block);
    }

    @Override
    public String getName() {
        return "LRU";
    }
}
