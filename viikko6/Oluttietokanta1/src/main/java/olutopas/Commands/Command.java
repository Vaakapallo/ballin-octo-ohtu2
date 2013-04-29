/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public abstract class Command {

    protected Scanner scanner;
    protected Datamapper mapper;

    public Command(Scanner scanner, Datamapper mapper) {
        this.scanner = scanner;
        this.mapper = mapper;
    }

    public abstract void run();
}
