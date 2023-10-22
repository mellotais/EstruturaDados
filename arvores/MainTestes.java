package arvores;

import java.util.Random;

public class MainTestes {

    public static void main(String[] args) {

        ArvoreB arvoreB = new ArvoreB();
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();

        Random random = new Random();
        
        int[] dadosAleatoriosAVL = new int[100000];
        int[] dadosAleatoriosB = new int[100000];
        int[] dadosAleatoriosBinaria = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dadosAleatoriosAVL[i] = random.nextInt();
            dadosAleatoriosB[i] = random.nextInt();
            dadosAleatoriosBinaria[i] = random.nextInt();
        }

        int[] dadosOrdenadosAVL = new int[100000];
        int[] dadosOrdenadosB = new int[100000];
        int[] dadosOrdenadosBinaria = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dadosOrdenadosAVL[i] = i;
            dadosOrdenadosB[i] = i;
            dadosOrdenadosBinaria[i] = i;
        }
		
        
        
        
        
        ////////////////ARVORE AVL ///////////////////////////
		

        
		//    - - -  ALEATORIO - - - 
		
		long inicio2 = System.nanoTime();
		for (int dado : dadosAleatoriosAVL) {
		    arvoreAVL.insere(dado);
		}
		long fim2 = System.nanoTime();
		System.out.println("Tempo de inserção na Árvore AVL (dados aleatórios): " + (fim2 - inicio2) + " ns");

		
		// exclui na arvore AVL
		inicio2 = System.nanoTime();
		for (int dado : dadosAleatoriosAVL) {
		    if(arvoreAVL.busca(dado)) {
		    	arvoreAVL.remove(dado);
		    }
		}
		fim2 = System.nanoTime();
		System.out.println("Tempo de exclusão na Árvore AVL (dados aleatórios): " + (fim2 - inicio2) + " ns");
		
		
		
		// - - - ORDENADO

		// insere na arvore AVL
		long inicio3 = System.nanoTime();
		for (int dado : dadosOrdenadosAVL) {
			arvoreAVL.insere(dado);
		}
		long fim3 = System.nanoTime();
		System.out.println("Tempo de inserção na Árvore AVL (dados ordenados): " + (fim3 - inicio3) + " ns");

		
		
		
		// exclui na arvore AVL
		inicio3 = System.nanoTime();
		for (int dado : dadosOrdenadosAVL) {
		    if(arvoreAVL.busca(dado)) {
		    	arvoreAVL.remove(dado);
		    }
		}
		fim3 = System.nanoTime();
		System.out.println("Tempo de exclusão na Árvore AVL (dados ordenados): " + (fim3 - inicio3) + " ns");

		
		
		
		////////////////   ARVORE B ///////////////////////////
		
		// - - - ORDENADO
		
		
		// insere na arvore B
		long inicio1 = System.nanoTime();
		for (int dado : dadosOrdenadosB) {
		    arvoreB.insere(dado);
		}
		long fim1 = System.nanoTime();
		System.out.println("Tempo de inserção na Árvore B (dados ordenados): " + (fim1 - inicio1) + " ns");

		
		
		
		// exclui na arvore B
		inicio1 = System.nanoTime();
		for (int dado : dadosOrdenadosB) {
		    if(arvoreB.busca(dado)) {
		        arvoreB.remove(dado);
		    }
		}
		fim1 = System.nanoTime();
		System.out.println("Tempo de exclusão na Árvore B (dados ordenados): " + (fim1 - inicio1) + " ns");

		
		
		// - - - ALEATÓRIO
	
		arvoreB = new ArvoreB();
		// insere na arvore B
		long inicio = System.nanoTime();
		for (int dado : dadosAleatoriosB) {
		    arvoreB.insere(dado);
		}
		long fim = System.nanoTime();
		System.out.println("Tempo de inserção na Árvore B (dados aleatórios): " + (fim - inicio) + " ns");

		
		// exclui na arvore B
		inicio = System.nanoTime();
		for (int dado : dadosAleatoriosB) {
		    if(arvoreB.busca(dado)) {
		        arvoreB.remove(dado);
		    }
		}
		fim = System.nanoTime();
		System.out.println("Tempo de exclusão na Árvore B (dados aleatórios): " + (fim - inicio) + " ns");



			
		
        //////////////// ARVORE BINARIA ///////////////////////////
		
		
		//    - - -  ORDENADO - - - 
		
		long inicio4 = System.nanoTime();
		for (int dado : dadosAleatoriosBinaria) {
		    arvoreBinaria.insere(dado);
		}
		long fim4 = System.nanoTime();
		System.out.println("Tempo de inserção na Árvore Binaria (dados aleatórios): " + (fim4 - inicio4) + " ns");

		
		// exclui na arvore Binaria
		inicio4 = System.nanoTime();
		for (int dado : dadosAleatoriosBinaria) {
		    if(arvoreBinaria.busca(dado)) {
		        arvoreBinaria.remove(dado);
		    }
		}
		fim4 = System.nanoTime();
		System.out.println("Tempo de exclusão na Árvore Binaria (dados aleatórios): " + (fim4 - inicio4) + " ns");

		
		
		long inicioBusca4 = System.nanoTime();
		arvoreB.busca(Integer.MAX_VALUE);
		long fimBusca4 = System.nanoTime();
		System.out.println("Tempo de busca na Árvore AVL (chave inexistente): " + (fimBusca4 - inicioBusca4) + " ns");
		
			
		// - - - DESORDENADO

		// insere na arvore Binaria
		long inicio5 = System.nanoTime();
		for (int dado : dadosOrdenadosBinaria) {
		    arvoreBinaria.insere(dado);
		}
		long fim5 = System.nanoTime();
		System.out.println("Tempo de inserção na Árvore Binaria (dados ordenados): " + (fim5 - inicio5)  + " ns");
	
		
		
		
		// exclui na arvore Binaria
		inicio5 = System.nanoTime();
		for (int dado : dadosOrdenadosBinaria) {
		    if(arvoreBinaria.busca(dado)) {
		        arvoreBinaria.remove(dado);
		    }
		}
		fim5 = System.nanoTime();
		System.out.println("Tempo de exclusão na Árvore Binaria (dados ordenados): " + (fim5 - inicio5)+ " ns");


		
	}

}
