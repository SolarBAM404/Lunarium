package me.solar.lunarium.utils.autos;

public interface IAutoRegister {

    <T extends IAutoRegister> void register();

    String getId();
}
