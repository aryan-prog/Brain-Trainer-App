package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    int locationOfCorrectAnswer;
    TextView resultText;
    int score=0;
    int numberOfQuestions=0;
    TextView scoreText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    MediaPlayer mplayer;
    TextView sum;
    TextView timerText;
    Button playAgainB;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers=new ArrayList<Integer>();

    public void chooseAnswer(View view)
    {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultText.setVisibility(View.VISIBLE);
            resultText.setText("Correct!");
            score++;
        }
        else
        {
            resultText.setVisibility(View.VISIBLE);
            resultText.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        timerText.setText("30s");
        playAgainB.setVisibility(View.INVISIBLE);
        resultText.setText("");
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        newQuestion();
        CountDownTimer count=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf((int) millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                MediaPlayer mplayer=MediaPlayer.create(getApplicationContext(),R.raw.alarm);
                mplayer.start();{
                    sleep(2000);
                    mplayer.stop();
                }
                timerText.setText("0s");
                resultText.setVisibility(View.VISIBLE);
                resultText.setText("Done!");
                playAgainB.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

            }
        }.start();
    }
    public void newQuestion(){

        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sum.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();

        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
                answers.add(a+b);
            else
            {
                int wronganswer=rand.nextInt(41);
                while(wronganswer==a+b)
                    wronganswer=rand.nextInt(41);

                answers.add(wronganswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        timerText.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        sum.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timer));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        sum=findViewById(R.id.question);
        button1=findViewById(R.id.answer1);
        button2=findViewById(R.id.answer2);
        button3=findViewById(R.id.answer3);
        button4=findViewById(R.id.answer4);
        resultText=findViewById(R.id.result);
        scoreText=findViewById(R.id.score);
        timerText =findViewById(R.id.timer);
        playAgainB=findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
   /* Runnable stopPlayerTask=new Runnable() {
        @Override
        public void run() {
            mplayer.pause();
        }
    };*/
}
