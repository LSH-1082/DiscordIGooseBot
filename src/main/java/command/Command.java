package command;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.WorkTimeService;

import java.util.ArrayList;
import java.util.List;


public class Command extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        String userId = event.getUser().getId();
        String command = event.getName();

        WorkTimeService attendanceService = new WorkTimeService();

        if(command.equals("출근")) attendanceService.attendance(userId, event);
        if(command.equals("퇴근")) attendanceService.leave(userId, event);
    }

    //서버채팅창에 "/"를 입력했을때 어떤 명령어가 사용가능한지 띄우는 기능
    public void onGuildReady(GuildReadyEvent event){
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("출근", "출근"));
        commandData.add(Commands.slash("퇴근", "퇴근"));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
