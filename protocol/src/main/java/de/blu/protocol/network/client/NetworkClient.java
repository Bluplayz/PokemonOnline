package de.blu.protocol.network.client;

import com.esotericsoftware.kryonet.Client;
import de.blu.protocol.network.Network;
import de.blu.protocol.network.packet.Packet;
import lombok.Getter;

import java.io.IOException;

public class NetworkClient extends Network {

    @Getter
    private Client client = new Client();

    public NetworkClient() {
        this.endPoint = this.getClient();
    }

    public boolean connect(String host, int tcpPort, int udpPort) {
        this.getClient().start();
        this.init();

        try {
            this.getClient().connect(5000, host, tcpPort, udpPort);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return false;
    }

    public void sendUDPPacket(Packet packet) {
        this.getClient().sendUDP(packet);
    }

    public void sendTCPPacket(Packet packet) {
        this.getClient().sendTCP(packet);
    }
}