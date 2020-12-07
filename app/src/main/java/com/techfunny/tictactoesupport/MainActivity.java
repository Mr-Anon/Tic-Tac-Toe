package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[] tapped = {0,0,0,0,0,0,0,0,0};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive = true;
    int activePlayer =0 ;

    public void appear(View view) {

        ImageView token = (ImageView) view;
        token.animate().alpha(0);

        int tappedToken= Integer.parseInt(token.getTag().toString());


        if (activePlayer==0 && tapped[tappedToken]==0 && gameIsActive){

            gameState[tappedToken]=activePlayer;
            token.setImageResource(R.drawable.zero);
            activePlayer=1;
        }
        else if(activePlayer==1 && tapped[tappedToken]==0 && gameIsActive){
            gameState[tappedToken]=activePlayer;
            token.setImageResource(R.drawable.cross);
            activePlayer=0;
        }
            token.animate().alpha(1).setDuration(250);

        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2 && gameIsActive){
                gameIsActive = false;
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                String winner=" " ;
//                Log.i("debug","i am here");

                if(activePlayer==1){
                    winner="Player 1";
                    Toast.makeText(this, "Player 1 has won!", Toast.LENGTH_SHORT).show();
                }
                else{
                    winner = "Player 2";
                    Toast.makeText(this, "Player 2 has won!", Toast.LENGTH_SHORT).show();
                }

                winnerTextView.setText(winner +" has won!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }
        }
        if( gameState[0]!=2 && gameState[1]!=2 && gameState[2]!=2 && gameState[3]!=2 && gameState[4]!=2 && gameState[5]!=2 && gameState[6]!=2 && gameState[7]!=2 && gameState[8]!=2 && gameIsActive  ) {
            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
            winnerTextView.setText(" It's a Tie!");
            Toast.makeText(this, "It's a Tie!", Toast.LENGTH_SHORT).show();
            playAgainButton.setVisibility(View.VISIBLE);
            winnerTextView.setVisibility(View.VISIBLE);
            gameIsActive = false;
        }

        tapped[tappedToken]=1;
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        for (int i=0;i<9;i++){
            gameState[i]=2;
            tapped[i]=0;
        }
        gameIsActive = true;
        activePlayer =0 ;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<9 ; i++){
            ImageView token = (ImageView) gridLayout.getChildAt(i);
            token.setImageDrawable(null);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}