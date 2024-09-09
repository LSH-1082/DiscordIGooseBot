package service;

import DAO.AttendanceDAO;
import DAO.UserDAO;
import DTO.UserDTO;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import util.ChangeTimeFormat;

import java.awt.*;
import java.time.Duration;

public class AttendanceService {
    UserDAO userDAO = new UserDAO();
    AttendanceDAO attendanceDAO = new AttendanceDAO();
    ChangeTimeFormat changeTimeFormat = new ChangeTimeFormat();
    EmbedBuilder embed = new EmbedBuilder();

    public void attendance(String userId, SlashCommandInteractionEvent event){
        if(attendanceDAO.getStatus(userId).equals("퇴근")){
            attendanceDAO.changeStatus(userId, "출근");
            UserDTO userDTO = userDAO.getUserInfo(userId);
            embed.setTitle("출근")
                    .setDescription("### 아이구스 근태관리")
                    .addField("🙎출근자", event.getMember().getAsMention(), true)
                    .addField("⏰출근시간", changeTimeFormat.format(userDTO.getAttendanceTime()), true)
                    .setThumbnail(event.getUser().getAvatarUrl())
                    .setColor(Color.white);
            event.replyEmbeds(embed.build()).queue();
        }

        if(attendanceDAO.getStatus(userId).equals("출근")){
            embed.setTitle("오류")
                    .setDescription("### 아이구스 근태관리")
                    .addField("⚠️오류", event.getMember().getAsMention() + "님은 이미 출근 중입니다!", true)
                    .setThumbnail(event.getUser().getAvatarUrl())
                    .setColor(Color.red);
            event.replyEmbeds(embed.build()).queue();
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
        }

        else{
            event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();
        }
    }


    public void leave(String userId, SlashCommandInteractionEvent event){
        if(attendanceDAO.getStatus(userId).equals("출근")){
            attendanceDAO.changeStatus(userId, "퇴근");
            UserDTO userDTO = userDAO.getUserInfo(userId);
            Duration duration = Duration.between(userDTO.getAttendanceTime().toLocalDateTime(), userDTO.getLeaveTime().toLocalDateTime());
            Long hour = duration.toHours();
            int minute = duration.toMinutesPart();

            embed.setTitle("퇴근")
                    .setDescription("### 아이구스 근태관리")
                    .addField("🙎퇴근자", event.getMember().getAsMention(), true)
                    .addField("⏰출근시간", changeTimeFormat.format(userDTO.getAttendanceTime()), true)
                    .addField("⏰퇴근시간", changeTimeFormat.format(userDTO.getLeaveTime()), true)
                    .addField("👍총근무시간", String.valueOf(hour) + "시간 " + String.valueOf(minute) + "분", true)
                    .setThumbnail(event.getUser().getAvatarUrl())
                    .setColor(Color.white);
            event.replyEmbeds(embed.build()).queue();
        }

        if(attendanceDAO.getStatus(userId).equals("퇴근")){
            embed.setTitle("오류")
                    .setDescription("### 아이구스 근태관리")
                    .addField("⚠️오류내용", event.getMember().getAsMention() + "님은 근무 중이 아닙니다!", true)
                    .setThumbnail(event.getUser().getAvatarUrl())
                    .setColor(Color.red);
            event.replyEmbeds(embed.build()).queue();
        }

        if(attendanceDAO.getStatus(userId).equals("false")){
            event.reply("⚠️오류: 사용자 정보가 존재하지 않습니다 출근을 먼저 해주세요!").queue();
        }

        else{
            event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();
        }
    }
}
