package command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Command extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        String command = event.getName();
        EmbedBuilder embed = new EmbedBuilder();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh시 mm분");
        now.format(formatter);
        switch (command){
            case "출근":
                embed.setTitle("출근")
                        .setDescription("### 아이구스 근태관리")
                        .addField("출근자🙎", event.getMember().getAsMention(), true)
                        .addField("출근시간⏰", now.format(formatter), true)
                        .setThumbnail(event.getUser().getAvatarUrl())
                        .setColor(Color.white);
                event.replyEmbeds(embed.build()).queue();
                break;
            case "퇴근":
                embed.setTitle("퇴근")
                        .setDescription("### 아이구스 근태관리")
                        .addField("퇴근자🙎", event.getMember().getAsMention(), true)
                        .addField("퇴근시간⏰", now.format(formatter), true)
                        .setThumbnail(event.getUser().getAvatarUrl())
                        .setColor(Color.white);
                event.replyEmbeds(embed.build()).queue();
                break;
        }
    }

    //서버채팅창에 "/"를 입력했을때 어떤 명령어가 사용가능한지 띄우는 기능
    public void onGuildReady(GuildReadyEvent event){
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("출근", "출근"));
        commandData.add(Commands.slash("퇴근", "퇴근"));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
