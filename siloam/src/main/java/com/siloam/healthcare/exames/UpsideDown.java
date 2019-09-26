package com.siloam.healthcare.exames;

import com.siloam.healthcare.pessoas.Paciente;

import java.util.ArrayList;
import java.util.UUID;

public class UpsideDown implements IUpsideDown {

    public ArrayList<LinkExame> _Links;

    public UpsideDown(){
        _Links = new ArrayList<LinkExame>();
    }

    @Override
    public ArrayList<UUID> ListarExames(Paciente Paciente){

        ArrayList<UUID> Link = Paciente.ListarLink();
        ArrayList<UUID> result = new ArrayList<UUID>();

        LinkExame e = _Links.stream().filter(x -> x.Paciente.equals(Link.get(0))).findFirst().orElse(null);

        if(e != null){

            result = e.Exames;

            int p = _Links.indexOf(e);
            ArrayList<UUID> finalResult = result;
            LinkExame n = new LinkExame(){{
                Paciente = Link.get(1);
                Exames = finalResult;
            }};
            _Links.set(p,n);
        }

        return result;
    }

    @Override
    public void InserirExame(Paciente Paciente, Exame Exame){

        ArrayList<UUID> Link = Paciente.ListarLink();
        ArrayList<UUID> result = new ArrayList<UUID>();

        LinkExame e = _Links.stream().filter(x -> x.Paciente.equals(Link.get(0))).findFirst().orElse(null);

        if(e != null){

            result = e.Exames;
            result.add(Exame.getId());

            int p = _Links.indexOf(e);
            ArrayList<UUID> finalResult = result;
            LinkExame n = new LinkExame(){{
                Paciente = Link.get(1);
                Exames = finalResult;
            }};
            _Links.set(p,n);
        }else{

            result.add(Exame.getId());

            ArrayList<UUID> finalResult1 = result;
            e = new LinkExame(){{
                Paciente = Link.get(1);
                Exames = finalResult1;
            }};
            _Links.add(e);
        }
    }
}
