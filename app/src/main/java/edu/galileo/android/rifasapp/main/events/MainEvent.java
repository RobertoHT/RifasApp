package edu.galileo.android.rifasapp.main.events;

import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by praxis on 11/07/16.
 */
public class MainEvent {
    private int type;
    private Rifa rifa;
    private String error;

    public final static int SAVE_EVENT = 0;
    public final static int READ_EVENT = 1;
    public final static int DELETE_EVENT = 2;
    public final static int ERROR_EVENT = 3;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Rifa getRifa(){
        return rifa;
    }

    public void setRifa(Rifa rifa){
        this.rifa = rifa;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
