package br.edu.ufpel.cachesimulator.policies;

import br.edu.ufpel.cachesimulator.model.Block;

public interface ReplacementPolicy {
    Block selectBlockToReplace(List<Block> blocks);
    void update(Block block);
}
