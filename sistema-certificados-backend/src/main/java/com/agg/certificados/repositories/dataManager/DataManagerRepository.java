package com.agg.certificados.repositories.dataManager;

import com.agg.certificados.entity.DataManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DataManagerRepository implements IDataManagerRepository{
    @Override
    public void flush() {


    }

    @Override
    public <S extends DataManager> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DataManager> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<DataManager> entities) {

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
    public DataManager getOne(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public DataManager getById(Long aLong) {
        return null;
    }

    @Override
    public DataManager getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends DataManager> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DataManager> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DataManager> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DataManager> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DataManager> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DataManager> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DataManager, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DataManager> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends DataManager> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DataManager> findById(Long aLong) {
        return findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<DataManager> findAll() {
        return null;
    }

    @Override
    public List<DataManager> findAllById(Iterable<Long> longs) {
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
    public void delete(DataManager entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DataManager> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DataManager> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DataManager> findAll(Pageable pageable) {
        return null;
    }
}
