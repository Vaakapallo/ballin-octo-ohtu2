/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.Commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.model.User;

/**
 *
 * @author lassi
 */
public abstract class Command {

    protected Scanner scanner;
    protected EbeanServer server;
    protected User user;

    public Command(Scanner lukija, EbeanServer server, User user) {
        this.scanner = lukija;
        this.server = server;
        this.user = user;
    }

    public abstract void run();
}
