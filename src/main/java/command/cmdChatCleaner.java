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
                    channel.sendMessage("!û�� 1~99������ ���ڸ� �Է��ϼ���").queue();
                }
                else{
                    int count = Integer.parseInt(args[0]) + 1;
                    List<Message> message = channel.getHistory().retrievePast(count).complete();
                    channel.purgeMessages(message);
                    long time = System.currentTimeMillis();
                    channel.sendMessage(event.getMember().getAsMention() + "�� " + (message.size() - 1) + "���� �޽����� �����Ǿ����ϴ�!").queue(response -> {response.editMessageFormat(event.getMember().getAsMention() + "�� " + (message.size() - 1) + "���� �޽����� �����Ǿ����ϴ�!    `%d ms`", System.currentTimeMillis() - time).queue();
                    });
                }
            }

            catch(ArrayIndexOutOfBoundsException e){
                event.getChannel().sendMessage("!û�� 1~99������ ���ڸ� �Է��ϼ���").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFO] " + event.getMember().getUser() + "�� �޽��� ���� ��ɾ ����Ͽ����ϴ�");
    }

    @Override
    public String help() {
        return null;
    }
}
