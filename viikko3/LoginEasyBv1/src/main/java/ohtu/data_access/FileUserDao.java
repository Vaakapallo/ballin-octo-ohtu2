/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author lassi
 */

public class FileUserDao implements UserDao {

    private File file;
    private List<User> users;

    public FileUserDao(String filename) {
        this.file = new File(filename);
        users = new ArrayList();
        readFromFileToList(file);
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        try {
            FileWriter writer = new FileWriter(file);
            for (User user1 : users) {
                writer.append(user1.getUsername() + "\n");
                writer.append(user1.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("File is kaput");
        }
    }

    private void readFromFileToList(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String user = scanner.nextLine();
                String password = scanner.nextLine();
                users.add(new User(user, password));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Everything went wrong, the file is not there");
        }
    }
}
