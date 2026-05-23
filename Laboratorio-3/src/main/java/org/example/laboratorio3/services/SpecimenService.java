package org.example.laboratorio3.services;

import org.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import org.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import org.example.laboratorio3.domain.dto.response.pageable.PageableResponse;
import org.example.laboratorio3.domain.dto.response.specimen.SpecimenResponse;

import java.util.UUID;

public interface SpecimenService {

    SpecimenResponse create(CreateSpecimenRequest request);

    PageableResponse<SpecimenResponse> findAll(int page, int size, String sortBy, String sortOrder);

    SpecimenResponse findById(UUID id);

    SpecimenResponse update(UUID id, UpdateSpecimenRequest request);

    void delete(UUID id);
}
