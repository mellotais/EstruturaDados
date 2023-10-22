package arvores;

public class MainBinaria {

	public static void main(String[] args) {
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		arvore.insere(30);
		arvore.insere(25);
		arvore.insere(20);
		arvore.insere(22);
		arvore.insere(40);
		arvore.insere(27);
		arvore.insere(45);

		
		//arvore.mostrarEmOrdemCrescente();
		
		arvore.mostrarPorNivel();
		arvore.mostrarMaior();
		arvore.mostrarMenor();
		arvore.mostrarNosFolhas();
		arvore.mostrarAncestraisNo(40);
		arvore.mostrarDescendentesNo(27);
		arvore.mostrarSubArvoreDireita(25);
		arvore.mostrarSubArvoreEsquerda(25);
		arvore.mostrarNumerosPares();
		arvore.mostrarNivel(30);
		arvore.mostrarAltura();
		arvore.mostrarTamanho();
	}

}
