package game;

public class Player {
    private String userId;

    Player(String user_ID) {
        this.userId = user_ID;
    }

    public String getUserId() {
        return this.userId;
    }

    public int takePins(Board board) {

        return 0;
    }
}
