package sangi.apishare;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import net.minecraft.server.world.ServerWorld;

import fi.iki.elonen.NanoHTTPD;
import net.minecraft.world.World;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ServerFunctional extends NanoHTTPD {
    public MinecraftServer server;
    public ServerFunctional(MinecraftServer server1) throws IOException {
        super(25566); // Порт для API
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("API сервер запущен на порту 25566");
        server = server1;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        if (uri.equals("/status")) {
            return newFixedLengthResponse("Сервер работает");
        }
        if (uri.equals("/players")) {
            var line = "";
            for (ServerPlayerEntity player : getPlayers()) {
                // Выводим имя каждого игрока
                line += " "+player.getName();
            }
            return newFixedLengthResponse(line);
        }
        return newFixedLengthResponse("wromg server");
    }
    private List<ServerPlayerEntity> getPlayers() {
        // Получаем ссылку на мир сервера (если это серверный контекст)
        ServerWorld world = MinecraftClient.getInstance().getServer().getWorld(World.OVERWORLD);
        return world.getPlayers();  // Возвращаем список всех игроков
    }
}
