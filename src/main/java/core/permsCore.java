package core;


import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class permsCore {
    public static boolean check(MessageReceivedEvent event, Permission p) throws NullPointerException{
        if(event.getMember().hasPermission(p)) {
            return true;
        }
        else{
            event.getMessage().reply(":warning: 이 명령어를 사용할 권한이 없습니다").queue();
            return false;
        }
    }
}
