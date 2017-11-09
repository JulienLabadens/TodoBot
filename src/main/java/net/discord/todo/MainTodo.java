package net.discord.todo;

import net.discord.todo.Commands;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class MainTodo {

    private MainTodo(String token) {
        Runtime runtime = Runtime.getRuntime();
        JDA jda;
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue veulliez verifier le token ou votre connection internet");
            return;
        } catch (RateLimitedException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Connecte avec: " + jda.getSelfUser().getName());
        int i;
        System.out.println("Le bot est autorisé sur " + (i = jda.getGuilds().size()) + " serveur" + (i > 1 ? "s" : ""));
        jda.addEventListener(new Commands());
        boolean stop = false;
        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            if (cmd.equalsIgnoreCase("stop")) {
                jda.shutdown();
                stop = true;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Veulliez indiquer le token du bot");
        }
        else{
            new MainTodo(args[0]);
        }
    }
}
