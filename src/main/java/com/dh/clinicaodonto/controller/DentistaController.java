package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.service.impl.DentistaServiceImpl;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<DentistaDto> findDentistaById(@PathVariable Long id){
        return dentistaService.findDentistaById(id);
    }
    @PostMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> saveDentista(@RequestBody Dentista dentista){
        return dentistaService.saveDentista(dentista);
    }

    @PutMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> updateDentistaById(@RequestBody Dentista dentista){
        return dentistaService.updateDentistaById(dentista);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDentista(@PathVariable Long id){
        return dentistaService.deleteDentista(id);
    }
}
