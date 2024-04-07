package me.solar.lunarium.client;

import net.fabricmc.api.ClientModInitializer;

public class LunariumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Initializing Lunarium");
    }
}
