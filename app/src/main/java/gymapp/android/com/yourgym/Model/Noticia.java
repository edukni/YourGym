package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 03/11/2015.
 */
public class Noticia {
    private String _titulo;
    private String _imagen;
    private int _tipoContenido;
    private String _descripion = "";
    private String _descripionIng = "";

    public Noticia()
    {

    }

    public Noticia(JSONObject object)
    {
        this.set_titulo(object.optString("titulo", ""));
        if(_titulo.equals(""))
        {
            _titulo = object.optString("nombre","");
        }
        this.set_tipoContenido(object.optInt("tipo", 0));
        this.set_imagen(object.optString("urlFoto", ""));
        this.set_descripion(object.optString("descripcion",""));
        this.set_descripionIng(object.optString("descripcionIng",""));
    }

    public String get_descripionIng() {
        return _descripionIng;
    }

    public void set_descripionIng(String _descripionIng) {
        this._descripionIng = _descripionIng;
    }

    public String get_titulo() {
        return _titulo;
    }

    public void set_titulo(String _titulo) {
        this._titulo = _titulo;
    }

    public String get_imagen() {
        return _imagen;
    }

    public void set_imagen(String _imagen) {
        this._imagen = _imagen;
    }

    public int get_tipoContenido() {
        return _tipoContenido;
    }

    public void set_tipoContenido(int _tipoContenido) {
        this._tipoContenido = _tipoContenido;
    }

    public String get_descripion() {
        return _descripion;
    }

    public void set_descripion(String _descripion) {
        this._descripion = _descripion;
    }
}
