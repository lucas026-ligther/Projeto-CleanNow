import java.time.LocalDate;
import java.util.List;

public class Cliente {
    private String nome;
    private String id;
    private String endereco;
    private String telefone;
    private String email;
    private List<String> preferências;
    private LocalDate dataCadastro;

    public Cliente( String NOME, String ID, String ENDERECO, String TELEFONE, String EMAIL) {
        this.nome = NOME;
        this.id = ID;
        this.endereco = ENDERECO;
        this.telefone = TELEFONE;
        this.email = EMAIL;
    }
}
