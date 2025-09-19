package structures;

public class Pilha {
    private Node topo;

    public boolean isEmpty(){
        if(topo == null){
            return true;
        }
        return false;
    }

    public void push(Ponto pixel){
        Node novo = new Node(pixel);
        novo.prox = topo; // o topo dessa linha é o topo antigo, então é aqui q ele aponta para o topo antigo
        topo = novo; // esse topo é o novo
    }

    public Ponto pop(){
        if(isEmpty()){
            throw new RuntimeException("Pilha vazia!");
        }
        Ponto valor = topo.pixel; // apenas guarda o valor que será retirado
        topo = topo.prox; // remove de fato, o marcador topo passa a apontar para 
        return valor;
    }
}
