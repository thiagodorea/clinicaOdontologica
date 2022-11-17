package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.service.impl.DentistaServiceImpl;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DentistaController {

    @Autowired
    private DentistaServiceImpl dentistaService;

    @GetMapping("listar")
    public List<Dentista> findAllDentistas(){
        return dentistaService.findAllDenstistas();
    }

    @GetMapping("{id}")
    public Dentista findDentistaById(@PathVariable Long id){
        return dentistaService.findDentistaById(id);
    }
    @PostMapping("salvar")
    @ResponseBody
    public ResponseEntity<Dentista> saveDentista(@RequestBody Dentista dentista){
        return new ResponseEntity<Dentista>(dentistaService.saveDentista(dentista), HttpStatus.CREATED)  ;
    }

    @PatchMapping("atualizar")
    @ResponseBody
    public Dentista updateDentistaById(@RequestBody Dentista dentista){
        return dentistaService.updateDentistaById(dentista);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<String> deleteDentista(@PathVariable Long id){
        dentistaService.deleteDentista(id);
        return new ResponseEntity<String>("Delete "+id +" deletado com sucesso",HttpStatus.OK);
    }
}
