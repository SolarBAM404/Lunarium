package me.solar.lunarium.utils;

import me.solar.lunarium.Lunarium;
import me.solar.lunarium.utils.autos.IAutoRegister;
import net.minecraft.item.Item;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ReflectionExecutor {

    public static void RegisterAuto() {
        Reflections reflections = new Reflections("me.solar.lunarium");
        Set<Class<? extends IAutoRegister>> derivedClasses = reflections.getSubTypesOf(IAutoRegister.class);

        for (Class<? extends IAutoRegister> derivedClass : derivedClasses) {
            IAutoRegister autoRegister;

            if (derivedClass.isEnum()) {
                IAutoRegister[] constants = derivedClass.getEnumConstants();
                for (IAutoRegister constant : constants) {
                    constant.register();
                }
                continue;
            }

            try {
                autoRegister = (IAutoRegister) derivedClass.getDeclaredField("INSTANCE").get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            autoRegister.register();

            if (autoRegister instanceof Item) {
                Lunarium.registerItem((Item) autoRegister);
            }
        }
    }

}
