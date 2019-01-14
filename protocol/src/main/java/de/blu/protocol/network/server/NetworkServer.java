package de.blu.protocol.network.server;

import com.esotericsoftware.kryonet.Server;
import de.blu.protocol.network.Network;
import de.blu.protocol.network.packet.Packet;
import lombok.Getter;

import java.io.IOException;

public class NetworkServer extends Network {

    @Getter
    private Server server = new Server();

    public NetworkServer() {
        this.endPoint = this.getServer();
    }

    public boolean start(int tcpPort, int udpPort) {
        this.getServer().start();
        this.init();

        try {
            this.getServer().bind(tcpPort, udpPort);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void sendUDPPacketToAll(Packet packet) {
        this.getServer().sendToAllUDP(packet);
    }

    public void sendUDPPacketTo(int id, Packet packet) {
        this.getServer().sendToUDP(id, packet);
    }

    public void sendUDPPacketToAllExcept(int id, Packet packet) {
        this.getServer().sendToAllExceptUDP(id, packet);
    }

    public void sendTCPPacketToAll(Packet packet) {
        this.getServer().sendToAllTCP(packet);
    }

    public void sendTCPPacketTo(int id, Packet packet) {
        this.getServer().sendToTCP(id, packet);
    }

    public void sendTCPPacketToAllExcept(int id, Packet packet) {
        this.getServer().sendToAllExceptTCP(id, packet);
    }
}
