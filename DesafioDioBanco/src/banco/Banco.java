package banco;


import cliente.Cliente;
import conta.Conta;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private List<Conta> contas;
    private List<Cliente> clientes;

    public Banco() {
        nome = null;
        contas = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public Banco(String nome) {
        this.nome = nome;
        contas = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarCliente(Cliente cli) {
        clientes.add(cli);
    }

    public void removerCliente(Cliente cli) {
        clientes.remove(cli);
    }

    public Cliente buscarCliente(Cliente cli) {
        Cliente clienteProcura = null;
        for (int i = 0; i < clientes.size(); i++) {
            if (cli.equals(clientes.get(i))) {
                clienteProcura = cli;
            }
        }
        return clienteProcura;
    }

    public void atualizarCliente(Cliente cli, Cliente novo) {
        int indice = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (cli.equals(clientes.get(i))) {
                indice = i;
            }
        }
        clientes.set(indice, novo);
        
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(nome + "[");
        for (Cliente cli : clientes) {
            str.append(cli.toString());
        }
        str.append("]");
        return str.toString();
    }
}
