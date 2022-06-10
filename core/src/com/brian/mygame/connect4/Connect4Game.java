package com.brian.mygame.connect4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brian.mygame.GameEndScreen;
import com.brian.mygame.MyGame;

public class Connect4Game implements Screen {
    final MyGame game;

    Texture emptyBox;
    Texture redPiece;
    Texture yellowPiece;
    Texture logo;
    OrthographicCamera camera;

    private final int columns;//6
    private final int rows; //7
    private int[][] boardArray;
    private final int squarewidth; //pixels
    private final int squareheight; //pixels
    private final int boardWidth; //pixels
    private final int boardHeight; //pixels
    private int currentPlayer;
    private Texture currentChip;

    private Array<Chip> chipPool;
    private Array<Rectangle> redchipPool;
    private Array<Rectangle> yellowchipPool;



    public Connect4Game(final MyGame game) {
        this.game = game;

        // load the images for the droplet and the bucket, 64x64 pixels each
        emptyBox = new Texture(Gdx.files.internal("Connect4Assets/blussy.png"));
        redPiece = new Texture(Gdx.files.internal("Connect4Assets/red.png"));
        yellowPiece = new Texture(Gdx.files.internal("Connect4Assets/yellow.png"));
        logo = new Texture(Gdx.files.internal("Connect4Assets/logo.png"));

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 900, 600);

        this.columns = 7;
        this.rows = 6;
        this.squarewidth = 100;
        this.squareheight = 100;
        this.boardWidth = 700;
        this.boardHeight = 600;
        this.boardArray = new int[rows][columns];
        constructBoardArray();

        chipPool = new Array<Chip>();
        redchipPool = new Array<Rectangle>();
        yellowchipPool = new Array<Rectangle>();

        this.currentPlayer = 1;

    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0.9f, 1f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

            drawBoard(emptyBox);
            game.batch.draw(logo,735, 180, 133, 400 );
            readyChip();
            boardClick();

//            for (Chip chip : chipPool) {
//                game.batch.draw(chip.getTexture(), chip.getRect().x+18, chip.getRect().y+20);
//            }

            for (Rectangle chip : redchipPool) {
                game.batch.draw(redPiece, chip.x +18, chip.y +20);
            }

            for (Rectangle chip : yellowchipPool) {
                game.batch.draw(yellowPiece, chip.x +18, chip.y +20);
            }

