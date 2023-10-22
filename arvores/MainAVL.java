package arvores;


public class MainAVL {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        // inserir elementos na árvore
        arvore.insere(10);
        arvore.insere(5);
        arvore.insere(15);
        arvore.insere(20);
        arvore.insere(3);
        arvore.insere(8);
        arvore.insere(8);
      
        
        System.out.println();
        arvore.verArvore();

        
        // árvore em ordem
        System.out.println();
        System.out.println("Árvore ordenada:");
        arvore.mostrarEmOrdem();
        
        
        // árvore após a remoção
        System.out.println();
        int numero = 5;
        arvore.remove(numero);
        
        System.out.println("Árvore avl depois de remover '" + numero + "':");
        arvore.verArvore();
        
        
        // ver se é avl
        System.out.println();
        boolean avl = arvore.verificarArvoreAVL();
        System.out.println("Árvore é avl? " +avl);
        
        // nós primos
        System.out.println();
        int primos = arvore.nosPrimos();
        
        System.out.println("Nós primos: "+primos);
        
       
        // nodos no nivel
        System.out.println();
        System.out.println("Nodos no nível: ");
        arvore.mostrarNodosNoNivel(2);
        
        //soma dos nodos em niveis impares
        System.out.println();
        int soma = arvore.somarNivel();
        System.out.println("Soma dos nodos nos niveis impares: " + soma);
        
    }
}