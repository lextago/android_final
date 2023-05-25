package com.example.tagomovieapp;

public class UserData{
    private int num_tickets;
    private Double ticket_cost;
    private String movie_time;

    public UserData(){

    }
    public UserData(int num_tickets, Double ticket_cost, String movie_time) {
        this.num_tickets = num_tickets;
        this.ticket_cost = ticket_cost;
        this.movie_time = movie_time;
    }

    public int getNum_tickets() {
        return num_tickets;
    }

    public void setNum_tickets(int num_tickets) {
        this.num_tickets = num_tickets;
    }

    public Double getTicket_cost() {
        return ticket_cost;
    }

    public void setTicket_cost(Double ticket_cost) {
        this.ticket_cost = ticket_cost;
    }

    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }
}
