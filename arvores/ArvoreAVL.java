package arvores;

import java.util.Stack;

public class ArvoreAVL {
    private class Nodo {
        private int dado, altd, alte, quant;
        private Nodo dir, esq;

        public Nodo(int dado) {
            this.dado = dado;
            dir = esq = null;
            altd = alte = 0;
            quant = 1;
        }
    }

    Nodo raiz;

    public ArvoreAVL() {
        raiz = null;
    }

    public void insere(int dado) {
        Nodo novoNodo = new Nodo(dado);

        if (raiz == null) {
            raiz = novoNodo;
            return;
        }

        Stack<Nodo> pilha = new Stack<>();
        Nodo atual = raiz;

        while (true) {
            pilha.push(atual);

            if (dado < atual.dado) {
                if (atual.esq == null) {
                    atual.esq = novoNodo;
                    break;
                }
                atual = atual.esq;
            } else if (dado > atual.dado) {
                if (atual.dir == null) {
                    atual.dir = novoNodo;
                    break;
                }
                atual = atual.dir;
            } else {
                atual.quant++;
                break;
            }
        }

        // Reequilibrar a árvore a partir do novo nodo até a raiz
        while (!pilha.isEmpty()) {
            atual = pilha.pop();
            atual.alte = 1 + Math.max(getAltura(atual.esq), getAltura(atual.dir));
            atual.altd = 1 + Math.max(getAltura(atual.dir), getAltura(atual.esq));

            raiz = balanceamento(atual);

            if (!pilha.isEmpty()) {
                Nodo pai = pilha.peek();
                if (dado < pai.dado) {
                    pai.esq = raiz;
                } else {
                    pai.dir = raiz;
                }
            }
        }
    }

    private Nodo balanceamento(Nodo raiz) {
        int balance = getAltura(raiz.esq) - getAltura(raiz.dir);
        if (balance > 1) {
            if (getAltura(raiz.esq.esq) >= getAltura(raiz.esq.dir)) {
                raiz = rotacaoDireita(raiz);
            } else {
                raiz.esq = rotacaoEsquerda(raiz.esq);
                raiz = rotacaoDireita(raiz);
            }
        } else if (balance < -1) {
            if (getAltura(raiz.dir.dir) >= getAltura(raiz.dir.esq)) {
                raiz = rotacaoEsquerda(raiz);
            } else {
                raiz.dir = rotacaoDireita(raiz.dir);
                raiz = rotacaoEsquerda(raiz);
            }
        }
        return raiz;
    }

    // Funções para rotações
    private Nodo rotacaoEsquerda(Nodo raiz) {
        Nodo aux1, aux2;
        aux1 = raiz.dir;
        aux2 = aux1.esq;
        raiz.dir = aux2;
        aux1.esq = raiz;
        raiz.altd = 1 + Math.max(getAltura(raiz.dir), getAltura(raiz.esq));
        aux1.alte = 1 + Math.max(getAltura(aux1.dir), getAltura(aux1.esq));
        return aux1;
    }

    private Nodo rotacaoDireita(Nodo raiz) {
        Nodo aux1, aux2;
        aux1 = raiz.esq;
        aux2 = aux1.dir;
        raiz.esq = aux2;
        aux1.dir = raiz;
        raiz.alte = 1 + Math.max(getAltura(raiz.esq), getAltura(raiz.dir));
        aux1.altd = 1 + Math.max(getAltura(aux1.esq), getAltura(aux1.dir));
        return aux1;
    }
    
    
    public void verArvore() {
        verAVL(raiz, 0);
    }

    private void verAVL(Nodo nodo, int nivel) {
        if (nodo != null) {
            StringBuilder espacos = new StringBuilder();
            for (int i = 0; i < nivel; i++) {
                espacos.append("  "); // espaços para mostrar hierarquia
            }
            System.out.println(espacos.toString() + "Valor: " + nodo.dado + " (Altura: " + getAltura(nodo) + ", Quantidade: " + nodo.quant + ")");
            verAVL(nodo.esq, nivel + 1); // ver esquerda
            verAVL(nodo.dir, nivel + 1); // ver direita
        }
    }

