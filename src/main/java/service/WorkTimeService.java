package service;

import DAO.WorkTimeDAO;
import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import DTO.WorkTimeDTO;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import util.CreateEmbed;

public class WorkTimeService {
    EmployeeDAO employeeDAO = new EmployeeDAO();
    WorkTimeDAO workTimeDAO = new WorkTimeDAO();
    CreateEmbed embed = new CreateEmbed();


    public void attendance(String userId, SlashCommandInteractionEvent event){
        EmployeeDTO employeeDTO = employeeDAO.getEmployeeInfo(userId);

        if(employeeDTO == null)
            event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();

        else if(employeeDTO.getState().equals("false")){
            employeeDTO.setUserId(event.getUser().getId());
            employeeDTO.setName(event.getUser().getName());
            employeeDTO.setState("출근");
            employeeDTO.setImgUrl(event.getUser().getAvatarUrl());
            employeeDAO.insertEmployee(employeeDTO);
            workTimeDAO.insertAttendanceTime(userId);
            embed.attendanceEmbed(event);
        }

        else if(employeeDTO.getState().equals("퇴근")){
            employeeDTO.setName(event.getUser().getName());
            employeeDTO.setState("출근");
            employeeDTO.setImgUrl(event.getUser().getAvatarUrl());
            employeeDAO.updateEmployee(employeeDTO);
            workTimeDAO.insertAttendanceTime(userId);
            embed.attendanceEmbed(event);
        }

        else if(employeeDTO.getState().equals("출근")) embed.attendanceErrorEmbed(event);
    }

    public void leave(String userId, SlashCommandInteractionEvent event){
        EmployeeDTO employeeDTO = employeeDAO.getEmployeeInfo(userId);

        if(employeeDTO == null)
            event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();

        else if(employeeDTO.getState().equals("출근")){
            employeeDTO.setName(event.getUser().getName());
            employeeDTO.setState("퇴근");
            employeeDTO.setImgUrl(event.getUser().getAvatarUrl());
            employeeDAO.updateEmployee(employeeDTO);
            workTimeDAO.updateLeaveTime(userId);
            WorkTimeDTO workTimeDTO = workTimeDAO.getWorkTime(userId);
            if(workTimeDTO == null){
                event.reply("⚠️오류: 데이터베이스 연결 중 오류가 발생하였습니다!").queue();
                return;
            }
            embed.leaveEmbed(event, workTimeDTO);
        }

        else if(employeeDTO.getState().equals("퇴근")) embed.leaveErrorEmbed(event);

        else if(employeeDTO.getState().equals("false"))
            event.reply("⚠️오류: 사용자 정보가 존재하지 않습니다 출근을 먼저 해주세요!").queue();
    }
}
