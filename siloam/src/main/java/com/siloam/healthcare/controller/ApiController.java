package com.siloam.healthcare.controller;

import java.util.List;
import java.util.UUID;

import com.siloam.healthcare.Repositorio;
import com.siloam.healthcare.consulta.Atendimento;
import com.siloam.healthcare.exames.Exame;
import com.siloam.healthcare.exames.LinkExame;
import com.siloam.healthcare.pessoas.Pessoa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private Repositorio _db = Repositorio.getInstance();

    @RequestMapping("/pessoas")
    public List<Pessoa> pessoas() {
        return _db.getPessoas();
    }

    @RequestMapping("/medicos")
    public List<Pessoa> medicos() {
        return _db.getMedicos();
    }

    @RequestMapping("/enfermeiros")
    public List<Pessoa> enfermeiros() {
        return _db.getEnfermeiros();
    }

    @RequestMapping("/pacientes")
    public List<Pessoa> pacientes() {
        return _db.getPacientes();
    }

    @RequestMapping("/atendimentos")
    public List<Atendimento> atendimentos() {
        return _db.getAtendimentos();
    }

    @RequestMapping("/exames")
    public List<Exame> exames(@RequestParam(required = false) String pid) {
        if(pid == null){
            return _db.getExames();
        }else{
            return _db.getExames(pid);
        }
    }

    @RequestMapping("/upsidedown")
    public List<LinkExame> upsidedown() {
        return _db.getUpsideDown();
    }

    @RequestMapping("/criaratendimento")
    public String criaratendimento(@RequestParam String pid) {
        return _db.CriarAtendimento(pid);
    }

    @RequestMapping("/fazertriagem")
    public String fazertriagem(@RequestParam String aid, @RequestParam String fid, @RequestParam(required = false) String des) {
        return _db.FazerTriagem(aid,fid,des);
    }

    @RequestMapping("/fazerconsulta")
    public String fazerconsulta(@RequestParam String aid, @RequestParam String mid, @RequestParam(required = false) String des) {
        return _db.FazerConsulta(aid,mid,des);
    }
}