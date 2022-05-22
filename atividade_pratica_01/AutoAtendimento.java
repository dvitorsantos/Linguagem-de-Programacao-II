import java.util.Iterator;
import java.util.Scanner;

public class AutoAtendimento {
    final private Banco banco = new Banco("Banco do Brasil", 1, 1234);

    public static void main(String[] args) {
        AutoAtendimento autoAtendimento = new AutoAtendimento();

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
                        int operation_inside = scanner.nextInt();
                        if (operation_inside == 1) {
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
                        if (operation_inside == 2) {
                            System.out.println("Digite o cpf do cliente");
                            long cpf_remover = scanner.nextLong();
                            if (autoAtendimento.gerenteExcluirCliente(cpf_remover)) {
                                System.out.println("Cliente removido!");
                            } else {
                                System.out.println("Cpf inválido!");
                            }
                        }
                        if (operation_inside == 0) {
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
                        int operation_inside = scanner.nextInt();
                        if (operation_inside == 1) {
                            System.out.println("Digite o valor para depositar: ");
                            float valor = scanner.nextFloat();
                            if (autoAtendimento.clienteDepositar(valor, numero_conta)) {
                                System.out.println("Valor depositado!");
                            } else {
                                System.out.println("Digite um número válido para depósito.");
                            }
                        }
                        if (operation_inside == 2) {
                            System.out.println("Digite sua senha para confirmação: ");
                            int senha_verificacao = scanner.nextInt();
                            float saldo = autoAtendimento.clienteVerificarSaldo(senha_verificacao, numero_conta);

                            //a funcao clienteVerificarSaldo retorna -1 se senha inválida.
                            if (saldo >= 0) {
                                System.out.println("Saldo: " + saldo);
                            } else {
                                System.out.println("Senha incorreta.");
                            }
                        }
                        if (operation_inside == 3) {
                            System.out.println("Digite o valor do saque: ");
                            float valor_saque = scanner.nextFloat();
                            System.out.println("Digite sua senha para confirmação: ");
                            int senha_verificacao = scanner.nextInt();
                            if (autoAtendimento.clienteSacar(valor_saque, senha_verificacao, numero_conta)) {
                                System.out.println("Retirado R$" + valor_saque);
                            } else {
                                System.out.println("Senha incorreta ou saldo insuficiente.");
                            }
                        }
                        if (operation_inside == 0) {
                            exitInside = true;
                        }
                    }
                } else {
                    System.out.println("Senha inválida!");
                }
            }
            if (operation == 0) {
                exit = true;
            }
        }

    }

    public void menuInicial() {
        System.out.println("1. Login Gerente");
        System.out.println("2. Login Cliente");
        System.out.println("0. Sair");
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
        Iterator iterator = this.banco.getClientes().iterator();

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
            if (conta.getNumero() == numero_conta && conta.getSenha() == senha)
                if (conta.sacar(valor, senha))
                    return true;
        }

        return false;
    }

    public boolean clienteDepositar(float valor, int numero_conta) {
        Iterator iterator = this.banco.getClientes().iterator();

        while (iterator.hasNext()) {
            Cliente cliente = (Cliente) iterator.next();
            Conta conta = cliente.getConta();
            if (conta.getNumero() == numero_conta)
                if (conta.depositar(valor))
                    return true;
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

        return -1;
    }
}
