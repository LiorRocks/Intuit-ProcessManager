package com.intuit.processmanager.service;

import com.intuit.processmanager.model.ListPreferenceEnum;
import com.intuit.processmanager.model.PriorityEnum;
import com.intuit.processmanager.model.Process;
import com.intuit.processmanager.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    public void addProcess(Process process) {
        this.processRepository.addProcess(process);
    }

    public void addProcessFifo(Process process) {
        this.processRepository.addProcessFifo(process);
    }

    public void addProcessPriority(Process process) {
        this.processRepository.addProcessPriority(process);
    }


    public List<Process> listRunningProcesses(ListPreferenceEnum listPreferenceEnum, PriorityEnum priorityEnum) {
        return this.processRepository.listRunningProcesses(listPreferenceEnum, priorityEnum);
    }

    public void killProcess(String pid) {
        this.processRepository.killProcess(pid);
    }

    public void killProcessGroup(PriorityEnum priorityEnum) {
        this.processRepository.killProcessGroup(priorityEnum);
    }

    public void killAllProcesses() {
        this.processRepository.killAllProcesses();
    }
}
