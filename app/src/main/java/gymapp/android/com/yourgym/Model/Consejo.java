package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class Consejo {

    private  String IdConsejos;
    private String consejo;
    private String consejoIng;

    public Consejo(JSONObject jsonObject)
    {
        setIdConsejos(jsonObject.optString("IdConsejos",""));
        setConsejo(jsonObject.optString("consejo",""));
        setConsejoIng(jsonObject.optString("consejoIng",""));
    }

    public String getConsejoIng() {
        return consejoIng;
    }

    public void setConsejoIng(String consejoIng) {
        this.consejoIng = consejoIng;
    }

    public String getIdConsejos() {
        return IdConsejos;
    }

    public void setIdConsejos(String idConsejos) {
        IdConsejos = idConsejos;
    }

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }
}
