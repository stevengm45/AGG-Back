package com.agg.certificados.repositories.dataDriverRepository;

import com.agg.certificados.entity.DataDriver;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DataDriverRepository implements IDataDriverRepository{
    @Override
    public void flush() {

    }

    @Override
    public <S extends DataDriver> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DataDriver> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<DataDriver> entities) {

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
    public DataDriver getOne(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public DataDriver getById(Long aLong) {
        return null;
    }

    @Override
    public DataDriver getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends DataDriver> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DataDriver> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DataDriver> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DataDriver> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DataDriver> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DataDriver> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DataDriver, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DataDriver> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends DataDriver> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DataDriver> findById(Long aLong) {
        return findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<DataDriver> findAll() {
        return null;
    }

    @Override
    public List<DataDriver> findAllById(Iterable<Long> longs) {
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
    public void delete(DataDriver entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DataDriver> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DataDriver> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DataDriver> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public DataDriver findByIdDataGenerator(Long aLong) {
        return findByIdDataGenerator(aLong);
    }
}
