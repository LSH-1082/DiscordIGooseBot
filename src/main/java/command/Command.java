package command;

import DAO.AttendanceDAO;
import DAO.UserDAO;
import DTO.UserDTO;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import util.ChangeTimeFormat;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Command extends ListenerAdapter {
    UserDAO userDAO = new UserDAO();
    AttendanceDAO attendanceDAO = new AttendanceDAO();
    ChangeTimeFormat changeTimeFormat = new ChangeTimeFormat();

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        String userId = event.getUser().getId();
        String command = event.getName();
        EmbedBuilder embed = new EmbedBuilder();

        switch (command){
            case "출근":
                if(attendanceDAO.getStatus(userId).equals("퇴근")){
                    attendanceDAO.changeStatus(userId, command);
                    UserDTO userDTO = userDAO.getUserInfo(userId);
                    embed.setTitle("출근")
                            .setDescription("### 아이구스 근태관리")
                            .addField("🙎출근자", event.getMember().getAsMention(), true)
                            .addField("⏰출근시간", changeTimeFormat.format(userDTO.getAttendanceTime()), true)
                            .setThumbnail(event.getUser().getAvatarUrl())
                            .setColor(Color.white);
                    event.replyEmbeds(embed.build()).queue();
                    break;
                }

                if(attendanceDAO.getStatus(userId).equals("출근")){
                    embed.setTitle("오류")
                            .setDescription("### 아이구스 근태관리")
                            .addField("⚠️오류", event.getMember().getAsMention() + "님은 이미 출근 중입니다!", true)
                            .setThumbnail(event.getUser().getAvatarUrl())
                            .setColor(Color.red);
                    event.replyEmbeds(embed.build()).queue();
                    break;
                }

                if(attendanceDAO.getStatus(userId).equals("false")){
                    userDAO.saveUser(userId);
                    UserDTO userDTO = userDAO.getUserInfo(userId);
                    embed.setTitle("출근")
                            .setDescription("### 아이구스 근태관리")
                            .addField("🙎출근자", event.getMember().getAsMention(), true)
                            .addField("⏰출근시간", changeTimeFormat.format(userDTO.getAttendanceTime()), true)
                            .setThumbnail(event.getUser().getAvatarUrl())
                            .setColor(Color.white);
                    event.replyEmbeds(embed.build()).queue();
                    break;
                }

                else{
                    event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();
                }

            case "퇴근":
                if(attendanceDAO.getStatus(userId).equals("출근")){
                    attendanceDAO.changeStatus(userId, command);
                    UserDTO userDTO = userDAO.getUserInfo(userId);
                    Duration duration = Duration.between(userDTO.getAttendanceTime().toLocalDateTime(), userDTO.getLeaveTime().toLocalDateTime());
                    embed.setTitle("퇴근")
                            .setDescription("### 아이구스 근태관리")
                            .addField("🙎퇴근자", event.getMember().getAsMention(), true)
                            .addField("⏰출근시간", changeTimeFormat.format(userDTO.getAttendanceTime()), true)
                            .addField("⏰퇴근시간", changeTimeFormat.format(userDTO.getLeaveTime()), true)
                            .addField("👍총근무시간", String.valueOf(duration.toHours()) + "시간" + String.valueOf(duration.toMinutes()) + "분", true)
                            .setThumbnail(event.getUser().getAvatarUrl())
                            .setColor(Color.white);
                    event.replyEmbeds(embed.build()).queue();
                    break;
                }

                if(attendanceDAO.getStatus(userId).equals("퇴근")){
                    embed.setTitle("오류")
                            .setDescription("### 아이구스 근태관리")
                            .addField("⚠️오류내용", event.getMember().getAsMention() + "님은 근무 중이 아닙니다!", true)
                            .setThumbnail(event.getUser().getAvatarUrl())
                            .setColor(Color.red);
                    event.replyEmbeds(embed.build()).queue();
                    break;
                }

                if(attendanceDAO.getStatus(userId).equals("false")){
                    event.reply("⚠️오류: 사용자 정보가 존재하지 않습니다 출근을 먼저 해주세요!").queue();
                }

                else{
                    event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();
                }
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
