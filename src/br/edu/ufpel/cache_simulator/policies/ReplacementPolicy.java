package br.edu.ufpel.cache_simulator.policies;

import java.util.List;

import br.edu.ufpel.cache_simulator.model.Block;

public interface ReplacementPolicy {
    Block selectBlockToReplace(List<Block> blocks);
    void update(Block block);
    String getName();
}
