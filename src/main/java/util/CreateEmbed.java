package util;

import DTO.WorkTimeDTO;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class CreateEmbed {
    EmbedBuilder embed = new EmbedBuilder();
    ChangeTimeFormat changeTimeFormat = new ChangeTimeFormat();

    public void attendanceEmbed(SlashCommandInteractionEvent event) {
        embed.setTitle("출근")
                .setDescription("### 아이구스 근태관리")
                .addField("🙎출근자", event.getMember().getAsMention(), true)
                .addField("⏰출근시간", changeTimeFormat.format(Timestamp.valueOf(LocalDateTime.now())), true)
                .setThumbnail(event.getUser().getAvatarUrl())
                .setColor(Color.white);
        event.replyEmbeds(embed.build()).queue();
    }

    public void leaveEmbed(SlashCommandInteractionEvent event, WorkTimeDTO workTimeDTO) {
        Duration duration = Duration.between(workTimeDTO.getAttendanceTime().toLocalDateTime(), workTimeDTO.getLeaveTime().toLocalDateTime());
        Long hour = duration.toHours();
        int minute = duration.toMinutesPart();
        embed.setTitle("퇴근")
                .setDescription("### 아이구스 근태관리")
                .addField("🙎퇴근자", event.getMember().getAsMention(), true)
                .addField("⏰출근시간", changeTimeFormat.format(workTimeDTO.getAttendanceTime()), true)
                .addField("⏰퇴근시간", changeTimeFormat.format(workTimeDTO.getLeaveTime()), true)
                .addField("👍총근무시간", String.valueOf(hour) + "시간 " + String.valueOf(minute) + "분", true)
                .setThumbnail(event.getUser().getAvatarUrl())
                .setColor(Color.white);
        event.replyEmbeds(embed.build()).queue();
    }

    public void attendanceErrorEmbed(SlashCommandInteractionEvent event) {
        embed.setTitle("오류")
                .setDescription("### 아이구스 근태관리")
                .addField("⚠️오류", event.getMember().getAsMention() + "님은 이미 출근 중입니다!", true)
                .setThumbnail(event.getUser().getAvatarUrl())
                .setColor(Color.red);
        event.replyEmbeds(embed.build()).queue();
    }

    public void leaveErrorEmbed(SlashCommandInteractionEvent event) {
        embed.setTitle("오류")
                .setDescription("### 아이구스 근태관리")
                .addField("⚠️오류내용", event.getMember().getAsMention() + "님은 근무 중이 아닙니다!", true)
                .setThumbnail(event.getUser().getAvatarUrl())
                .setColor(Color.red);
        event.replyEmbeds(embed.build()).queue();
    }
}
