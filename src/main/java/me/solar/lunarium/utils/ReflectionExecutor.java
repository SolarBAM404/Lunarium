package me.solar.lunarium.utils;

import me.solar.lunarium.Lunarium;
import me.solar.lunarium.utils.autos.IAutoRegister;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ReflectionExecutor {

    public static void RegisterItems() {
        Reflections reflections = new Reflections("me.solar.lunarium.items");
        Set<Class<? extends LunariumItem>> derivedClasses = reflections.getSubTypesOf(LunariumItem.class);

        for (Class<? extends LunariumItem> derivedClass : derivedClasses) {
            LunariumItem item;
            try {
                item = (LunariumItem) derivedClass.getDeclaredMethod("getInstance").invoke(null);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            item.register();
            Lunarium.registerItem(item);
        }

    }

    public static void RegisterAuto() {
        Reflections reflections = new Reflections("me.solar.lunarium");
        Set<Class<? extends IAutoRegister>> derivedClasses = reflections.getSubTypesOf(IAutoRegister.class);

        for (Class<? extends IAutoRegister> derivedClass : derivedClasses) {
            IAutoRegister autoRegister;
            try {
                autoRegister = (IAutoRegister) derivedClass.getDeclaredField("INSTANCE").get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            autoRegister.register();
        }
    }

}
