package net.vanderkast.wishlists.server.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

public interface OrError<T> {
    static <T> OrError<T> of(@NonNull T t) {
        return new Ok<>(t);
    }

    static <T> OrError<T> ofNullable(T t) {
        if (t == null)
            return new Fail<>(() -> "Result is null");
        return new Ok<>(t);
    }

    static <T> OrError<T> error(Error t) {
        return new Fail<>(t);
    }

    static <T> OrError<T> error(String error) {
        return new Fail<>(() -> error);
    }

    @JsonUnwrapped
    T getData();

    @JsonUnwrapped
    Error getError();

    @JsonIgnore
    default boolean isOk() {
        return getError() == null;
    }

    @JsonIgnore
    default boolean isError() {
        return getError() != null;
    }

    @AllArgsConstructor
    @Getter
    class Ok<T> implements OrError<T> {
        private final T data;

        @Override
        public Error getError() {
            return null;
        }
    }

    @AllArgsConstructor
    @Getter
    class Fail<T> implements OrError<T> {
        private final Error error;

        @Override
        public T getData() {
            return null;
        }
    }
}
