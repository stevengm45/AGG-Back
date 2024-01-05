package com.agg.certificados.repositories.manager;

import com.agg.certificados.entity.Manager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ManagerRepository implements IManagerRepository{
    @Override
    public void flush() {

    }

    @Override
    public <S extends Manager> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Manager> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Manager> entities) {

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
    public Manager getOne(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public Manager getById(Long aLong) {
        return null;
    }

    @Override
    public Manager getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Manager> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Manager> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Manager> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Manager> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Manager> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Manager> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Manager, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Manager> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends Manager> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Manager> findById(Long aLong) {
        return findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Manager> findAll() {
        return null;
    }

    @Override
    public List<Manager> findAllById(Iterable<Long> longs) {
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
    public void delete(Manager entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Manager> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Manager> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Manager> findAll(Pageable pageable) {
        return null;
    }
}
