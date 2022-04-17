package com.innova.project.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CachedBeanCopier {

    private static final Map<Class<?>, Map<Class<?>, BeanCopier>> copierMap = new ConcurrentHashMap<>();

    public static <T> T convert(Object source, Class<T> targetClazz) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(targetClazz);
        try {
            T target = targetClazz.getConstructor().newInstance();
            _copy(source, target);
            return target;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    private static <T> void _copy(Object source, Object target) {
        getCopier(source.getClass(), target.getClass())
                .copy(source, target, null);
    }

    private static BeanCopier getCopier(Class<?> source, Class<?> target) {
        return copierMap.computeIfAbsent(target, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(source, k -> BeanCopier.create(source, target, false));
    }
}
