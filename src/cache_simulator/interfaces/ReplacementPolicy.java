package cache_simulator.interfaces;

import java.util.List;

import cache_simulator.model.Block;

public interface ReplacementPolicy {
    Block selectBlockToReplace(List<Block> blocks);
    void update(Block block);
    String getName();
}
