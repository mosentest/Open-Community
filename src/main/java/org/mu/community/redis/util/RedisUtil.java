package org.mu.community.redis.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * Created by Muu on 2014/9/26.
 */
public class RedisUtil {

    public static <T, U> List<U> convertList(List<T> from, Function<T, U> function) {
        return from.stream().map(function).collect(Collectors.toList());
    }

    public static <T, U> U[] convertArray(T[] from, Function<T, U> function, IntFunction<U[]> generator) {
        return Arrays.stream(from).map(function).toArray(generator);
    }
}
