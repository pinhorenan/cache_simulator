package br.edu.ufpel.cache_simulator.policies;

import java.util.ArrayList;
import java.util.List;
import br.edu.ufpel.cache_simulator.model.Block;

public class LRUReplacement implements ReplacementPolicy {
    private List<Block> lruList = new ArrayList<>();

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        // Inicializa a lista LRU se estiver vazia
        if (lruList.isEmpty()) {
            lruList.addAll(blocks);  // Preenche a lista com todos os blocos
        }
        // Remove e retorna o bloco menos recentemente usado (primeiro da lista)
        return lruList.remove(0);
    }

    @Override
    public void update(Block block) {
        // Remove o bloco da lista se já estiver presente (foi acessado novamente)
        lruList.remove(block);
        // Reinsere o bloco no final da lista (mais recentemente usado)
        lruList.add(block);
    }
}
