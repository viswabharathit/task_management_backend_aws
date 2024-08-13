package com.sdp.taskandtimemanager.model;

// import java.time.LocalDate;

import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.ToString;

@Entity
@ToString
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "taskid")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskid;
    private String taskname;
    private String taskdescription;
    private String taskstatus;
    private String taskpriority;
    // private LocalDate duedate;
    // private Boolean assignedstatus = false;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectid")
    // @Column(name = "projectid")
    private Projects project;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @JoinColumn(name = "assignedto")
    // @JsonIgnore
    private Users member;

    public Tasks() {
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdescription() {
        return taskdescription;
    }

    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getTaskpriority() {
        return taskpriority;
    }

    public void setTaskpriority(String taskpriority) {
        this.taskpriority = taskpriority;
    }

    // public LocalDate getDuedate() {
    // return duedate;
    // }

    // public void setDuedate(LocalDate duedate) {
    // this.duedate = duedate;
    // }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project2) {
        this.project = project2;
    }

    // public Boolean getAssignedstatus() {
    // return assignedstatus;
    // }

    // public void setAssignedstatus(Boolean assignedstatus) {
    // this.assignedstatus = assignedstatus;
    // }

    public Users getMember() {
        return member;
    }

    public void setMember(Users member) {
        this.member = member;
    }
}