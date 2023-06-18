package com.example.github.core.network.mapper

interface BaseMapper<R, D> {

    fun toDomain(remote: R): D

    fun fromDomain(domain: D): R

    fun toDomain(remote: List<R>): List<D> {
        return remote.map { toDomain(it) }
    }

    fun fromDomain(domain: List<D>): List<R> {
        return domain.map { fromDomain(it) }
    }

}