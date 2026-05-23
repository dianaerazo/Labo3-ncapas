package org.example.laboratorio3.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.laboratorio3.common.utils.ResponseBuilder;
import org.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import org.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import org.example.laboratorio3.domain.dto.response.GeneralResponse;
import org.example.laboratorio3.services.SpecimenService;


@RestController
@RequestMapping("/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;
    private final ResponseBuilder responseBuilder;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> create(
            @Valid @RequestBody CreateSpecimenRequest request,
            HttpServletRequest servletRequest
    ) {
        return responseBuilder.buildResponse(
                servletRequest.getRequestURI(),
                "Specimen registered successfully",
                HttpStatus.CREATED,
                specimenService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            HttpServletRequest servletRequest
    ) {
        return responseBuilder.buildResponse(
                servletRequest.getRequestURI(),
                "Specimens retrieved successfully",
                HttpStatus.OK,
                specimenService.findAll(page, size, sortBy, sortOrder)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> findById(
            @PathVariable UUID id,
            HttpServletRequest servletRequest
    ) {
        return responseBuilder.buildResponse(
                servletRequest.getRequestURI(),
                "Specimen retrieved successfully",
                HttpStatus.OK,
                specimenService.findById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponse> update(
            @PathVariable UUID id,
            @RequestBody UpdateSpecimenRequest request,
            HttpServletRequest servletRequest
    ) {
        return responseBuilder.buildResponse(
                servletRequest.getRequestURI(),
                "Specimen updated successfully",
                HttpStatus.OK,
                specimenService.update(id, request)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> delete(
            @PathVariable UUID id,
            HttpServletRequest servletRequest
    ) {
        specimenService.delete(id);

        return responseBuilder.buildResponse(
                servletRequest.getRequestURI(),
                "Specimen deleted successfully",
                HttpStatus.OK,
                null
        );
    }
}