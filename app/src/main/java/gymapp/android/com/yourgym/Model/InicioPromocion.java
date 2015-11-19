package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class InicioPromocion {

    private String idPromocion;
    private String tituloPromocion;
    private String urlFoto;
    private String tituloPromocionIng;

    public InicioPromocion(JSONObject jsonObject)
    {
        setIdPromocion(jsonObject.optString("idPromocion",""));
        setTituloPromocion(jsonObject.optString("tituloPromocion",""));
        setUrlFoto(jsonObject.optString("urlFoto",""));
        setTituloPromocionIng(jsonObject.optString("tituloPromocionIng",""));
    }

    public String getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTituloPromocion() {
        return tituloPromocion;
    }

    public String getTituloPromocionIng() {
        return tituloPromocionIng;
    }

    public void setTituloPromocionIng(String tituloPromocionIng) {
        this.tituloPromocionIng = tituloPromocionIng;
    }

    public void setTituloPromocion(String tituloPromocion) {
        this.tituloPromocion = tituloPromocion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
