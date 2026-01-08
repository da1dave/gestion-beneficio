package com.davidag.gestion_beneficio.Enum;

public enum TipoDocBen {

    CUENTA_COBRO("Cuenta de cobro"),
    RECIBO_MAT("Recibo de pago matricula"),
    CERTF_NOTAS("Certificado original de notas"),
    CERTF_SISBEN("Certificado del Sisben"),
    FOTOC_DOCB("Fotocopia del documento de identidad del beneficiario"),
    FOTOC_DOCA("Fotocopia del documento de identidad del acudiente"),
    PENSUM("Pensum de la carrera"),
    CERTF_JUR("Certificacion juramentada veracidad de los documentos"),
    CERTF_BANK("Certificacion Bancaria"),
    CERTF_SS("Certificacion del servicio social");


    private final String descripcion;

    TipoDocBen(String descripcion){
        this.descripcion=descripcion;
    }

    public String getDescripcion(){

        return descripcion;
    }
    
}
