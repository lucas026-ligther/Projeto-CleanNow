import java.util.Objects;


public class Cliente {
    private String nome;
    private String id;
    private String endereço;
    private String telefone;
    private List<String> preferencias;
    private LocalDateTime dataCadastro;
    private boolean isVip;

    public Cliente(String nome,String id,String endereço,String telefone) {
        this.nome =Objects.requireNonNull(nome,"nome não pode ser nulo");
        this.id = Objects.requireNonNull(id);
        this.endereço = endereço;
        this.telefone = telefone;
        this.preferencias = new ArrayList<>();
        this.dataCadastro = LocalDateTime.now();
        this.isVip = false;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getId() {return id;}

    public String getEndereço() {return endereço;}
    public void setEndereço(String endereço) {this.endereço = endereço;}

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<String> getPreferencias() { return preferencias; }
    public void addPreferencia(String p) { preferencias.add(p); }

    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public static boolean isVip() { return isVip; }
    public void setVip(boolean vip) { this.isVip = vip; }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", endereco='" + endereço + '\'' +
                ", telefone='" + telefone + '\'' +
                ", isVip=" + isVip +
                ", dataCadastro=" + dataCadastro +
                '}';
    }

}
