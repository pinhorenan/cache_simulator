package br.edu.ufpel.cache_simulator.interfaces;

import java.util.ArrayList;
import java.util.List;
import br.edu.ufpel.cache_simulator.model.Block;

public class LRUReplacement implements ReplacementPolicy {
    private List<Block> lruList = new ArrayList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        // Remove e retorna o bloco menos recentemente usado (primeiro da lista)
        if (lruList.isEmpty()) {
            throw new IllegalStateException("LRU list is empty. No block to replace.");
        }
        return lruList.remove(0);
    }

    @Override
    public void update(Block block) {
        // Se o bloco não estiver na lista, adiciona
        if (!lruList.contains(block)) {
            lruList.add(block); // Adiciona o bloco ao final da lista
        } else {
            // Remove o bloco da lista se já estiver presente
            lruList.remove(block);
            // Adiciona ao final (mais recentemente usado)
            lruList.add(block);
        }
    }

    @Override
    public String getName() {
        return "LRU";
    }
}
