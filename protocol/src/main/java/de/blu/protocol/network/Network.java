package de.blu.protocol.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import de.blu.protocol.network.packet.Packet;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class Network {

    @Getter(AccessLevel.PRIVATE)
    protected EndPoint endPoint;

    @Getter
    private Map<UUID, List<Consumer<Packet>>> callbacks = new HashMap<>();

    protected void init() {
        // Register Packets for all Modules

        // Register Classes which are used by the packets
        this.registerClass(List.class);
        this.registerClass(ArrayList.class);

        this.addAsyncListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (!(object instanceof Packet)) {
                    return;
                }

                Packet packet = (Packet) object;
                for (Consumer<Packet> consumer : Network.this.getPacketCallbacks(packet)) {
                    consumer.accept(packet);
                }
            }
        });

        this.getEndPoint().getKryo().register(UUID.class, new Serializer() {
            @Override
            public void write(Kryo kryo, Output output, Object object) {
                UUID uuid = (UUID) object;
                output.writeString(uuid.toString());
            }

            @Override
            public Object read(Kryo kryo, Input input, Class clazz) {
                return UUID.fromString(input.readString());
            }
        });
    }

    public void addListener(Listener listener) {
        this.getEndPoint().addListener(listener);
    }

    public void addAsyncListener(Listener listener) {
        this.getEndPoint().addListener(new Listener.ThreadedListener(listener));
    }

    public void addAsyncListener(Listener listener, ExecutorService customExecutorService) {
        this.getEndPoint().addListener(new Listener.ThreadedListener(listener, customExecutorService));
    }

    public void removeListener(Listener listener) {
        this.getEndPoint().removeListener(listener);
    }

    public void registerPacket(Class<? extends Packet> clazz) {
        this.registerClass(clazz);
    }

    public void registerClass(Class<?> clazz) {
        this.getEndPoint().getKryo().register(clazz);
    }

    public void addPacketCallback(Packet packet, Consumer<Packet> callback) {
        List<Consumer<Packet>> callbacks = this.getPacketCallbacks(packet);
        callbacks.add(callback);
        this.getCallbacks().put(packet.getUniqueId(), callbacks);
    }

    public void removePacketCallback(Packet packet, Consumer<Packet> callback) {
        this.getPacketCallbacks(packet).remove(callback);
    }

    public List<Consumer<Packet>> getPacketCallbacks(Packet packet) {
        return this.getCallbacks().getOrDefault(packet.getUniqueId(), new ArrayList<>());
    }
}