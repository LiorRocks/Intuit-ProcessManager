package com.intuit.processmanager.repository;

import com.intuit.processmanager.model.ListPreferenceEnum;
import com.intuit.processmanager.model.PriorityEnum;
import com.intuit.processmanager.model.Process;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProcessRepository {
    private List<Process> processList;
    private List<Process> processesByPid;
    private Map<PriorityEnum, List<Process>> runtimeQueues;
    private Map<String, Process> processMap;

    public ProcessRepository() {
        processList = new ArrayList<>();
        runtimeQueues = new HashMap<>();
        runtimeQueues.put(PriorityEnum.HIGH, new ArrayList<>());
        runtimeQueues.put(PriorityEnum.MEDIUM, new ArrayList<>());
        runtimeQueues.put(PriorityEnum.LOW, new ArrayList<>());
        processMap = new HashMap<>();
    }

    public void addProcess(Process process) {
        addProcessFifo(process);
    }

    public void addProcessFifo(Process process) {
        processMap.put(process.getPid(), process);
        processList.add(process);
        runtimeQueues.get(process.getPriority()).add(process);
    }

    public void addProcessPriority(Process process) {
        addProcessFifo(process);
    }

    public List<Process> listRunningProcesses(ListPreferenceEnum listPreferenceEnum, PriorityEnum priorityEnum) {
        switch (listPreferenceEnum) {
            case PID: {
                processesByPid = processList.stream().collect(Collectors.toList());
                processesByPid.sort(Comparator.comparing(Process::getPid));
                return processesByPid;
            }
            case PRIORITY: return runtimeQueues.get(priorityEnum);
            case CREATION_TIME: return processList;
        }
        return null;
    }



    public void killProcess(String pid) {
        Process originalProcess = processMap.get(pid);
        processList = processList.stream().filter(process -> !process.getPid().equals(pid)).collect(Collectors.toList());
        runtimeQueues.put(originalProcess.getPriority(), runtimeQueues.get(originalProcess.getPriority()).stream().filter(process -> !process.getPid().equals(pid)).collect(Collectors.toList()));
    }

    public void killProcessGroup(PriorityEnum priorityEnum) {
        List<Process> processGroup = runtimeQueues.get(priorityEnum);
        processList.removeAll(processGroup);
        processGroup.stream().forEach(processMap::remove);
    }

    public void killAllProcesses() {
        this.killProcessGroup(PriorityEnum.HIGH);
        this.killProcessGroup(PriorityEnum.MEDIUM);
        this.killProcessGroup(PriorityEnum.LOW);
    }
}
