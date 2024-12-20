package sangi.apishare;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.fabricmc.api.ClientModInitializer;

import java.io.IOException;
import java.util.List;

public class SangishareClient implements ClientModInitializer {
    public ServerFunctional functional;
    @Override
    public void onInitializeClient() {
        ServerLifecycleEvents.SERVER_STARTED.register(this::startListening);
    }

    public void startListening(MinecraftServer server) {
        try {
            new ServerFunctional(server); // Инициализируем сервер API
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
