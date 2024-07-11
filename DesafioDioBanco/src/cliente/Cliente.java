package cliente;

import conta.Conta;

public class Cliente {

    private String nome;
    private Conta conta;

    public Cliente(String nome, Conta conta) {
        this.nome = nome;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void depositar(double valor) {
        conta.depositar(valor);
    }

    public void sacar(double valor) throws Exception {
        conta.sacar(valor);
    }

    public void transferir(double valor, Cliente cliDestino) throws Exception {
        conta.sacar(valor);
        cliDestino.depositar(valor);
    }

    public String toString() {
        return String.format("Cliente[nome=%s, conta=%s]", nome, conta.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente cliente = (Cliente) obj;
        if (nome.equals(cliente.getNome()) && conta.equals(cliente.getConta())) {
            return true;
        } else {
            return false;
        }
    }

}
