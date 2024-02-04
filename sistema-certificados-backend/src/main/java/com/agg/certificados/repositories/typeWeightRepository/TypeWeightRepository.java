package com.agg.certificados.repositories.typeWeightRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TypeWeightRepository implements ITypeWeightRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<com.agg.certificados.entity.TypeWeight> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public com.agg.certificados.entity.TypeWeight getOne(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public com.agg.certificados.entity.TypeWeight getById(Long aLong) {
        return null;
    }

    @Override
    public com.agg.certificados.entity.TypeWeight getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends com.agg.certificados.entity.TypeWeight> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<com.agg.certificados.entity.TypeWeight> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<com.agg.certificados.entity.TypeWeight> findAll() {
        return null;
    }

    @Override
    public List<com.agg.certificados.entity.TypeWeight> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(com.agg.certificados.entity.TypeWeight entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends com.agg.certificados.entity.TypeWeight> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<com.agg.certificados.entity.TypeWeight> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<com.agg.certificados.entity.TypeWeight> findAll(Pageable pageable) {
        return null;
    }
}
