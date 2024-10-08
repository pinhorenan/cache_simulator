package br.edu.ufpel.cachesimulator.policies;

import java.util.List;

import br.edu.ufpel.cachesimulator.model.Block;

public interface ReplacementPolicy {
    Block selectBlockToReplace(List<Block> blocks);
    void update(Block block);
}
