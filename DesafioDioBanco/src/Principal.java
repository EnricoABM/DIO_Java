
import conta.ContaCorrente;
import conta.ContaPoupanca;
import banco.Banco;
import cliente.Cliente;

public class Principal {

    public static void main(String[] args) {

        Cliente c1 = new Cliente("Ranni", new ContaPoupanca(1, 1));
        Cliente c2 = new Cliente("Rennala", new ContaPoupanca(2, 1));
        Cliente c3 = new Cliente("Mufasa", new ContaCorrente(1, 2));
        Cliente c4 = new Cliente("Quasimodo", new ContaCorrente(2, 2));
        System.out.println("--\tInicio\t--");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);

        System.out.println("--\tDepositos\t--");
        c1.depositar(1000);
        c2.depositar(500);
        c3.depositar(250);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);

        System.out.println("--\tSaques\t--");
        try {
            c1.sacar(300);
            System.out.println(c1.getNome() + " Sacou R$" + 300d);
            c2.sacar(200);
            System.out.println(c2.getNome() + " Sacou R$" + 200d);
            c3.sacar(100);
            System.out.println(c3.getNome() + " Sacou R$" + 100d);
            c4.sacar(1);
            System.out.println(c4.getNome() + " Sacou R$" + 1d);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("--\tTransferencias\t--");
        try {
            c1.transferir(100, c2);
            System.out.println(c1.getNome() + " transferiu R$" + 100d + " para " + c2.getNome());
            c2.transferir(50, c3);
            System.out.println(c2.getNome() + " transferiu R$" + 50d + " para " + c3.getNome());
            c3.transferir(25, c4);
            System.out.println(c3.getNome() + " transferiu R$" + 25d + " para " + c4.getNome());
            c4.transferir(0, c1);
            System.out.println(c4.getNome() + " transferiu R$" + 0d + " para " + c1.getNome());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("--\tAdicionando Clientes ao Banco\t--");
        Banco b1 = new Banco("Banco do Norte");
        System.out.println(b1);
        b1.adicionarCliente(c1);
        b1.adicionarCliente(c2);
        b1.adicionarCliente(c3);
        b1.adicionarCliente(c4);
        System.out.println(b1);

    }

}
