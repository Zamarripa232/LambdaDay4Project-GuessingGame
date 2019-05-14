package us.floydasaur.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final int CORRECT = 0;
    static final int LOW = 1;
    static final int HIGH = 2;
    static final int RESET = 3;

    static Random      random = new Random();
    static final int SECRETNUMBER = random.nextInt(101);

    EditText           editGuess;
    Button             buttonGuess, buttonReset;
    TextView           textLastGuess;
    TextView           textResponse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editGuess = findViewById(R.id.edit_guess);
        buttonGuess = findViewById(R.id.button_guess);
        buttonReset = findViewById(R.id.button_reset);
        textLastGuess = findViewById(R.id.text_last_guess);
        textResponse = findViewById(R.id.text_response);

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guessString = editGuess.getText().toString();
                if (guessString.matches("")){
                    textResponse.setText("Please input an int 0-100");
                } else {
                    int guessInt = Integer.parseInt(guessString);
                    textLastGuess.setText(guessString);
                    updateUI(checkGuess(guessInt));
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI(RESET);
            }
        });
    }

    int checkGuess(int guess){
        if (guess == SECRETNUMBER){
            return CORRECT;
        } else {
            return guess < SECRETNUMBER ? LOW : HIGH;
        }
    }

    void updateUI(int result) {
        switch(result){
            case CORRECT:
                textResponse.setText("Good Job!");
                break;
            case LOW:
                textResponse.setText("Too low!");
                break;
            case HIGH:
                textResponse.setText("Too high!");
                break;
            case RESET:
                editGuess.setHint("Enter your guess! 0-100");
                editGuess.setText("");
                textResponse.setText("I'll let you know if it's too high or low here!");
                textLastGuess.setText("0");
                break;
            default:
                textResponse.setText("This shouldn't be happening.");
        }
    }

}
