package com.intuit.processmanager.controller;

import com.intuit.processmanager.model.ListPreferenceEnum;
import com.intuit.processmanager.model.PriorityEnum;
import com.intuit.processmanager.model.Process;
import com.intuit.processmanager.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @PostMapping("/process/add")
    @ResponseBody
    public void addProcess(@RequestBody Process process) {
        this.processService.addProcess(process);
    }

    @GetMapping("/process/list/{listPreferenceEnum}/{priorityEnum}")
    @ResponseBody
    public List<Process> listRunningProcesses(@PathVariable ListPreferenceEnum listPreferenceEnum, @PathVariable PriorityEnum priorityEnum) {
        return this.processService.listRunningProcesses(listPreferenceEnum, priorityEnum);
    }

    @GetMapping("/process/kill/{pid}")
    @ResponseBody
    public void killProcess(@PathVariable String pid) {
        this.processService.killProcess(pid);
    }

    @GetMapping("/process/kill_process_group/{priorityEnum}")
    @ResponseBody
    public void killProcessGroup(@PathVariable PriorityEnum priorityEnum) {
        this.processService.killProcessGroup(priorityEnum);
    }

    @GetMapping("/process/kill_all_processes")
    public void killAllProcesses() {
        this.processService.killAllProcesses();
    }
}
