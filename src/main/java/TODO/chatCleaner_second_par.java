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
//        channel.sendMessage(event.getMember().getAsMention() + "님 " + (message.size() - 1) + "개의 메시지가 삭제되었습니다!").queue(response -> {
//                            response.editMessageFormat(event.getMember().getAsMention() + "님 " + (message.size() - 1) + "개의 메시지가 삭제되었습니다!    `%d ms`", System.currentTimeMillis() - time).queue();
//        });
//    }
//}
