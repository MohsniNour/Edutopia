/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author rayen
 */
public class Rating {

    private int id;
    private int id_item;
    private int id_rater;
    private int rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_rater() {
        return id_rater;
    }

    public void setId_rater(int id_rater) {
        this.id_rater = id_rater;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Rating(int id_item, int id_rater, int rate) {
        this.id_item = id_item;
        this.id_rater = id_rater;
        this.rate = rate;
    }

    public Rating(int id, int id_item, int id_rater, int rate) {
        this.id = id;
        this.id_item = id_item;
        this.id_rater = id_rater;
        this.rate = rate;
    }

    public Rating(int id_item, int rate) {
        this.id_item = id_item;
        this.rate = rate;
    }

    public Rating() {
    }
     
    

}
