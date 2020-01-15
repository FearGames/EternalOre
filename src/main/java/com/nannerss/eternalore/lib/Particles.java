package com.nannerss.eternalore.lib;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Particles {

    public static void spawnParticle(final Player player, final String particle, final float x, final float y, final float z, final float xOffset, final float yOffset, final float zOffset, final float speed, final int amount, final int data) {
        player.spawnParticle(Particle.valueOf(particle), x, y, z, amount, xOffset, yOffset, zOffset, speed, data);
    }

    public static <T> void spawnParticle(final Player player, final String particle, final float x, final float y, final float z, final float xOffset, final float yOffset, final float zOffset, final float speed, final int amount, final T data) {
        player.spawnParticle(Particle.valueOf(particle), x, y, z, amount, xOffset, yOffset, zOffset, speed, data);
    }
}
