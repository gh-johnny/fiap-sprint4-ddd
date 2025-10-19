package com.dasa.keepinventory.application.cqs.base;

import com.dasa.keepinventory.shared.Result;

public interface CommandHandler<C extends Command, R> {
    Result<R> handle(C command);
}
