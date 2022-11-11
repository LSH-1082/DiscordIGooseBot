package core;


//import listener.messageListener;

import listener.commandListener;
import listener.messageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import util.SECRET;
import command.*;

import javax.security.auth.login.*;

public class Main {
    JDABuilder builder = JDABuilder.createDefault(SECRET.TOKEN);
    public Main() throws LoginException{
        builder.setStatus(OnlineStatus.ONLINE);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_VOICE_STATES);
        builder.enableCache(CacheFlag.VOICE_STATE);
        addListener();
        addCommands();
        builder.build();
    }

    public static void main(String[] args) {
        try {
            Main b = new Main();
        } catch (LoginException e) {
            System.out.println("[Error] Login Error");
        }
    }

    public static void addCommands(){
        commandHandler.command.put("Ã»¼Ò", new cmdChatCleaner());
    }

    public void addListener(){
        //builder.addEventListeners(new voiceListener());
        builder.addEventListeners(new messageListener());
    }
}