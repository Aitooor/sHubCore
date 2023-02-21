package online.starsmc.hubcore.service;

public interface Service {

    void start();

    default void stop() {
    }
}
