package ece558.pdx.edu.polyr0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageButton mTalkButton;
    private TextView mPhraseText;
    private String[] mPhrases = {
            "Andy wants a cracker",
            "*SQUAWK* Pretty Andy Pretty Andy*AWRP*",
            "Arrrrr...Shiver me timbers!",
            "Take my loot, feel my boot",
            "Let's Party!!!",
            "Give me Python or give me death",
            "I'm not that kind of parrot",
            "No pity from this city...RCTID",
            "Go Thorns",
            "Go Timbers",
            "How about them Blazers",
            "B.A.O.N",
            "Have a hoppin' time - GO HOPs"

    };

    private Random mRndmInt = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTalkButton = (ImageButton) findViewById(R.id.talkButton);
        ButtonHandler bh = new ButtonHandler();
        mTalkButton.setOnClickListener(bh);

        mPhraseText = (TextView) findViewById(R.id.phraseTextView);
        updatePhrase();

    }

    private void updatePhrase() {
        mPhraseText.setText(mPhrases[mRndmInt.nextInt(mPhrases.length)]);
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            updatePhrase();
        }
    }

}