    private int getAltura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        return Math.max(getAltura(nodo.esq), getAltura(nodo.dir)) + 1;
    }
	private int altura(Nodo nodo) {
	    if (nodo == null) {
	        return -1; // Nó nulo tem altura -1
	    }
	    int alturaEsq = altura(nodo.esq);
	    int alturaDir = altura(nodo.dir);
	    return 1 + Math.max(alturaEsq, alturaDir);
	}


	public void mostrarEmOrdem() {
		mostrandoOrdenado(raiz);
	}

	public void mostrandoOrdenado(Nodo raiz) {
		if (raiz != null) {
			mostrandoOrdenado(raiz.esq);
			//mostra a quantidade aq
			System.out.println(raiz.dado + " (Quantidade: "+ raiz.quant +")");
			mostrandoOrdenado(raiz.dir);
		}
	}

	public void remove(int dado) {
		raiz = removerNodo(raiz, dado);
	}

	private Nodo removerNodo(Nodo raiz, int dado) {
	    if (raiz == null) {
	        return raiz;
	    }

	    // procurar na subárvore esquerda ou direita
	    if (dado < raiz.dado) {
	        raiz.esq = removerNodo(raiz.esq, dado);
	    } else if (dado > raiz.dado) {
	        raiz.dir = removerNodo(raiz.dir, dado);
	    } else {
	        // Se encontrado, verifique a quantidade e decremente se maior que 1
	        if (raiz.quant > 1) {
	            raiz.quant--;
	        } else {
	            // Nó com quantidade igual a 1, execute a exclusão completa
	            // apenas um filho ou nenhum
	            if (raiz.esq == null) {
	                return raiz.dir;
	            } else if (raiz.dir == null) {
	                return raiz.esq;
	            }

	            // Nodo com dois filhos
	            Nodo nodoSucessor = sucessor(raiz.dir);
	            raiz.dado = nodoSucessor.dado;
	            raiz.quant = nodoSucessor.quant; // Atualiza a quantidade
	            raiz.dir = removerNodo(raiz.dir, nodoSucessor.dado);
	        }
	    }

	    // Atualiza as alturas após a remoção
	    raiz.alte = Math.max(altura(raiz.esq), altura(raiz.dir)) + 1;

	    // Rebalanceia a árvore
	    return balanceamento(raiz);
	}


	private Nodo sucessor(Nodo nodo) {
		Nodo atual = nodo;
		while (atual.esq != null) {
			atual = atual.esq;
		}
		return atual;
	}

	public boolean verificarArvoreAVL() {
		return verArvoreAVL(raiz);
	}

	private boolean verArvoreAVL(Nodo nodo) {
		if (nodo == null) {
			return true;
		}

		// ver diferenca
		int difAltura = altura(nodo.esq) - altura(nodo.dir);

		// ver se está dentro do limite de -1 a 1
		if (Math.abs(difAltura) > 1) {
			return false; // se diferença for maior que 1, false
		}

		// Verifica se as subárvores também são AVLs
		return verArvoreAVL(nodo.esq) && verArvoreAVL(nodo.dir);
	}


	
    public int nosPrimos() {
        return contarNos(raiz);
    }

    private int contarNos(Nodo nodo) {
        if (nodo == null) {
            return 0; 
        }

        
        if (primo(nodo.dado)) {
            // se for primo soma 1 e verifica as subárvores
            return 1 + contarNos(nodo.esq) + contarNos(nodo.dir);
        } else {
            // se não, verifica só as subárvores
            return contarNos(nodo.esq) + contarNos(nodo.dir);
        }
    }

    private boolean primo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public void mostrarNodosNoNivel(int nivel) {
        nodosNoNivel(raiz, 0, nivel);
    }

    private void nodosNoNivel(Nodo nodo, int nivelPresente, int nivelDesejado) {
        if (nodo == null) {
            return;
        }
        
        // se é nivel presente, faca isso
        if (nivelPresente == nivelDesejado) {
        	System.out.println(nodo.dado);
            return;
        }
        
        // ver nivel desejado recursivamente
         nodosNoNivel(nodo.esq,  nivelPresente + 1, nivelDesejado);
         nodosNoNivel(nodo.dir, nivelPresente + 1, nivelDesejado);
       
    }
    
    public int somarNivel() {
        return somar(raiz, 0);
    }

    private int somar(Nodo nodo, int nivel) {
        if (nodo == null) {
            return 0;
        }

        int soma = 0;

        if (nivel % 2 == 1) {
            soma += nodo.dado;
        }

        soma += somar(nodo.esq, nivel + 1);
        soma += somar(nodo.dir, nivel + 1);

        return soma;
    }


    public boolean busca(int dado) {
        return buscar(raiz, dado);
    }

    private boolean buscar(Nodo nodo, int dado) {
        if (nodo == null) {
            return false;
        }

        if (dado < nodo.dado) {
            return buscar(nodo.esq, dado);
        } else if (dado > nodo.dado) {
            return buscar(nodo.dir, dado);
        } else {
            return true;
        }
    }


}
