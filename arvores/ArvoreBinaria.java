package arvores;
import java.util.LinkedList;
import java.util.Queue;


public class ArvoreBinaria {
	private class Nodo{
		private int chave;
		private Nodo dir, esq;
		
		public Nodo (int item) {
			this.chave=item;
			dir = esq = null;
		} 
	}

	Nodo raiz = null;
	
	public void insere(int chave) {
	    if (!busca(chave)) {
	        Nodo novoNodo = new Nodo(chave);
	        Nodo atual = raiz;
	        Nodo parent = null;

	        while (atual != null) {
	            parent = atual;
	            if (chave < atual.chave) {
	                atual = atual.esq;
	            } else {
	                atual = atual.dir;
	            }
	        }

	        if (parent == null) {
	            raiz = novoNodo;
	        } else if (chave < parent.chave) {
	            parent.esq = novoNodo;
	        } else {
	            parent.dir = novoNodo;
	        }
	    }
	}


	public boolean busca(int chave) {
	    Nodo atual = raiz;
	    while (atual != null) {
	        if (chave == atual.chave) {
	            return true;
	        } else if (chave < atual.chave) {
	            atual = atual.esq;
	        } else {
	            atual = atual.dir;
	        }
	    }
	    return false;
	}



	
	/*public void mostrarEmOrdem() {
		mostrarOrdenado(raiz);
	}*/
	
	public void mostrarEmOrdemCrescente() {
		mostrarCrescente(raiz);
	}
	
	public void mostrarEmOrdemDecrescente() {
		mostrarDecrescente(raiz);
	}
	
	
	/*private void mostrarOrdenado(Nodo raiz) { //modificar, acrescentar crescente
		if (raiz!=null) {
			mostrarOrdenado(raiz.esq);
			System.out.println(raiz.chave);
			mostrarOrdenado(raiz.dir);
		}
	}*/
	
	// a) Mostrar o maior numero
	public void mostrarMaior() {
	    Nodo maiorNodo = mostrarMaiorNumero(raiz);
	    if (maiorNodo != null) {
	        System.out.println("Maior valor: " + maiorNodo.chave);
	    } else {
	        System.out.println("�rvore vazia");
	    }
	}

	private Nodo mostrarMaiorNumero(Nodo nodo) {
	    if (nodo == null) {
	        return null;
	    }
	    
	    while (nodo.dir != null) {
	        nodo = nodo.dir;
	    }
	    
	    return nodo;
	}


	// b) Mostrar o menor numero
	public int mostrarMenor() {
	    Nodo menorNodo = mostrarMenorNumero(raiz);
	    if (menorNodo != null) {
	        System.out.println("Menor valor" + menorNodo.chave);
	        return menorNodo.chave;
	    }
	    System.out.println("�rvore vazia");
	    return Integer.MAX_VALUE;
	}

	private Nodo mostrarMenorNumero(Nodo nodo) {
	    while (nodo != null && nodo.esq != null) {
	        nodo = nodo.esq;
	    }
	    return nodo;
	}

	// c) Mostrar os nos folhas
	public void mostrarNosFolhas() {
	    System.out.println("N�s folhas:");
	    mostrarFolhas(raiz);
	}

	private void mostrarFolhas(Nodo nodo) {
	    if (nodo != null) {
	        if (nodo.esq == null && nodo.dir == null) {
	            System.out.println(nodo.chave);
	        }
	        mostrarFolhas(nodo.esq);
	        mostrarFolhas(nodo.dir);
	    }
	}

	// d) Mostrar os nos ancestrais de um no
	public void mostrarAncestraisNo(int chave) {
	    System.out.println("N�s ancestrais de " + chave + ":");
	    mostrarAncestrais(raiz, chave);
	}

