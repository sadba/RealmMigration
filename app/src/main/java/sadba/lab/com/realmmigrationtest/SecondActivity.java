package sadba.lab.com.realmmigrationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import sadba.lab.com.realmmigrationtest.model.Person;
import sadba.lab.com.realmmigrationtest.realmmigration.PersonData;
import sadba.lab.com.realmmigrationtest.realmmigration.UserData;

public class SecondActivity extends AppCompatActivity {

    EditText secondNumero;
    EditText secondTel;
    EditText secondCouleur;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_second);
        toolbar.setTitle("Realm Suite");
        setSupportActionBar(toolbar);

        secondNumero = findViewById(R.id.et_numero);
        secondTel = findViewById(R.id.et_tel);
        secondCouleur = findViewById(R.id.et_couleur);

        Realm.init(this);
        // Realm realm;
        try{
            realm = Realm.getDefaultInstance();

        }catch (Exception e){

            // Get a Realm instance for this thread
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);

        }



        PersonData data = realm.where(PersonData.class).findFirst();

        if (null != data) {

            data = realm.copyFromRealm(data);

            final Person person = new Person();
            person.setNumero(data.getNumero());
            person.setTel(data.getTel());
            person.setCouleur(data.getCouleur());

            Toast.makeText(this, String.format("Numero: %s, Tel: %s Couleur: %s", person.getNumero(), person.getTel(), person.getCouleur()), Toast.LENGTH_LONG).show();
        }

        Button button = (Button) findViewById(R.id.btn_second);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String numero = secondNumero.getText().toString();
                final String tel = secondTel.getText().toString();
                final String couleur = secondCouleur.getText().toString();

                if (!TextUtils.isEmpty(numero) && !TextUtils.isEmpty(tel)) {
                    final Person person = new Person();
                    person.setNumero(numero);
                    person.setTel(tel);
                    person.setCouleur(couleur);

                    //final Realm realm = Realm.getDefaultInstance();

                    //Realm.init(this);
                    // Realm realm;
                    try{
                        realm = Realm.getDefaultInstance();

                    }catch (Exception e){

                        // Get a Realm instance for this thread
                        RealmConfiguration config = new RealmConfiguration.Builder()
                                .deleteRealmIfMigrationNeeded()
                                .build();
                        realm = Realm.getInstance(config);

                    }

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(final Realm realm) {
                            final PersonData personData = new PersonData();
                            personData.fill(person);

                            realm.insertOrUpdate(personData);
                        }
                    });

                    realm.close();

                    // Toast.makeText(this, String.format("Email: %s, Name: %s and Age: %s", user.getEmail(), user.getName(), user.getAge()), Toast.LENGTH_LONG).show();

                    Toast.makeText(SecondActivity.this, String.format("Numero: %s, Tel: %s Couleur: %s", person.getNumero(), person.getTel(), person.getCouleur()), Toast.LENGTH_SHORT).show();
                   // Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    //startActivity(i);
                }

            }
        });
    }
}
