package com.agg.certificados.services.botaderoServices;

import com.agg.certificados.dtos.BotaderoRequestDto;
import com.agg.certificados.dtos.BotaderoResponseDto;
import com.agg.certificados.entity.Botadero;

import java.util.List;
import java.util.Optional;

public interface IBotaderoService {
    Optional<Botadero> getById(int id);
    List<BotaderoResponseDto> getAll();

    int save(BotaderoRequestDto dto);
    boolean delete(int id);
    boolean update(BotaderoRequestDto dto,int id);

}
