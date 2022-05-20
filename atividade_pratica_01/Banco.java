import java.util.ArrayList;
import java.util.Iterator;

public class Banco {
    private String nome;
    private int agencia;
    private ArrayList<Cliente> clientes;
    private int senhaGerente;

    public Banco(String nome, int agencia, int senhaGerente) {
        this.nome = nome;
        this.agencia = agencia;
        this.clientes = new ArrayList<Cliente>();
        this.senhaGerente = senhaGerente;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (cliente != null) {
            this.clientes.add(cliente);
        }
    }

    public void excluirCliente(long cpf) {
        Iterator iterator = clientes.iterator();

        while (iterator.hasNext()) {
            Cliente cliente = (Cliente) iterator.next();
            if (cliente.getCpf() == cpf) {
                iterator.remove();
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public int getSenhaGerente() {
        return senhaGerente;
    }

    public void setSenhaGerente(int senhaGerente) {
        this.senhaGerente = senhaGerente;
    }
}
