package main;

import graphics.ViewController;
import team.Team;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends WindowAdapter {


    @Override
    public void windowClosed(WindowEvent e) {

        Team t = new Team(ViewController.instance().getParams());
        ViewController.instance().createTeam(t);
    }
}
