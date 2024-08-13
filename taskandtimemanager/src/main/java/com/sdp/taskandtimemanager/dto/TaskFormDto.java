package com.sdp.taskandtimemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskFormDto {

    private String taskname;
    private String taskdescription;
    private String taskpriority;
    private String taskstatus;
    private Long assignedto;
    private Long projectid;

}