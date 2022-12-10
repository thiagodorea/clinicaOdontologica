package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("dentistas")
public class DentistaController {

    @Autowired
    private DentistaServiceImpl dentistaService;

    @GetMapping()

    public List<DentistaDto> findAllDentistas(){
        return dentistaService.findAllDenstistas();
    }

    @GetMapping("{matricula}")
    public ResponseEntity<DentistaDto> findByMatricula(@PathVariable String matricula){
        return dentistaService.findByMatricula(matricula);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> saveDentista(@RequestBody DentistaDto dentistaDto){
        return dentistaService.saveDentista(dentistaDto);
    }

    @PutMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> updateDentista(@RequestBody @Valid DentistaDto dentistadto){
        return dentistaService.updateDentistaByMatricula(dentistadto);
    }

    @DeleteMapping("{matricula}")
    public ResponseEntity<String> deleteDentista(@PathVariable String matricula){
        return dentistaService.deleteDentista(matricula);
    }
}
