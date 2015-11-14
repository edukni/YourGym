package gymapp.android.com.yourgym.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import gymapp.android.com.yourgym.Model.Usuario;
import gymapp.android.com.yourgym.OnChangePage;
import gymapp.android.com.yourgym.R;

/**
 * Created by Cesar on 01/11/2015.
 */
public class FragmentRegister extends Fragment {
    private static final int RESULT_LOAD_IMAGE=1;
    private static final String SERVER_ADDRESS ="http://yourgym.site88.net/";
    Button bRegister;
    ImageView imageToUpload;
    EditText etName, etPassword, etAge;
    Context _context;
    String urlImageUsuario;
    OnChangePage listner;
    String nombre;
    String edad;
    String contrasenia;


    public  FragmentRegister( OnChangePage listner) {
        this.listner = listner;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup)inflater.inflate(R.layout.fragment_register, container, false);
        _context = container.getContext();
        imageToUpload= (ImageView) container.findViewById(R.id.imageToUpload);
        etName = (EditText) container.findViewById(R.id.etName);
        etAge = (EditText) container.findViewById(R.id.etAge);
        etPassword = (EditText) container.findViewById(R.id.etPassword);
        bRegister= (Button) container.findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=etName.getText().toString();
                Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
                new UploadImage(image,nombre).execute();
                SharedPreferences preferencias=getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putString("nombre", etName.getText().toString());
                editor.putString("edad", etAge.getText().toString());
                editor.putString("constrasenia", etPassword.getText().toString());
                editor.putString("imagen", urlImageUsuario);
                editor.commit();



                edad=etAge.getText().toString();
                contrasenia=etPassword.getText().toString();



                new SummaryAsyncTask().execute((Void) null);
                listner.changePage("Inicio", "http://yourgym.site88.net/loadInicio.php");

            }
        });
        imageToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
        return container;
    }

    private void registerUser (Usuario usuario){
        //ServerRequests serverRequests= new ServerRequests(this);
        //serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
        //    @Override
        //    public void done(User returnUser) {
        //        startActivity(new Intent(Register.this, Login.class));
        //    }
        //});
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Codigo = ",String.valueOf(resultCode));
        if (requestCode==RESULT_LOAD_IMAGE && resultCode==-1 && data !=null){
            Uri selectedImage= data.getData();
            imageToUpload.setImageURI(selectedImage);

            OutputStream out = null;
            String root = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
            File createDir = new File(root+"YourGym"+File.separator);
            if(!createDir.exists()) {
                createDir.mkdir();
                Log.e("", "" + createDir.getPath());
            }
            urlImageUsuario = root + "YourGym" + File.separator + (Calendar.getInstance()).get(Calendar.MILLISECOND) + ".png";

            File file = new File(root + "YourGym" + File.separator + (Calendar.getInstance()).get(Calendar.MILLISECOND) + ".png");
            Log.e("", "" + file.getPath());
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (out != null) {
                InputStream iStream = null;
                try {
                    iStream = _context.getContentResolver().openInputStream(selectedImage);
                    byte[] inputData = getBytes(iStream);
                    out.write(inputData);
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class UploadImage extends AsyncTask<Void,Void,Void> {
        Bitmap image;
        String name;

        public UploadImage(Bitmap image, String name){
            this.image=image;
            this.name= name;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(getActivity().getApplicationContext(), "Registration Complete", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);



            ArrayList<NameValuePair> dataToSend= new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image",encodedImage));
            dataToSend.add(new BasicNameValuePair("name",name));
            HttpParams httpRequestParams= getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post= new HttpPost(SERVER_ADDRESS + "SavePicture.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch (Exception e){
                e.printStackTrace();

            }
            return null;
        }
    }
    private HttpParams getHttpRequestParams(){
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 30);
        return httpRequestParams;
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    class SummaryAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private void postData(String name, String age, String password) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://yourgym.site88.net/Register.php");

            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("age", age));
                nameValuePairs.add(new BasicNameValuePair("password", password));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
            }
            catch(Exception e)
            {
                Log.e("log_tag", "Error:  "+e.toString());
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            postData(nombre, edad, contrasenia);
            return null;
        }
    }
}


