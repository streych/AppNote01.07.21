package com.example.note0107;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static androidx.core.content.ContentProviderCompat.requireContext;

public class MainActivity extends AppCompatActivity {

    private final NotesRepository repository = NotesFirebaseRepository.INSTANCE;
    //private final Adapter notesAdapter = new Adapter(this);

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        router = new Router(getSupportFragmentManager());
        if (savedInstanceState == null){
            //router.showAuth();
            router.showNotes();
        }

        /*getSupportFragmentManager().setFragmentResultListener(AuthFragment.AUTH_RESULT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey,
                                         @NonNull Bundle result) {
                router.showNotes();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_add:

                View view = LayoutInflater.from(this).inflate(R.layout.add_layout,  null, false);

                EditText editTitle = view.findViewById(R.id.input_titleD);
                EditText editDesc = view.findViewById(R.id.input_descriptionD);

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                String date = format.format(Calendar.getInstance().getTime());

                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle(R.string.titleAddDialog)
                        .setView(view)
                        .setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                repository.add(String.valueOf(editTitle.getText()), date, String.valueOf(editDesc.getText()), new CallBack<Note>() {
                                    @Override
                                    public void onSucess(Note result) {

                                    }
                                });
                                router.showNotes();
                            }
                        });
                builder.show();
                //router.showAdd();
                return true;
            case R.id.about:
                router.showInfo();
                return true;
            case R.id.settings:
                router.showSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}