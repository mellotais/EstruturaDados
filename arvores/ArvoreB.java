package arvores;

public class ArvoreB {
    private static final int ORDEM = 3;
    private No raiz;

    private class No {
        int numChaves = 0;
        Chave[] chaves = new Chave[ORDEM - 1];
        No[] filhos = new No[ORDEM];
        boolean folha = true;

        
        private class Chave {
            int chave;
        }
    }
    
    public ArvoreB() {
        raiz = new No();
    }
    
    public void insere(int chave) {
        No raizAntiga = raiz;
        if (raizAntiga.numChaves == ORDEM - 1) {
            No novaRaiz = new No();
            novaRaiz.filhos[0] = raizAntiga;
            novaRaiz.folha = false;
            raiz = novaRaiz;
            divideNoFilho(novaRaiz, 1, raizAntiga);
            insereNaoCompleto(novaRaiz, chave);
        } else {
        	insereNaoCompleto(raizAntiga, chave);
        }
    }

    private void divideNoFilho(No pai, int i, No filho) {
        No novoNo = new No();
        novoNo.folha = filho.folha;
        novoNo.numChaves = ORDEM / 2 - 1;

        for (int j = 0; j < ORDEM / 2 - 1; j++) {
            novoNo.chaves[j] = filho.chaves[j + ORDEM / 2];
        }

        if (!filho.folha) {
            for (int j = 0; j < ORDEM / 2; j++) {
                novoNo.filhos[j] = filho.filhos[j + ORDEM / 2];
            }
        }

        filho.numChaves = ORDEM / 2 - 1;

        for (int j = pai.numChaves; j >= i; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }

        pai.filhos[i] = novoNo;

        for (int j = pai.numChaves - 1; j >= i - 1; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        pai.chaves[i - 1] = filho.chaves[ORDEM / 2 - 1];
        pai.numChaves++;
    }

    private void insereNaoCompleto(No no, int chave) {
        int i = no.numChaves - 1;

        if (no.folha) {
            while (i >= 0 && chave < no.chaves[i].chave) {
                no.chaves[i + 1] = no.chaves[i];
                i--;
            }

            no.chaves[i + 1] = new No().new Chave();
            no.chaves[i + 1].chave = chave;
            no.numChaves++;
       
        } else {
                while (i >= 0 && chave < no.chaves[i].chave) {
                    i--;
                }
                i++;

                if (no.filhos[i].numChaves == ORDEM - 1) {
                    divideNoFilho(no, i + 1, no.filhos[i]);
                    if (chave > no.chaves[i].chave) {
                        i++;
                    }
                }
                insereNaoCompleto(no.filhos[i], chave);
            }
        }
    
    public void remove(int chave) {
        remove(raiz, chave);

        if (raiz.numChaves == 0) {
            if (raiz.folha) {
                raiz = null;
            } else {
                raiz = raiz.filhos[0];
            }
        }
    }
     // Método de remoção
    private void remove(No no, int chave) {
        int i = 0;
        while (i < no.numChaves && chave > no.chaves[i].chave) {
            i++;
        }

        if (no.folha) {
            for (int j = i; j < no.numChaves - 1; j++) {
                no.chaves[j] = no.chaves[j + 1];
            }
            no.numChaves--;
        } else {
            No y = null;
            No z = null;

            if (i < no.filhos.length) {
                y = no.filhos[i];
            }

            if (i + 1 < no.filhos.length) {
                z = no.filhos[i + 1];
            }

            if (y != null && y.numChaves >= ORDEM / 2) {
                no.chaves[i] = y.chaves[y.numChaves - 1];
                remove(y, y.chaves[y.numChaves - 1].chave);
            } else if (z != null && z.numChaves >= ORDEM / 2) {
                no.chaves[i] = z.chaves[0];
                remove(z, z.chaves[0].chave);
            } else {
                y.chaves[y.numChaves] = no.chaves[i];
                if (z != null) {
                    for (int j = 0; j < z.numChaves; j++) {
                        y.chaves[y.numChaves + j + 1] = z.chaves[j];
                        y.filhos[y.numChaves + j + 1] = z.filhos[j];
                    }
                }
                y.filhos[y.numChaves + z.numChaves + 1] = z.filhos[z.numChaves];
                y.numChaves += z.numChaves + 1;

                for (int j = i; j < no.numChaves - 1; j++) {
                    no.chaves[j] = no.chaves[j + 1];
                    no.filhos[j + 1] = no.filhos[j + 2];
                }
                no.numChaves--;

                remove(y, chave);
            }
        }
    }
     // Método de busca
        public boolean busca(int chave) {
            return busca(raiz, chave);
        }

        private boolean busca(No no, int chave) {
            int i = 0;
            while (i < no.numChaves && chave > no.chaves[i].chave) {
                i++;
            }
            if (i < no.numChaves && chave == no.chaves[i].chave) {
                return true;
            } else if (no.folha) {
                return false;
            } else {
                return busca(no.filhos[i], chave);
            }
        }

     // Método para retornar a altura da árvore B
        public int altura() {
            return altura(raiz);
        }

        private int altura(No no) {
            if (no == null) {
                return 0;
            }
            int alturaMaxima = 0;
            for (int i = 0; i < no.numChaves; i++) {
                int alturaFilho = altura(no.filhos[i]);
                if (alturaFilho > alturaMaxima) {
                    alturaMaxima = alturaFilho;
                }
            }
            return alturaMaxima + 1;
        }

        // Método para verificar se a árvore B está vazia
        public boolean estaVazia() {
            return raiz == null;
        }

        // Método para imprimir a árvore B em ordem
        public void imprimeEmOrdem() {
            imprimeEmOrdem(raiz);
        }

        private void imprimeEmOrdem(No no) {
            if (no != null) {
                int i;
                for (i = 0; i < no.numChaves; i++) {
                    imprimeEmOrdem(no.filhos[i]);
                    System.out.print(no.chaves[i].chave + " ");
                }
                imprimeEmOrdem(no.filhos[i]);
            }
        }
        
     }