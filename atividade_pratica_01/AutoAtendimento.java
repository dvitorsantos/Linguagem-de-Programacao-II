import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AutoAtendimento {
    private Banco banco;

    public static void main(String[] args) {
        Banco banco = new Banco("Banco do Brasil", 1, 1234);
        AutoAtendimento autoAtendimento = new AutoAtendimento(banco);

        boolean exit = false;
        boolean exitInside = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            autoAtendimento.menuInicial();
            int operation = scanner.nextInt();

            if (operation == 1) {
                System.out.println("Digite a agencia: ");
                int agencia = scanner.nextInt();
                System.out.println("Digite a senha: ");
                int senha = scanner.nextInt();
                if (autoAtendimento.loginGerente(agencia, senha)) {
                    if (exitInside == true) exitInside = false;
                    while (!exitInside) {
                        autoAtendimento.menuGerente();
                        operation = scanner.nextInt();
                        if (operation == 1) {
                            System.out.println("Digite o nome do cliente: ");
                            String nome = scanner.next();
                            System.out.println("Digite o cpf do cliente: ");
                            long cpf = scanner.nextLong();
                            System.out.println("Digite o número da conta do cliente: ");
                            int numero_conta = scanner.nextInt();
                            System.out.println("Digite a senha do cliente: ");
                            int senha_cliente = scanner.nextInt();

                            Conta conta = new Conta(numero_conta, senha_cliente);
                            Cliente cliente = new Cliente(nome, cpf, conta);

                            if (autoAtendimento.gerenteCadastrarCliente(cliente)) {
                                System.out.println("Cliente cadastrado!");
                            } else {
                                System.out.println("Erro ao cadastrar cliente!");
                            }
                        }
                        if (operation == 2) {
                            System.out.println("Digite o cpf do cliente");
                            long cpf_remover = scanner.nextLong();
                            if (autoAtendimento.gerenteExcluirCliente(cpf_remover)) {
                                System.out.println("Cliente removido!");
                            } else {
                                System.out.println("Cpf inválido!");
                            }
                        }
                        if (operation == 0) {
                            exitInside = true;
                        }
                    }
                } else {
                    System.out.println("Senha inválida!");
                }
            }
            if (operation == 2) {
                System.out.println("Digite o número da conta: ");
                int numero_conta = scanner.nextInt();
                System.out.println("Digite a senha: ");
                int senha = scanner.nextInt();
                if (autoAtendimento.loginCliente(numero_conta, senha)) {
                    if (exitInside == true) exitInside = false;
                    while (!exitInside) {
                        autoAtendimento.menuCliente();
                        operation = scanner.nextInt();
                        if (operation == 1) {
                            System.out.println("Digite o valor para depositar: ");
                            float valor = scanner.nextFloat();
                            if (autoAtendimento.clienteDepositar(valor, numero_conta)) {
                                System.out.println("Valor depositado!");
                            } else {
                                System.out.println("Erro ao depositar valor!");
                            }
                        }
                        if (operation == 2) {
                            float saldo = autoAtendimento.clienteVerificarSaldo(senha, numero_conta);

                            System.out.println("Saldo: " + saldo);
                        }
                        if (operation == 3) {
                            System.out.println("Digite o valor do saque: ");
                            float valor_saque = scanner.nextFloat();
                            if (autoAtendimento.clienteSacar(valor_saque, senha, numero_conta)) {

                            }
                        }
                        if (operation == 0) {
                            exitInside = true;
                        }
                    }
                } else {
                    System.out.println("Senha inválida!");
                }
            }
        }

    }

    public AutoAtendimento(Banco banco) {
        this.banco = banco;
    }

    public void menuInicial() {
        System.out.println("1. Login Gerente");
        System.out.println("2. Login Cliente");
    }

    public void menuGerente() {
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Excluir Cliente");
        System.out.println("0. Logout");
    }

    public void menuCliente() {
        System.out.println("1. Depositar");
        System.out.println("2. Ver Saldo");
        System.out.println("3. Sacar");
        System.out.println("0. Logout");
    }

    public boolean loginCliente(int numConta, int senha) {
        ArrayList<Cliente> clientes = this.banco.getClientes();

        Iterator iterator = clientes.iterator();

        while (iterator.hasNext()) {
            Cliente cliente = (Cliente) iterator.next();
            Conta conta = cliente.getConta();

            if (conta.getNumero() == numConta && conta.getSenha() == senha) {
                return true;
            }
        }

        return false;
    }

    public boolean loginGerente(int numAgencia, int senha) {
        if (this.banco.getAgencia() == numAgencia && this.banco.getSenhaGerente() == senha) {
            return true;
        } else {
            return false;
        }
    }

    public boolean gerenteCadastrarCliente(Cliente cliente) {
        if (cliente == null) return false;

        this.banco.cadastrarCliente(cliente);
        return true;
    }

    public boolean gerenteExcluirCliente(long cpf) {
        return this.banco.getClientes().removeIf(cliente -> cliente.getCpf() == cpf);
    }

    public boolean clienteSacar(float valor, int senha, int numero_conta) {
        Iterator iterator = this.banco.getClientes().iterator();

        while (iterator.hasNext()) {
            Cliente cliente = (Cliente) iterator.next();
            Conta conta = cliente.getConta();
            if (conta.getNumero() == numero_conta && conta.getSenha() == senha) {
                conta.sacar(valor, senha);
                return true;
            }
        }

        return false;
    }

    public boolean clienteDepositar(float valor, int numero_conta) {
        Iterator iterator = this.banco.getClientes().iterator();

        while (iterator.hasNext()) {
            Cliente cliente = (Cliente) iterator.next();
            Conta conta = cliente.getConta();
            if (conta.getNumero() == numero_conta) {
                conta.depositar(valor);
                return true;
            }
        }

        return false;
    }

    public float clienteVerificarSaldo(int senha, int numero_conta) {
        Iterator iterator = this.banco.getClientes().iterator();

        while (iterator.hasNext()) {
            Cliente cliente = (Cliente) iterator.next();
            Conta conta = cliente.getConta();

            if (conta.getSenha() == senha && conta.getNumero() == numero_conta) {
                return conta.verificarSaldo(senha);
            }
        }

        return 0;
    }
}