        game.batch.end();


    }

    //RENDER METHODS FOR THE VISUAL BOARD
    public void drawBoard(Texture texture){
        //draws all 42 boxes onto the 700*600 pixel screen
        for (int x = 0; x < 700; x+= 100) {
            for (int y = 0; y < 600; y+= 100) {
                game.batch.draw(texture, x, y, squarewidth,squareheight);
            }
        }
    }

    public void readyChip(){
        //show the chip in play
        if(currentPlayer == 1){
            currentChip = redPiece;
            game.font.draw(game.batch, "red goes next", 735, 120);
        }
        else
        {
            currentChip = yellowPiece;
            game.font.draw(game.batch, "yellow goes next", 735, 120);
        }

        game.batch.draw(currentChip, 750, 30);

    }

    private void spawnChip(int x, int y) {

        Rectangle rect = new Rectangle(y * 100f, x*100f, 100f, 100f);
        if (currentPlayer == 1){
//            RedChip redChip = new RedChip(x, y);
            redchipPool.add(rect);
        }
        else {
            yellowchipPool.add(rect);
        }
    }

    //BACKGROUND LOGIC TO PROCESS THE GAME
    public void boardClick(){

        int columnClicked = -1;

        if (Gdx.input.justTouched()) {
            columnClicked = findMouseOnColumn();

            for(int i = 0; i<rows; i++){

                if (boardArray[i][columnClicked] == 0){

                    boardArray[i][columnClicked] = currentPlayer;
                    spawnChip(i, columnClicked);
                    System.out.println("row " + i + " column " + columnClicked);
                    System.out.println(toString());

                if (!checkForWinner(new Vector2(i,columnClicked))){

                    togglePlayer();
                    readyChip();
                }
                else{
                    //load win scene
                    System.out.println("you win");
                    game.setScreen(new GameEndScreen(game));
                    dispose();
                }
                    break;
                }
            }
        }
    }

    public void constructBoardArray() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boardArray[i][j] = 0;
            }
        }
    }

    public int findMouseOnColumn(){
        //find which column the mouse is hovering over
        Vector2 touchPos = new Vector2();

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        }

        if(touchPos.x < 0){
            return 0;
        }
        else if(touchPos.x > boardWidth){
            return columns - 1;
        }
        else{
            return (int)(touchPos.x/100);
        }
    }

    public void togglePlayer(){
        if(currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }

    }


    //CHECK WIN CONDITIONS
    private boolean checkForWinner(Vector2 v2){
        if(verticalCheck(v2))return true;
        if(horizontalCheck(v2))return true;
        if(leftDiagonalCheck(v2))return true;
        if(rightDiagonalCheck(v2))return true;
        return false;
    }

    private boolean leftDiagonalCheck(Vector2 v2) {
        int row = (int)v2.x + 1;
        int col = (int)v2.y - 1;

        if (row < 0 || col < 0)
        {
            return false;
        }

        int player = boardArray[row -1][col +1];
        int count = 1;

        while (row < 6 && col >= 0)
        {
            if (boardArray[row][col] == player)
            {
                count++;
                row++;
                col--;
            }
            else
            {
                break;
            }
        }

        row = (int)v2.x - 1;
        col = (int)v2.y + 1;

        while (row >= 0 && col < 7)
        {
            if (boardArray[row +1][col -1] == player)
            {
                count++;
                row--;
                col++;
            }
            else
            {
                break;
            }
        }

        if(count >=4)
        {
            System.out.print("rdia win with " +  count +", player " + player);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean rightDiagonalCheck(Vector2 v2) {
        int player = boardArray[(int)v2.x][(int)v2.y];

        int row = (int)v2.x - 1;
        int col = (int)v2.y - 1;

        if (row < 0 || col < 0)
        {
            return false;
        }


        int count = 1;

        while (row >= 0 && col >= 0)
        {
            if (boardArray[row][col] == player)
            {
                count++;
                row--;
                col--;
            }
            else
            {
                break;
            }
        }

        row = (int)v2.x + 1;
        col = (int)v2.y + 1;

//        if (row > rows && col > columns){
//            return false;
//        }

        while (row < rows && col < columns)
        {
            if (boardArray[row][col] == player)
            {
                count++;
                row++;
                col++;
            }
            else
            {
                break;
            }
        }

        if(count >=4)
        {
            System.out.print("leftdia win, ");
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean horizontalCheck(Vector2 v2) {
        int row = (int)v2.x;
        int col = (int)v2.y;

        int player = boardArray[row][col];
        int count = 1;

        for(int i = col-1; i>=0; i--)
        {
            if(boardArray[row][i] != player)
            {
                break;
            }
            count++;
        }

        for(int j = col+1; j<columns; j++)
        {
            if(boardArray[row][j] != player)
            {
                break;
            }
            count++;
        }

        if(count == 4){
            System.out.print("hori win, ");
            return true;
        }
        else{
            return false;
        }

    }

    private boolean verticalCheck(Vector2 v2) {
        int row = (int)v2.x;
        int col = (int)v2.y;

        if (row < 3)
        {
            return false;
        }

        int player = boardArray[row][col];

        for (int i = row-1; i >= row - 3; i--)
        {
            if (boardArray[i][col] != player)
            {
                System.out.print("no v win, ");
                return false;
            }
        }
        System.out.print("vert win, player: "+ player);
        return true;
    }

    public String toString(){

        String board = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                board += "" + boardArray[i][j] + " ";
            }

            board += "\n";
        }

        return board;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }





}
