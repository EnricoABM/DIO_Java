/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author enric
 */
public abstract class Conta {

    private static int SEQUENCIA = 1;
    private static final int AGENCIA_PADRAO = 1;

    protected int numero;
    protected int agencia;
    protected double saldo;

    public Conta() {
        this.numero = SEQUENCIA++;
        this.agencia = AGENCIA_PADRAO;
        saldo = 0;
    }

    public Conta(int numero, int agencia) {
        this.numero = numero;
        this.agencia = agencia;
        saldo = 0;
    }

    public void sacar(double valor) throws Exception {
        if (valor > saldo) {
            throw new Exception("Saldo Insuficiente");
        }
        saldo -= valor;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void transferencia(double valor, Conta contaDestino) throws Exception {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public int getNumero() {
        return numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public String toString() {
        return String.format("Conta[numero=%d, agencia=%d, saldo=%.2f]", numero, agencia, saldo);
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
        final Conta conta = (Conta) obj;
        if (numero == conta.getNumero() && agencia == conta.getAgencia()) {
            return true;
        } else {
            return false;
        }
    }

}
