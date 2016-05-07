package com.timestamp.timestamp.tictactoe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private boolean won = false;
    private ImageView box1;
    private ImageView box2;
    private ImageView box3;
    private ImageView box4;
    private ImageView box5;
    private ImageView box6;
    private ImageView box7;
    private ImageView box8;
    private ImageView box9;
    private ArrayList<SquareInfo> board;
    private List<Integer> pieceSquares = new ArrayList<Integer>();

    private TextView gameInfo;
    private Button newGame;

    private Player player1;
    private Player player2;

    private String gameResult = "We have a winner!";
    private int turn = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        box1 = (ImageView) findViewById(R.id.box1);
        box2 = (ImageView) findViewById(R.id.box2);
        box3 = (ImageView) findViewById(R.id.box3);
        box4 = (ImageView) findViewById(R.id.box4);
        box5 = (ImageView) findViewById(R.id.box5);
        box6 = (ImageView) findViewById(R.id.box6);
        box7 = (ImageView) findViewById(R.id.box7);
        box8 = (ImageView) findViewById(R.id.box8);
        box9 = (ImageView) findViewById(R.id.box9);
        board = new ArrayList<SquareInfo>();
        board.add(new SquareInfo(1,box1.getId()));
        board.add(new SquareInfo(2,box2.getId()));
        board.add(new SquareInfo(3,box3.getId()));
        board.add(new SquareInfo(4,box4.getId()));
        board.add(new SquareInfo(5,box5.getId()));
        board.add(new SquareInfo(6,box6.getId()));
        board.add(new SquareInfo(7,box7.getId()));
        board.add(new SquareInfo(8,box8.getId()));
        board.add(new SquareInfo(9,box9.getId()));


        gameInfo = (TextView) findViewById(R.id.turn_info);
        newGame = (Button) findViewById(R.id.newGame);

        player1 = new Player("Player 1", (int) R.drawable.x);
        player2 = new Player("Player 2", (int) R.drawable.o);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(true);
            }
        });

        startGame(true);
        View.OnClickListener boxClicked = new View.OnClickListener() {
            @Override public void onClick(View v) {
                ImageView box = (ImageView) v;
                if (box.getDrawable()==null){
                    if (turn % 2 == 0) {
                        box.setImageResource(player1.getPiece());
                        addPiece(box.getId(),player1);
                        gameInfo.setText(player2.getName());
                        checkWin(player1, box.getId());
                    } else {
                        box.setImageResource(player2.getPiece());
                        addPiece(box.getId(),player2);
                        gameInfo.setText(player1.getName());
                        checkWin(player2, box.getId());
                    }
                    turn++;
                }
            }
        };

        box1.setOnClickListener(boxClicked);
        box2.setOnClickListener(boxClicked);
        box3.setOnClickListener(boxClicked);
        box4.setOnClickListener(boxClicked);
        box5.setOnClickListener(boxClicked);
        box6.setOnClickListener(boxClicked);
        box7.setOnClickListener(boxClicked);
        box8.setOnClickListener(boxClicked);
        box9.setOnClickListener(boxClicked);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void addPiece(Integer viewId, Player player){
        for(SquareInfo s: board){
            if(s.viewId == viewId){
                s.pieceId=player.getPiece();
                break;
            }
        }
    }

    public void checkWin(Player player, Integer viewId){
        //Top Row Horizontal
        won = false;
        List<Integer> pieceSquares = new ArrayList<Integer>();
        for(SquareInfo s: board){
            if(s.pieceId == player.getPiece()){
                pieceSquares.add(s.squareNum);
            }
        }
        //Top Row
        if(pieceSquares.contains(1) && pieceSquares.contains(2) && pieceSquares.contains(3)){
            won=true;
        }
        if(pieceSquares.contains(1) && pieceSquares.contains(4) && pieceSquares.contains(7)){
            won=true;
        }
        if(pieceSquares.contains(1) && pieceSquares.contains(5) && pieceSquares.contains(9)){
            won=true;
        }
        if(pieceSquares.contains(2) && pieceSquares.contains(5) && pieceSquares.contains(8)){
            won=true;
        }
        if(pieceSquares.contains(3) && pieceSquares.contains(6) && pieceSquares.contains(9)){
            won=true;
        }
        if(pieceSquares.contains(3) && pieceSquares.contains(5) && pieceSquares.contains(7)){
            won=true;
        }
        if(pieceSquares.contains(4) && pieceSquares.contains(5) && pieceSquares.contains(6)){
            won=true;
        }
        if(pieceSquares.contains(7) && pieceSquares.contains(8) && pieceSquares.contains(9)){
            won=true;
        }

        if(won==true){
            gameOver(player);
        }
    }
    public void gameOver(Player player){
        Intent intent = new Intent(MainActivity.this,Popup.class);
        intent.putExtra("player", player.getName());
        startActivity(intent);
        startGame(false);
    }
    public void startGame(boolean justLaunched) {
        box1.setImageDrawable(null);
        box2.setImageDrawable(null);
        box3.setImageDrawable(null);
        box4.setImageDrawable(null);
        box5.setImageDrawable(null);
        box6.setImageDrawable(null);
        box7.setImageDrawable(null);
        box8.setImageDrawable(null);
        box9.setImageDrawable(null);
        gameInfo.setText(player1.getName());
        for(SquareInfo s: board){
            s.pieceId=0;
        }
        pieceSquares = new ArrayList<Integer>();
        if(justLaunched == false){turn=-1;}else{turn=0;}
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.timestamp.timestamp.tictactoe/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.timestamp.timestamp.tictactoe/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
