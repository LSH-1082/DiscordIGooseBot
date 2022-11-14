package listener;

import core.commandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.STATIC;

public class commandListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().startsWith(STATIC.PREFIX) && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())){
            commandHandler.handlerCommand(commandHandler.parse.parser(event.getMessage().getContentRaw(), event));
        }
    }
}
