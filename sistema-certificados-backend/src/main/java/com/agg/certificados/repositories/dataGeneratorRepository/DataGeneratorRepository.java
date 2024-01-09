package com.agg.certificados.repositories.dataGeneratorRepository;

import com.agg.certificados.entity.DataGenerator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DataGeneratorRepository implements IDataGeneratorRepository{
    @Override
    public void flush() {

    }

    @Override
    public <S extends DataGenerator> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DataGenerator> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<DataGenerator> entities) {

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
    public DataGenerator getOne(Long aLong) {
        return null;
    }

    /**
     * @param aLong
     * @deprecated
     */
    @Override
    public DataGenerator getById(Long aLong) {
        return null;
    }

    @Override
    public DataGenerator getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends DataGenerator> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DataGenerator> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DataGenerator> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DataGenerator> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DataGenerator> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DataGenerator> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DataGenerator, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DataGenerator> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends DataGenerator> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DataGenerator> findById(Long aLong) {
        return findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return existsById(aLong);
    }

    @Override
    public List<DataGenerator> findAll() {
        return null;
    }

    @Override
    public List<DataGenerator> findAllById(Iterable<Long> longs) {
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
    public void delete(DataGenerator entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DataGenerator> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DataGenerator> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DataGenerator> findAll(Pageable pageable) {
        return null;
    }
}
