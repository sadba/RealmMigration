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
import sadba.lab.com.realmmigrationtest.model.User;
import sadba.lab.com.realmmigrationtest.realmmigration.RealmMigrations;
import sadba.lab.com.realmmigrationtest.realmmigration.UserData;

public class MainActivity extends AppCompatActivity {

    EditText etMainEmail;
    EditText etMainName;
    EditText etMainAge;
    EditText etMainAdresse;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("Realm");
        setSupportActionBar(toolbar);

        etMainEmail = (EditText) findViewById(R.id.et_main_email);
        etMainName = (EditText) findViewById(R.id.et_main_name);
        etMainAge = (EditText) findViewById(R.id.et_main_age);
        etMainAdresse = (EditText) findViewById(R.id.et_main_dresse);

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

        UserData data = realm.where(UserData.class).findFirst();

        if (null != data) {
            data = realm.copyFromRealm(data);

            final User user = new User();
            user.setEmail(data.getEmail());
            user.setName(data.getName());
            user.setAge(data.getAge());
            user.setAdresse(data.getAdresse());

            Toast.makeText(this, String.format("Email: %s, Name: %s and Age: %s, Adresse: %s", user.getEmail(), user.getName(), user.getAge(),user.getAdresse()), Toast.LENGTH_LONG).show();
        }

        Button button = (Button) findViewById(R.id.btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String email = etMainEmail.getText().toString();
                final String name = etMainName.getText().toString();
                final int age = Integer.parseInt(etMainAge.getText().toString());
                final String adresse = etMainAdresse.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(name)) {
                    final User user = new User();
                    user.setEmail(email);
                    user.setName(name);
                    user.setAge(age);
                    user.setAdresse(adresse);

                    //final Realm realm = Realm.getDefaultInstance();
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
                            final UserData userData = new UserData();
                            userData.fill(user);

                            realm.insertOrUpdate(userData);
                        }
                    });

                    realm.close();

                   // Toast.makeText(this, String.format("Email: %s, Name: %s and Age: %s", user.getEmail(), user.getName(), user.getAge()), Toast.LENGTH_LONG).show();

                    Toast.makeText(MainActivity.this, String.format("Email: %s, Name: %s and Age: %s Adresse: %s", user.getEmail(), user.getName(), user.getAge(),user.getAdresse()), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(i);
                }

            }
        });
    }
}
