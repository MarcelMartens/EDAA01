package game;

/** Superklass till spelarklasserna */
class Player {
    private String userId;

    Player(String user_ID) {
        this.userId = user_ID;
    }

    /** Returnar denna spelares ID */
    String getUserId() {
        return this.userId;
    }

    /** metod som overridas i subklasser. Returnar alltid 0 */
    int takePins(Board board) {
        return 0;
    }
}
