package com.siloam.healthcare;

public interface IRepositorio {

    String CriarAtendimento(String pid);
    String FazerTriagem(String aid, String fid, String des);
    String FazerConsulta(String aid, String mid, String des);

}
