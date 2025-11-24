package Lavanderia;

public class Pagamento {

    private double valor;
    private String metodo;
    private boolean confirmado;

    public Pagamento(double valor, String metodo) {
        this.valor = valor;
        this.metodo = metodo;
        this.confirmado = false;
    }

    public void confirmar() {
        confirmado = true;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getMetodo() {
        return metodo;
    }

    public double getValor() {
        return valor;
    }
}