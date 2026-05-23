package org.example.laboratorio3.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.laboratorio3.common.mappers.SpecimenMapper;
import org.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import org.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import org.example.laboratorio3.domain.dto.response.pageable.PageableResponse;
import org.example.laboratorio3.domain.dto.response.specimen.SpecimenResponse;
import org.example.laboratorio3.domain.entities.Specimen;
import org.example.laboratorio3.exceptions.ResourceNotFoundException;
import org.example.laboratorio3.repositories.SpecimenRepository;
import org.example.laboratorio3.services.SpecimenService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {

    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse create(CreateSpecimenRequest request) {

        return specimenMapper.toDto(
                specimenRepository.save(
                        specimenMapper.toEntityCreate(request)
                )
        );
    }

    @Override
    public PageableResponse<SpecimenResponse> findAll(
            int page,
            int size,
            String sortBy,
            String sortOrder
    ) {

        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Specimen> specimenPage =
                specimenRepository.findAll(pageable);

        return specimenMapper.toDtoPage(specimenPage);
    }

    @Override
    public SpecimenResponse findById(UUID id) {

        return specimenMapper.toDto(
                specimenRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Specimen not found in Hyrule Records"
                                )
                        )
        );
    }

    @Override
    @Transactional
    public SpecimenResponse update(
            UUID id,
            UpdateSpecimenRequest request
    ) {

        this.findById(id);

        return specimenMapper.toDto(
                specimenRepository.save(
                        specimenMapper.toEntityUpdate(request, id)
                )
        );
    }

    @Override
    @Transactional
    public void delete(UUID id) {

        this.findById(id);

        specimenRepository.deleteById(id);
    }
}