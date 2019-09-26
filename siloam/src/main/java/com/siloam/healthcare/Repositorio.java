package com.siloam.healthcare;

import com.siloam.healthcare.consulta.Atendimento;
import com.siloam.healthcare.consulta.ClassificacaoRisco;
import com.siloam.healthcare.consulta.Consulta;
import com.siloam.healthcare.exames.Exame;
import com.siloam.healthcare.exames.Laudo;
import com.siloam.healthcare.exames.LinkExame;
import com.siloam.healthcare.exames.UpsideDown;
import com.siloam.healthcare.exames.analiseclinica.FatorSanguineo;
import com.siloam.healthcare.exames.analiseclinica.Hemograma;
import com.siloam.healthcare.exames.analiseclinica.TipoSanguineo;
import com.siloam.healthcare.exames.grafico.EcoCardiograma;
import com.siloam.healthcare.exames.nascimento.*;
import com.siloam.healthcare.pessoas.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public final class Repositorio implements IRepositorio {

    private static Repositorio INSTANCE;
    private ArrayList<Pessoa> _Pessoas;
    private ArrayList<Atendimento> _Atendimentos;

    private ArrayList<Exame> _Exames;
    private UpsideDown _UpsideDown;

    private Repositorio(){

        _Pessoas = new ArrayList<Pessoa>();
        _Atendimentos = new ArrayList<Atendimento>();

        _Exames = new ArrayList<Exame>();
        _UpsideDown = new UpsideDown();

        Seed();
    }

    private void Seed(){

        _Pessoas.add(new Paciente(){{ Nome = "Paciente Fulano"; Nascimento = StringToDate("1955-12-21"); Email = "paciente1@gmail.com"; Telefone = "2345-6789"; }});
        _Pessoas.add(new Paciente(){{ Nome = "Paciente Ciclano"; Nascimento = StringToDate("1956-2-13"); Email = "paciente2@gmail.com"; Telefone = "2345-6789"; }});
        _Pessoas.add(new Paciente(){{ Nome = "Paciente Beltrano"; Nascimento = StringToDate("1957-10-12"); Email = "paciente3@gmail.com"; Telefone = "2345-6789"; }});
        _Pessoas.add(new Enfermeiro(){{ Nome = "Enfermeiro Beltrano"; Nascimento = StringToDate("1957-10-12"); Email = "paciente3@gmail.com"; Telefone = "2345-6789"; DataAdmissao = StringToDate("2015-10-01"); Matricula = "0001"; CRE = "1234"; }});
        _Pessoas.add(new Enfermeiro(){{ Nome = "Enfermeiro Fulano"; Nascimento = StringToDate("1955-12-21"); Email = "paciente1@gmail.com"; Telefone = "2345-6789"; DataAdmissao = StringToDate("2015-10-01"); Matricula = "0001"; CRE = "1234"; }});
        _Pessoas.add(new Medico(){{ Nome = "Medico Fulano"; Nascimento = StringToDate("1955-12-21"); Email = "paciente1@gmail.com"; Telefone = "2345-6789";DataAdmissao = StringToDate("2015-10-01"); Matricula = "0001"; CRM = "1234"; }});
        _Pessoas.add(new Medico(){{ Nome = "Medico Ciclano"; Nascimento = StringToDate("1956-2-13"); Email = "paciente2@gmail.com"; Telefone = "2345-6789";DataAdmissao = StringToDate("2015-10-01"); Matricula = "0001"; CRM = "1234"; }});

        _Exames.add(new Apgar(){{
            Data = new Date();
            Validade = new Date();
            Laudo = new Laudo();
            Aparencia = com.siloam.healthcare.exames.nascimento.Aparencia.SemCianose;
            Pulsacao = com.siloam.healthcare.exames.nascimento.Pulsacao.Maior100Bpm;
            Gesticulacao = com.siloam.healthcare.exames.nascimento.Gesticulacao.Careta;
            Atividade = com.siloam.healthcare.exames.nascimento.Atividade.PoucaAtividade;
            Respiracao = com.siloam.healthcare.exames.nascimento.Respiracao.Forte;
        }});

        _Exames.add(new Hemograma(){{
            Data = new Date();
            Validade = new Date();
            Laudo = new Laudo();
            TipoSanguineo = com.siloam.healthcare.exames.analiseclinica.TipoSanguineo.O;
            FatorSanguineo = com.siloam.healthcare.exames.analiseclinica.FatorSanguineo.AntigenoRh;
        }});

        _Exames.add(new EcoCardiograma(){{
            Data = new Date();
            Validade = new Date();
            Laudo = new Laudo();
            Grafico = null;
        }});

        _UpsideDown.InserirExame((Paciente)_Pessoas.get(0),_Exames.get(0));
        _UpsideDown.InserirExame((Paciente)_Pessoas.get(0),_Exames.get(1));
        _UpsideDown.InserirExame((Paciente)_Pessoas.get(0),_Exames.get(2));
    }

    public static synchronized Repositorio getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Repositorio();
        }
        return INSTANCE;
    }

    public List<Pessoa> getPessoas(){
        return _Pessoas;
    }

    public List<Pessoa> getMedicos(){
        return _Pessoas.stream().filter(x -> x instanceof Medico).collect(Collectors.toList());
    }

    public List<Pessoa> getEnfermeiros(){
        return _Pessoas.stream().filter(x -> x instanceof Enfermeiro).collect(Collectors.toList());
    }

    public List<Pessoa> getPacientes(){
        return _Pessoas.stream().filter(x -> x instanceof Paciente).collect(Collectors.toList());
    }

    public List<Exame> getExames(){
        return _Exames;
    }

    public List<Exame> getExames(String pid){

        Pessoa p = EncontrarPessoa(pid);

        if(p != null){

            ArrayList<UUID> e = _UpsideDown.ListarExames((Paciente)p);
            return _Exames.stream().filter(x -> e.contains(x.getId())).collect(Collectors.toList());

        }else{
            return null;
        }
    }

    public ArrayList<LinkExame> getUpsideDown(){
        return _UpsideDown._Links;
    }

    public List<Atendimento> getAtendimentos(){
        return _Atendimentos;
    }

    @Override
    public String CriarAtendimento(String pid){

        Pessoa p = EncontrarPessoa(pid);

        if(p != null){

            Atendimento a = new Atendimento((Paciente)p);
            _Atendimentos.add(a);

            return a.getId().toString();
        }else{
            return null;
        }
    }

    @Override
    public String FazerTriagem(String aid, String fid, String des){

        Atendimento a = EncontrarAtendimento(aid);
        Pessoa f = EncontrarPessoa(fid);

        if(a != null && f != null){

            a.AdicionarTriagem((Funcionario)f, ClassificacaoRisco.Amarela, des);

            return a.getId().toString();
        }else{
            return null;
        }
    }

    @Override
    public String FazerConsulta(String aid, String mid, String des){

        Atendimento a = EncontrarAtendimento(aid);
        Pessoa m = EncontrarPessoa(mid);

        if(a != null && m != null){

            Consulta c = new Consulta((Medico)m);
            c.AdicionarAnotacao(des);
            c.Fechar(true);

            a.AdicionarConsulta(c);
            return a.getId().toString();
        }else{
            return null;
        }
    }

    private Atendimento EncontrarAtendimento(String s){
        UUID id = StringToUUID(s);
        return EncontrarAtendimento(id);
    }

    private Atendimento EncontrarAtendimento(UUID id){
        Atendimento a = _Atendimentos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        return a;
    }

    private Pessoa EncontrarPessoa(String s){
        UUID id = StringToUUID(s);
        return EncontrarPessoa(id);
    }

    private Pessoa EncontrarPessoa(UUID id){
        Pessoa p = _Pessoas.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        return p;
    }

    private Date StringToDate(String s){
        Date result = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result  = dateFormat.parse(s);
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        return result ;
    }

    private UUID StringToUUID(String s){
        UUID result = null;
        try{
            result = UUID.fromString(s);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return result;
    }
}