	private boolean mostrarAncestrais(Nodo nodo, int chave) {
	    if (nodo == null) {
	        return false;
	    }
	    if (nodo.chave == chave) {
	        return true;
	    }
	    if (mostrarAncestrais(nodo.esq, chave) || mostrarAncestrais(nodo.dir, chave)) {
	        System.out.print(nodo.chave + " ");
	        return true;
	    }
	    return false;
	}

	// e) Mostrar os nos descendentes de um no
	public void mostrarDescendentesNo(int chave) {
	    Nodo nodo = encontrarNodo(raiz, chave);
	    if (nodo != null) {
	        System.out.println("N�s descendentes :" + chave);
	        mostrarDescendentes(nodo);
	    } else {
	        System.out.println("N� n�o encontrado");
	    }
	}

	private Nodo encontrarNodo(Nodo nodo, int chave) {
	    if (nodo == null) {
	        return null;
	    }
	    if (nodo.chave == chave) {
	        return nodo;
	    }
	    Nodo esquerda = encontrarNodo(nodo.esq, chave);
	    if (esquerda != null) {
	        return esquerda;
	    }
	    return encontrarNodo(nodo.dir, chave);
	}

	private void mostrarDescendentes(Nodo nodo) {
	    if (nodo != null) {
	        System.out.print(nodo.chave + " ");
	        mostrarDescendentes(nodo.esq);
	        mostrarDescendentes(nodo.dir);
	    }
	}


	// f) Mostrar a subarvore da direita de um no
	public void mostrarSubArvoreDireita(int chave) {
	    Nodo nodo = encontrarNodo(raiz, chave);
	    if (nodo != null) {
	        System.out.println("Sub�rvore da direita de " + chave + ":");
	        mostrarArvore(nodo.dir);
	    } else {
	        System.out.println("N� n�o encontrado na �rvore.");
	    }
	}

	// g) Mostrar a subarvore da esquerda de um no
	public void mostrarSubArvoreEsquerda(int chave) {
	    Nodo nodo = encontrarNodo(raiz, chave);
	    if (nodo != null) {
	        System.out.println("Sub�rvore da esquerda de " + chave + ":");
	        mostrarArvore(nodo.esq);
	    } else {
	        System.out.println("N� n�o encontrado na �rvore.");
	    }
	}

	
	private void mostrarArvore(Nodo nodo) {
	    if (nodo != null) {
	        mostrarArvore(nodo.esq);
	        System.out.print(nodo.chave + " ");
	        mostrarArvore(nodo.dir);
	    }
	}

	// h) Transformar a arvore em uma lista encadeada
	public LinkedList<Integer> arvoreEmListaEncadeada() {
	    LinkedList<Integer> lista = new LinkedList<>();
	    transformarEmListaEncadeada(raiz, lista);
	    return lista;
	}

	private void transformarEmListaEncadeada(Nodo nodo, LinkedList<Integer> lista) {
	    if (nodo != null) {
	        transformarEmListaEncadeada(nodo.esq, lista);
	        lista.add(nodo.chave);
	        transformarEmListaEncadeada(nodo.dir, lista);
	    }
	}

	// i) Mostrar somente os numeros pares
	public void mostrarNumerosPares() {
	    System.out.print("N�meros pares na �rvore: ");
	    mostrarNumerosPares(raiz);
	    System.out.println();
	}

	private void mostrarNumerosPares(Nodo nodo) {
	    if (nodo != null) {
	        if (nodo.chave % 2 == 0) {
	            System.out.print(nodo.chave + " ");
	        }
	        mostrarNumerosPares(nodo.esq);
	        mostrarNumerosPares(nodo.dir);
	    }
	}
	// j) Mostrar o nivel de um nodo
	public void mostrarNivel(int chave) {
	    int nivel = encontrarNivel(raiz, chave, 0);
	    if (nivel == -1) {
	        System.out.println("N� n�o encontrado na �rvore.");
	    } else {
	        System.out.println("N�vel do n� " + chave + ": " + nivel);
	    }
	}

