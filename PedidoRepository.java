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

public class PedidoRepository {
    private final File file;
    private Gson gson = new Gson();
    private List<Pedido> pedidos;
    private final ClienteRepository clienteRepo;
    private final ServicoRepository servicoRepo;

    public PedidoRepository(File file, ClienteRepository clienteRepo, ServicoRepository servicoRepo) {
        this.file = file;
        this.clienteRepo = clienteRepo;
        this.servicoRepo = servicoRepo;
        this.pedidos = new ArrayList<>();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        load();
    }

    public List<Pedido> findAll() { return pedidos; }
    public Optional<Pedido> findById(String id) { return pedidos.stream().filter(p -> p.getId().equals(id)).findFirst(); }

    public void save(Pedido pedido) {
        if (pedido.getCliente() != null) pedido.setClienteId(pedido.getCliente().getId());
        if (pedido.getServicos() != null) {
            List<String> ids = new ArrayList<>();
            for (Servico s : pedido.getServicos()) ids.add(s.getId());
            pedido.setServicoIds(ids);
        }
        findById(pedido.getId()).ifPresent(pedidos::remove);
        pedidos.add(pedido);
        persist();
    }

    public void deleteById(String id) {
        findById(id).ifPresent(p -> { pedidos.remove(p); persist(); });
    }

    private void load() {
        if (!file.exists()) { pedidos = new ArrayList<>(); return; }
        try (Reader r = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Pedido>>(){}.getType();
            pedidos = gson.fromJson(r, listType);
            if (pedidos == null) pedidos = new ArrayList<>();
            for (Pedido p : pedidos) {
                if (p.getClienteId() != null) {
                    clienteRepo.findById(p.getClienteId()).ifPresent(p::setClienteReference);
                }
                if (p.getServicoIds() != null) {
                    List<Servico> resolved = new ArrayList<>();
                    for (String sid : p.getServicoIds()) {
                        servicoRepo.findById(sid).ifPresent(resolved::add);
                    }
                    p.setServicosReference(resolved);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            pedidos = new ArrayList<>();
        }
    }

    private void persist() {
        try (Writer w = new FileWriter(file)) {
            gson.toJson(pedidos, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
