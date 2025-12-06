package Lavanderia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoRepository {
    private final File file;
    private final Gson gson;
    private List<Servico> servicos;

    public ServicoRepository(File file) {
        this.file = file;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        this.servicos = new ArrayList<>();
        load();
    }

    public List<Servico> findAll() { return servicos; }

    public Optional<Servico> findById(String id) {
        return servicos.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void save(Servico servico) {
        findById(servico.getId()).ifPresent(servicos::remove);
        servicos.add(servico);
        persist();
    }

    public void deleteById(String id) {
        findById(id).ifPresent(s -> { servicos.remove(s); persist(); });
    }

    private void load() {
        if (!file.exists()) { servicos = new ArrayList<>(); return; }
        try (Reader r = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Servico>>(){}.getType();
            servicos = gson.fromJson(r, listType);
            if (servicos == null) servicos = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            servicos = new ArrayList<>();
        }
    }

    private void persist() {
        try (Writer w = new FileWriter(file)) {
            gson.toJson(servicos, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
