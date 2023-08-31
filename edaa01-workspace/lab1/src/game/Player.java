package game;

class Player {
    private String userId;

    Player(String user_ID) {
        this.userId = user_ID;
    }

    String getUserId() {
        return this.userId;
    }

    int takePins(Board board) {
        return 0;
    }
}
