package com.intuit.processmanager.repository;

import com.intuit.processmanager.model.ListPreferenceEnum;
import com.intuit.processmanager.model.PriorityEnum;
import com.intuit.processmanager.model.Process;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProcessRepositoryTest {

    @Mock
    private ProcessRepository processRepository;

    @Test
    public void addProcessTest() {
        List<Process> list = new ArrayList<Process>();
        Process proc1 = new Process("1", PriorityEnum.LOW);
        Process proc2 = new Process("2", PriorityEnum.HIGH);
        Process proc3 = new Process("3", PriorityEnum.LOW);

        list.add(proc1);
        list.add(proc2);
        list.add(proc3);

        when(processRepository.listRunningProcesses(ListPreferenceEnum.CREATION_TIME,null)).thenReturn(list);

        //test
        List<Process> procList = processRepository.listRunningProcesses(ListPreferenceEnum.CREATION_TIME, null);

        assertEquals(3, procList.size());
        assertEquals("1", procList.get(0).getPid());
    }
}
