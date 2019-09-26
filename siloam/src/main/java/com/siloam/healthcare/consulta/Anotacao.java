package com.siloam.healthcare.consulta;

import java.util.Date;

public class Anotacao {
    private Date _Data;
    private String _Texto;

    public Anotacao(String Texto){
        _Data = new Date();
        _Texto = Texto;
    }

    public Date getData(){
        return _Data;
    }

    public String getTexto(){
        return _Texto;
    }
}
