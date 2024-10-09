package br.edu.ufpel.cache_simulator.policies;

import java.util.List;
import java.util.Random;
import br.edu.ufpel.cache_simulator.model.Block;

public class RandomPolicy implements ReplacementPolicy {
    private Random random;

    public RandomPolicy() {
        this.random = new Random();
    }   

    @Override
    public void update(Block block) {
        // A política RANDOM não precisa de atualização específica ao acessar blocos
    }

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        int randomIndex = random.nextInt(blocks.size());
        return blocks.get(randomIndex);
    }
}
