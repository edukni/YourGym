package gymapp.android.com.yourgym.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import gymapp.android.com.yourgym.Model.Usuario;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 01/11/2015.
 */
public class FragmentLogin extends Fragment {
    FrameLayout _fragmentRutina;
    OnChangePage _listener;

    private File[] listFile;
    private ArrayList<String> f = new ArrayList<>();
    int lenguaje;

    public FragmentLogin(OnChangePage listener) {
        _listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup)inflater.inflate(R.layout.fragment_contenido, container, false);
        Button btnLogin = (Button)container.findViewById(R.id.btnLogin);
        final TextView txtUserName = (TextView)container.findViewById(R.id.etUsername);

        final TextView txtContrasenia = (TextView)container.findViewById(R.id.etPassword);
        ImageView image = (ImageView)container.findViewById(R.id.imageView);

        Context context = getActivity();
        SharedPreferences prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        lenguaje = prefs.getInt("language", 0);

//        getFromSdcard();
//        image.setImageURI(Uri.parse(f.get(0)));
        TextView tvRegisterLink = (TextView)container.findViewById(R.id.txtViewregister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefe=getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
                String nombre = prefe.getString("nombre", "");
                String contr = prefe.getString("contrasenia","");


                Usuario usuario=new Usuario(nombre,contr);


                if(nombre.equals(txtUserName.getText().toString()))
                {
                    if(lenguaje == 0)
                        _listener.changePage("Inicio","http://yourgym.site88.net/loadInicio.php");
                    else if(lenguaje == 0)
                        _listener.changePage("Home","http://yourgym.site88.net/loadInicio.php");
                }
                else {
                    Toast.makeText(getActivity(), "Nombre de usuario o contraseña invalida", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                _listener.changePage("Register",null);
            }
        });
        return container;
    }



    public void getFromSdcard()
    {
        File file= new File(android.os.Environment.getExternalStorageDirectory(),"YourGym");
        if (file.isDirectory())
        {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++)
            {
                f.add(listFile[i].getAbsolutePath());
            }
        }
    }
}
