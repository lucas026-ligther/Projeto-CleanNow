package Lavanderia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

public class ClienteRepository {
    private final File file;
    private final Gson gson;
    private Map<String, Cliente> clientes;

    public ClienteRepository(File file) {
        this.file = file;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        this.clientes = new HashMap<>();
        load();
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes.values());
    }

    public Optional<Cliente> findById(String id) {
        return Optional.ofNullable(clientes.get(id));
    }

    public void save(Cliente cliente) {
        if (cliente == null) return;
        clientes.put(cliente.getId(), cliente);
        persist();
    }

    public void deleteById(String id) {
        if (id == null) return;
        clientes.remove(id);
        persist();
    }

    private void load() {
        if (!file.exists()) {
            clientes = new HashMap<>();
            return;
        }
        try (Reader r = new FileReader(file)) {
            Type type = new TypeToken<Map<String, Cliente>>() {}.getType();
            Map<String, Cliente> loaded = gson.fromJson(r, type);
            if (loaded == null) loaded = new HashMap<>();
            clientes = loaded;
        } catch (Exception e) {
            e.printStackTrace();
            clientes = new HashMap<>();
        }
    }

    private void persist() {
        try (Writer w = new FileWriter(file)) {
            gson.toJson(clientes, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
