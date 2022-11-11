package command;

import core.permsCore;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class cmdChatCleaner implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(permsCore.check(event, Permission.MESSAGE_MANAGE)){
            try{
                MessageChannel channel = event.getChannel();
                if (Integer.parseInt(args[0]) > 99 || Integer.parseInt(args[0]) < 1) {
                    channel.sendMessage("!청소 1~99사이의 숫자를 입력하세요").queue();
                }
                else{
                    int count = Integer.parseInt(args[0]) + 1;
                    List<Message> message = channel.getHistory().retrievePast(count).complete();
                    channel.purgeMessages(message);
                    long time = System.currentTimeMillis();
                    channel.sendMessage(event.getMember().getAsMention() + "님 " + (message.size() - 1) + "개의 메시지가 삭제되었습니다!").queue(response -> {response.editMessageFormat(event.getMember().getAsMention() + "님 " + (message.size() - 1) + "개의 메시지가 삭제되었습니다!    `%d ms`", System.currentTimeMillis() - time).queue();
                    });
                }
            }

            catch(ArrayIndexOutOfBoundsException e){
                event.getChannel().sendMessage("!청소 1~99사이의 숫자를 입력하세요").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFO] " + event.getMember().getUser() + "가 메시지 제거 명령어를 사용하였습니다");
    }

    @Override
    public String help() {
        return null;
    }
}
