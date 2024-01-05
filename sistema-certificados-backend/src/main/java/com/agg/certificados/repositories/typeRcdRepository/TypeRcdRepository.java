package com.agg.certificados.repositories.typeRcdRepository;

import com.agg.certificados.entity.TypeRcd;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TypeRcdRepository implements ITypeRcdRepository{
    @Override
    public void flush() {

    }

    @Override
    public <S extends TypeRcd> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends TypeRcd> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<TypeRcd> entities) {

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
    public TypeRcd getOne(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public TypeRcd getById(Long aLong) {
        return null;
    }

    @Override
    public TypeRcd getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends TypeRcd> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TypeRcd> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TypeRcd> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TypeRcd> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TypeRcd> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TypeRcd> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TypeRcd, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends TypeRcd> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends TypeRcd> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TypeRcd> findById(Long aLong) {
        return findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TypeRcd> findAll() {
        return null;
    }

    @Override
    public List<TypeRcd> findAllById(Iterable<Long> longs) {
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
    public void delete(TypeRcd entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TypeRcd> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TypeRcd> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TypeRcd> findAll(Pageable pageable) {
        return null;
    }
}
