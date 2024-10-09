package br.edu.ufpel.cache_simulator.model;

import java.util.List;
import java.util.ArrayList;

import br.edu.ufpel.cache_simulator.policies.ReplacementPolicy;

public class Set {
    private List<Block> blocks;
    private ReplacementPolicy replacementPolicy;

    // Construtor
    public Set(int bsize, int assoc, ReplacementPolicy policy) {
        this.replacementPolicy = policy;

        // Inicializa o array de blocos com o tamanho da associatividade e preenche com blocos vazios de tamanho bsize
        blocks = new ArrayList<>(assoc);
        for (int i = 0; i < assoc; i++) {
            blocks.add(new Block(bsize));
        }
    }

    public Object getReplacementPolicy() {
        return replacementPolicy;
    }

    // getBlock() retorna o bloco no índice passado
    public Block getBlock(int index) {
        return blocks.get(index);
    }

    // checkTag() verifica se a tag do bloco é igual à tag passada
    public boolean checkTag(Block block, int tag) {
        if (block.isValid() && block.getTag() == tag) {
            return true;
        } else return false;
    }
}
