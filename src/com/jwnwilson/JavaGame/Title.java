package com.jwnwilson.JavaGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.content.Context;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by noelwilson on 06/01/2016.
 */
public class Title extends Activity {
    /** Called when the activity is first created. */
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        final Context context = this;

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main.class);
                startActivity(intent);
            }
        });
    }
}
