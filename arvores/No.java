package arvores;

import java.util.Vector;

public class No {

    private int qtdChaves;
    private Vector<Integer> chaves;
    private Vector<No> filhos;
    private boolean ehFolha;
    private int posX;
    private int posY;
    private int larguraFilhos;

    public No(int qtdChaves) {
        this.chaves = new Vector<>(qtdChaves - 1);
        for (int i = 0; i < qtdChaves - 1; i++) {
            this.chaves.add(null);
        }
        this.filhos = new Vector<>(qtdChaves);
        for (int i = 0; i < qtdChaves; i++) {
            this.filhos.add(null);
        }
        this.ehFolha = true;
        this.qtdChaves = 0;
    }

    public Vector<Integer> getChaves() {
        return chaves;
    }

    public void setChaves(Vector<Integer> chaves) {
        this.chaves = chaves;
    }

    public Vector<No> getFilhos() {
        return filhos;
    }

    public void setFilhos(Vector<No> filhos) {
        this.filhos = filhos;
    }

    public boolean isEhFolha() {
        return ehFolha;
    }

    public void setEhFolha(boolean ehFolha) {
        this.ehFolha = ehFolha;
    }

    public int getQtdChaves() {
        return qtdChaves;
    }

    public void setQtdChaves(int qtdChaves) {
        this.qtdChaves = qtdChaves;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int calcularTamanho() {
        return qtdChaves * 28 + 12;
    }

    public void atualizarCoordenadas(No pai, int x) {
        if (pai == null) {
            if (x == 0) {
                atualizarLarguraFilhos();
            }
            posY = 0;
        } else {
            posY = pai.getPosY() + 30; // Aumentei a constante para 30
        }
        if (!ehFolha) {
            posX = (larguraFilhos / 2) + x;
            int xAcumuladoLocal = x;
            for (int i = 0; i < qtdChaves + 1; i++) {
                filhos.get(i).atualizarCoordenadas(this, xAcumuladoLocal);
                xAcumuladoLocal += filhos.get(i).larguraFilhos + 5; // Aumentei a constante para 5
            }
        } else {
            posX = x;
        }
    }

    public int atualizarLarguraFilhos() {
        larguraFilhos = 0;
        if (!ehFolha) {
            for (int i = 0; i < qtdChaves + 1; i++) {
                larguraFilhos += filhos.get(i).atualizarLarguraFilhos();
            }
        } else {
            larguraFilhos = calcularTamanho() + 5; // Aumentei a constante para 5
        }
        return larguraFilhos;
    }
}