package structures;

public class Fila {
    private Node inicio,fim;

    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }
        return false;
    }

    public void enqueue(Ponto pixel){
        Node novo = new Node(pixel);
        if (fim != null) fim.prox = novo;
        fim = novo;
        if (inicio == null) inicio = novo;
    }

    public Ponto dequeue(){
        if (isEmpty()) throw new RuntimeException("Fila vazia");
        Ponto pixel = inicio.pixel;
        inicio = inicio.prox;
        if (inicio == null) fim = null;
        return pixel;
    }

}
