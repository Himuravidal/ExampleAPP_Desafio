package com.example.exampleapp_desafio.model.pojo


import com.google.gson.annotations.SerializedName

data class DrugStore(
    @SerializedName("comuna_nombre")
    val comunaNombre: String,
    @SerializedName("fecha")
    val fecha: String,
    @SerializedName("fk_comuna")
    val fkComuna: String,
    @SerializedName("fk_localidad")
    val fkLocalidad: String,
    @SerializedName("fk_region")
    val fkRegion: String,
    @SerializedName("funcionamiento_dia")
    val funcionamientoDia: String,
    @SerializedName("funcionamiento_hora_apertura")
    val funcionamientoHoraApertura: String,
    @SerializedName("funcionamiento_hora_cierre")
    val funcionamientoHoraCierre: String,
    @SerializedName("local_direccion")
    val localDireccion: String,
    @SerializedName("local_id")
    val localId: String,
    @SerializedName("local_lat")
    val localLat: String,
    @SerializedName("local_lng")
    val localLng: String,
    @SerializedName("local_nombre")
    val localNombre: String,
    @SerializedName("local_telefono")
    val localTelefono: String,
    @SerializedName("localidad_nombre")
    val localidadNombre: String
)