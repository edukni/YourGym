package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 01/11/2015.
 */
public class Usuario {
     private String id_usuario;
    private String nombre_usuario;
    private String constrasenia_usuario;
    private int edad;
    private float peso;
    private String nombreGimnasio;
    private float altura;
    private String idDieta;
    private String urlFoto;
    private String idInstructor;

    public Usuario(JSONObject jsonObject)
    {
        id_usuario = jsonObject.optString("id_usuario","");
        nombre_usuario = jsonObject.optString("nombre_usuario", "");
        constrasenia_usuario = jsonObject.optString("constrasenia_usuario", "");
        edad = jsonObject.optInt("edad",0);
        peso = (float)jsonObject.optDouble("peso",0.0);
        nombreGimnasio = jsonObject.optString("nombreGimnasio", "");
        altura = (float)jsonObject.optDouble("altura",0.0);
        idDieta = jsonObject.optString("idDieta","");
        urlFoto = jsonObject.optString("urlFoto","");
        idInstructor = jsonObject.optString("idInstructor","");
    }

    public Usuario(String name, int age, String username, String password){
        this.setNombre_usuario(name);
        this.setEdad(age);
        this.setNombre_usuario(username);
        this.setConstrasenia_usuario(password);
    }


    public Usuario(String username, String password){
        this.setNombre_usuario(username);
        this.setConstrasenia_usuario(password);
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getConstrasenia_usuario() {
        return constrasenia_usuario;
    }

    public void setConstrasenia_usuario(String constrasenia_usuario) {
        this.constrasenia_usuario = constrasenia_usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    public void setNombreGimnasio(String nombreGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(String idDieta) {
        this.idDieta = idDieta;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(String idInstructor) {
        this.idInstructor = idInstructor;
    }
}