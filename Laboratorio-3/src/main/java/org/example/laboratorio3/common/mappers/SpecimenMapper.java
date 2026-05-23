package org.example.laboratorio3.common.mappers;

import org.example.laboratorio3.domain.dto.response.pageable.PageableResponse;
import org.example.laboratorio3.domain.dto.response.specimen.SpecimenResponse;
import org.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import org.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.example.laboratorio3.domain.entities.Specimen;

import java.util.UUID;

@Component
public class SpecimenMapper {

    public Specimen toEntityCreate(CreateSpecimenRequest request) {
        return Specimen.builder()
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public Specimen toEntityUpdate(UpdateSpecimenRequest request, UUID id) {
        return Specimen.builder()
                .id(id)
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public SpecimenResponse toDto(Specimen specimen) {
        return SpecimenResponse.builder()
                .id(specimen.getId())
                .name(specimen.getName())
                .region(specimen.getRegion())
                .dangerLevel(specimen.getDangerLevel())
                .isFriendly(specimen.getIsFriendly())
                .build();
    }

    public PageableResponse<SpecimenResponse> toDtoPage(Page<Specimen> page) {
        Page<SpecimenResponse> dtoPage = page.map(this::toDto);

        return PageableResponse.<SpecimenResponse>builder()
                .content(dtoPage.getContent())
                .page(dtoPage.getNumber())
                .size(dtoPage.getSize())
                .totalElements(dtoPage.getTotalElements())
                .totalPages(dtoPage.getTotalPages())
                .last(dtoPage.isLast())
                .first(dtoPage.isFirst())
                .build();
    }
}