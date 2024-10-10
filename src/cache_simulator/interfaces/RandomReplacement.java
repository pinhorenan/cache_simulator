package br.edu.ufpel.cache_simulator.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import br.edu.ufpel.cache_simulator.model.Block;

public class RandomReplacement implements ReplacementPolicy {
    private Random random;

    public RandomReplacement() {
        this.random = new Random();
    }   

    @Override
    public void update(Block block) {
        // A política RANDOM não precisa de atualização específica ao acessar blocos
    }

    @Override
    public Block selectBlockToReplace(List<Block> blocks) {
        // Filtra blocos inválidos
        List<Block> invalidBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (!block.isValid()) {
                invalidBlocks.add(block);
            }
        }

        // Se houver blocos inválidos, escolha aleatoriamente um deles
        if (!invalidBlocks.isEmpty()) {
            int randomIndex = random.nextInt(invalidBlocks.size());
            return invalidBlocks.get(randomIndex);
        } else {
            // Se não houver blocos inválidos, escolha aleatoriamente um bloco válido
            int randomIndex = random.nextInt(blocks.size());
            return blocks.get(randomIndex);
        }
    }

    @Override
    public String getName() {
        return "RANDOM";
    }

}
