package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.service.impl.DentistaServiceImpl;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("dentistas")
@CrossOrigin
@EnableGlobalAuthentication
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
    public ResponseEntity<DentistaDto> saveDentista(@RequestBody Dentista dentista){
        return dentistaService.saveDentista(dentista);
    }

    @PatchMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> updateDentista(@RequestBody @Valid DentistaDto dentistadto){
        return dentistaService.updateDentistaByMatricula(dentistadto);
    }

    @DeleteMapping("{matricula}")
    public ResponseEntity<String> deleteDentista(@PathVariable String matricula){
        return dentistaService.deleteDentista(matricula);
    }
}
