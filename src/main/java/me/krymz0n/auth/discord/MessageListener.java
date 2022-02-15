package me.krymz0n.auth.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        Message msg = evt.getMessage();
        User user = evt.getAuthor();

        if (msg.equals("-reset nick")) {
            evt.getChannel().sendMessage("Okay");
        }
    }
}
