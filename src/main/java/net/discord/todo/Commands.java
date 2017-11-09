package net.discord.todo;

import net.discord.todo.ReferenceDiscord;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Commands extends ListenerAdapter {

    private ArrayList<String> Todo = new ArrayList<>();
    private String fileName = "Agenda.txt";


    public Commands() {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line =bufferedReader.readLine())!=null){
                Todo.add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    private ReferenceDiscord Prefixe = new ReferenceDiscord("!");
    private ReferenceDiscord ClientID = new ReferenceDiscord("376460624487514113");
    private ReferenceDiscord Clement = new ReferenceDiscord("<@212302105149571072>");
    private ReferenceDiscord Shipenton = new ReferenceDiscord("<@196322917242503168>");
    private ReferenceDiscord Costa = new ReferenceDiscord("<@253290055173537794>");
    private ReferenceDiscord Remi = new ReferenceDiscord("<@208704877625344000>");
    private ReferenceDiscord Pierre = new ReferenceDiscord("<@131170813444358144>");
    private ReferenceDiscord Kenny = new ReferenceDiscord("<@290067503147909120>");
    private ReferenceDiscord Xavier = new ReferenceDiscord("<@239718582244605953>");


    private List<ReferenceDiscord> TableauRef = new ArrayList<>();


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        String[] command =event.getMessage().getContent().split(" ");

        TableauRef.add(Prefixe);
        TableauRef.add(ClientID);
        TableauRef.add(Clement);
        TableauRef.add(Shipenton);
        TableauRef.add(Costa);
        TableauRef.add(Remi);
        TableauRef.add(Pierre);
        TableauRef.add(Kenny);
        TableauRef.add(Xavier);

        if (!command[0].startsWith(Prefixe.reference)) return;

        if (command[0].equalsIgnoreCase("!ping")){
            String msg = "Pong! `" + event.getJDA().getPing() +"ms`";
            event.getChannel().sendMessage(msg).queue();
        }

        if (command[0].equalsIgnoreCase("!shrug")){
            String msg = "```¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯ ¯\\_(ツ)_/¯```";
            event.getChannel().sendMessage(msg).queue();
        }
        
        if (command.length >= 2 && command[0].equalsIgnoreCase("!addtodo")){
            String msg;
            StringBuilder msgb = new StringBuilder(200);
            msgb.append("[+] ");
            for (int i=1;i<command.length;i++) {
                msgb.append(command[i]);
                msgb.append(" ");
            }
            msg = msgb.toString();
            Todo.add(msg);
            event.getChannel().sendMessage("Ajouté -> " + msg).queue();
        }

        if (command[0].equalsIgnoreCase("!todo")){
            if (Todo.size()== 0) {
                event.getChannel().sendMessage("Rien a faire!").queue();
            } else {
                for (String s : Todo) {
                    event.getChannel().sendMessage(s).queue();
                }
            }
        }


        if (command[0].equalsIgnoreCase("!save")){

            try {
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                for (String s :
                        Todo) {
                    bufferedWriter.write(s+"\n");
                }
                bufferedWriter.close();

                event.getChannel().sendMessage("Saved!").queue();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
