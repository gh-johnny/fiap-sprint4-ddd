package com.dasa.keepinventory.shared;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<T> {
    private final T data;
    private final String error;
    private final boolean isSuccess;

    private Result(T data, String error, boolean isSuccess) {
        this.data = data;
        this.error = error;
        this.isSuccess = isSuccess;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, null, true);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, error, false);
    }

    public static <T> Result<T> failure(Exception exception) {
        return new Result<>(null, exception.getMessage(), false);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure() {
        return !isSuccess;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public Optional<T> getDataOptional() {
        return Optional.ofNullable(data);
    }

    public <U> Result<U> map(Function<T, U> mapper) {
        if (isSuccess) {
            try {
                return Result.success(mapper.apply(data));
            } catch (Exception e) {
                return Result.failure(e);
            }
        }
        return Result.failure(error);
    }

    public <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
        if (isSuccess) {
            try {
                return mapper.apply(data);
            } catch (Exception e) {
                return Result.failure(e);
            }
        }
        return Result.failure(error);
    }

    public Result<T> onSuccess(Consumer<T> action) {
        if (isSuccess && data != null) {
            action.accept(data);
        }
        return this;
    }

    public Result<T> onFailure(Consumer<String> action) {
        if (!isSuccess) {
            action.accept(error);
        }
        return this;
    }
}
