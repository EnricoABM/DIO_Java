package conta;

public class ContaPoupanca extends Conta {

    public ContaPoupanca() {
    }

    public ContaPoupanca(int numero, int agencia) {
        super(numero, agencia);
    }

    @Override
    public String toString() {
        return String.format("ContaPoupanca[numero=%d, agencia=%d, saldo=%.2f]", numero, agencia, saldo);
    }
}
