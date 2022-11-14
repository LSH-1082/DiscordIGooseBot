package listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import static listener.commandListener.onCommandReceived;

public class messageListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event){
        String msg = event.getMessage().getContentRaw();
        if(!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())){
            event.getGuild().getTextChannelsByName("chat_log", true).get(0).sendMessage(event.getMember().getUser() + event.getChannel().getAsMention() + ": " + msg).queue();
        }
    }
}
