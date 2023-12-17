package com.agg.certificados.repositories.botaderoRepository;

import com.agg.certificados.entity.Botadero;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BotaderoRepository implements IBotaderoRepository{
    @Override
    public void flush() {

    }

    @Override
    public <S extends Botadero> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Botadero> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Botadero> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public Botadero getOne(Integer integer) {
        return null;
    }

    /**
     * @param integer
     * @deprecated
     */
    @Override
    public Botadero getById(Integer integer) {
        return null;
    }

    @Override
    public Botadero getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Botadero> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Botadero> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Botadero> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Botadero> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Botadero> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Botadero> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Botadero, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Botadero> S save(S entity) {
        return save(entity);
    }

    @Override
    public <S extends Botadero> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Botadero> findById(Integer integer) {
        return findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return existsById(integer);
    }

    @Override
    public List<Botadero> findAll() {
        return findAll();
    }

    @Override
    public List<Botadero> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        deleteById(integer);
    }

    @Override
    public void delete(Botadero entity) {
        delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Botadero> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Botadero> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Botadero> findAll(Pageable pageable) {
        return null;
    }
}
