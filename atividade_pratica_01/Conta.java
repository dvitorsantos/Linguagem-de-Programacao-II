public class Conta {
    private int numero;
    private float saldo;
    private int senha;

    public Conta(int numero, int senha) {
        this.numero = numero;
        this.saldo = 0;
        this.senha = senha;
    }

    public boolean depositar(float valor) {
        if (valor < 0) return false;

        this.saldo += valor;
        return true;
    }

    public boolean sacar(float valor, int senha) {
        if (this.saldo < valor || this.senha != senha) return false;

        this.saldo -= valor;
        return true;
    }

    public float verificarSaldo(int senha) {
        if (this.senha == senha) {
            return getSaldo();
        } else {
            return 0;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
