package com.kgteknoloji.retailmode.util.extension

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.exceptions.CompositeException

inline fun <T> Flowable<T>.dropBreadcrumb(): Flowable<T> {
        val breadcrumb = BreadcrumbException()
        return this.onErrorResumeNext { error: Throwable ->
            throw CompositeException(error, breadcrumb)
        }
    }

inline fun <T> Maybe<T>.dropBreadcrumb(): Maybe<T> {
    val breadcrumb = BreadcrumbException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, breadcrumb)
    }
}

    class BreadcrumbException : Exception()