	private int encontrarNivel(Nodo nodo, int chave, int nivelAtual) {
	    if (nodo == null) {
	        return -1; // 
	    }
	    if (nodo.chave == chave) {
	        return nivelAtual;
	    }
	    int nivelEsquerda = encontrarNivel(nodo.esq, chave, nivelAtual + 1);
	    if (nivelEsquerda != -1) {
	        return nivelEsquerda;
	    }
	    return encontrarNivel(nodo.dir, chave, nivelAtual + 1);
	}


	// k) Mostrar a altura da arvore
	public void mostrarAltura() {
	    int altura = calcularAltura(raiz);
	    System.out.println("Altura da �rvore: " + altura);
	}

	private int calcularAltura(Nodo nodo) {
	    if (nodo == null) {
	        return -1;
	    }
	    int alturaEsquerda = calcularAltura(nodo.esq);
	    int alturaDireita = calcularAltura(nodo.dir);
	    return Math.max(alturaEsquerda, alturaDireita) + 1;
	}

	// l) Mostrar o tamanho da arvore
	public void mostrarTamanho() {
	    int tamanho = calcularTamanho(raiz);
	    System.out.println("Tamanho da �rvore: " + tamanho);
	}

	private int calcularTamanho(Nodo nodo) {
	    if (nodo == null) {
	        return 0;
	    }
	    int tamanhoEsquerda = calcularTamanho(nodo.esq);
	    int tamanhoDireita = calcularTamanho(nodo.dir);
	    return tamanhoEsquerda + tamanhoDireita + 1;
	}


	
	private void mostrarCrescente(Nodo raiz) { //modificar, acrescentar crescente
	if (raiz!=null) {
		mostrarCrescente(raiz.esq);
		System.out.println(raiz.chave);
		mostrarCrescente(raiz.dir);
	}
}
	private void mostrarDecrescente(Nodo raiz) { //modificar, acrescentar decrescente
		if (raiz!=null) {
			mostrarDecrescente(raiz.dir);
			System.out.println(raiz.chave);
			mostrarDecrescente(raiz.esq);
		}
	}
	
	public void mostrarPorNivel() {
		if(raiz ==null) {
			System.out.println("�rvore vazia!");
			return;
		}
		Queue<Nodo> fila= new LinkedList<>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i=0;i<tamanhoNivel;i++) {
				Nodo nodoAtual = fila.poll();
				if(nodoAtual !=null) {
					System.out.print(nodoAtual.chave+" ");
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}else {
					System.out.print("- ");
				}
			}
			System.out.println(); //Nova linha para separar os niveis
		}
		
	}
	
	
	
	public void remove(int chave) {
		raiz=removerItem(raiz, chave);
	}
	
	private Nodo removerItem(Nodo raiz, int chave) {
		if (raiz== null) {
			
			//No nao encontrado, nao faz nada
			return null;
		}
		if (chave<raiz.chave) {
			
			//chave a ser removida esta a esquerda
		raiz.esq= removerItem(raiz.esq,chave);
		}else if (chave>raiz.chave){
		raiz.dir= removerItem(raiz.dir,chave);
		}else {
			
				//encontramos o no a ser removido
			if(raiz.esq==null) {
				
				//caso em que o no nao possui filho a esquerda
				return raiz.dir;
			}else if(raiz.dir==null) {
				
				//caso em que o no nao possui filha a direita
				return raiz.esq;
			}else {
				
				//caso em que o no possui ambos os filhos
				//N� sucessor ser� o menoor da sub�vore da direita
				Nodo sucessor = encontrarSucessor(raiz.dir);
				
				//substituimos o valor do no a ser removido pelo valor do sucessor
				raiz.chave = sucessor.chave;
				raiz.dir = removerItem(raiz.dir,sucessor.chave);
			}
		}
		return raiz;
	}
	
	private Nodo encontrarSucessor(Nodo nodo) {
		while (nodo.esq!= null) {
			nodo= nodo.esq;
		}
		return nodo;
	}
	
}
