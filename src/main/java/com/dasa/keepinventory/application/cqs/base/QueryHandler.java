package com.dasa.keepinventory.application.cqs.base;

import com.dasa.keepinventory.shared.Result;

public interface QueryHandler<Q extends Query<R>, R> {
    Result<R> handle(Q query);
}
