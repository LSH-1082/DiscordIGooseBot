//package TODO;
//
//public class chatCleaner_second_par {
//    if(args.length == 2){
//        String name = args[1].replaceAll("[@<>]", "");
//        String channelID = event.getChannel().getName();
//        Member member = event.getGuild().retrieveMemberById(name).complete();
//        String a = member.getId();
//        List<Message> message = member.getGuild().getTextChannelsByName(channelID, true).get(0).getHistory().retrievePast(count).complete();
//        channel.purgeMessages(message);
//        long time = System.currentTimeMillis();
//        channel.sendMessage(a).queue();
//        channel.sendMessage(event.getMember().getAsMention() + "�� " + (message.size() - 1) + "���� �޽����� �����Ǿ����ϴ�!").queue(response -> {
//                            response.editMessageFormat(event.getMember().getAsMention() + "�� " + (message.size() - 1) + "���� �޽����� �����Ǿ����ϴ�!    `%d ms`", System.currentTimeMillis() - time).queue();
//        });
//    }
//}
