package conta;

public class ContaCorrente extends Conta {

    public ContaCorrente() {
    }

    public ContaCorrente(int numero, int agencia) {
        super(numero, agencia);
    }

    @Override
    public String toString() {
        return String.format("ContaCorrente[numero=%d, agencia=%d, saldo=%.2f]", numero, agencia, saldo);
    }

}
