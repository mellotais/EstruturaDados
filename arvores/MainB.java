package arvores;

public class MainB {
    public static void main(String[] args) {
        ArvoreB arvore = new ArvoreB();

        arvore.insere(10);
        arvore.insere(20);
        arvore.insere(30);
        arvore.insere(40);
        arvore.insere(50);

        System.out.println("Busca pelo valor 30: " + (arvore.busca(30) ? "Encontrado" : "Não encontrado"));
        System.out.println("Busca pelo valor 60: " + (arvore.busca(60) ? "Encontrado" : "Não encontrado"));

        arvore.remove(20);
        System.out.println("Busca pelo valor 20 após remoção: " + (arvore.busca(20) ? "Encontrado" : "Não encontrado"));
    }
    }

