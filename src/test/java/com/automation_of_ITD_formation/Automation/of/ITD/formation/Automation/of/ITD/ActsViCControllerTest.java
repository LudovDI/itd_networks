package com.automation_of_ITD_formation.Automation.of.ITD.formation.Automation.of.ITD;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ActsViCData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ActsViCRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ActsViCControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActsViCRepository actsViCRepository;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetActsVicTable() throws Exception {
        mockMvc.perform(get("/acts-vic-table"))
                .andExpect(status().isOk())
                .andExpect(view().name("actsVicTable"))
                .andExpect(model().attributeExists("actsViCList"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testPostActsVicAdd() throws Exception {
        mockMvc.perform(post("/acts-vic-add")
                        .param("numberActViC", "123")
                        .param("dataActViC", "01.01.2023")
                        .param("nameLaboratory", "Lab"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/acts-vic-table"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testPostActsVicDelete() throws Exception {
        long id = 1L;

        ActsViCData actsViCData = new ActsViCData("123", "01.01.2023", "Lab");
        actsViCData.setId(id);
        when(actsViCRepository.findById(id)).thenReturn(Optional.of(actsViCData));

        mockMvc.perform(post("/acts-vic-table/{id}/acts-vic-remove", id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/acts-vic-table"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetActsVicEditPage() throws Exception {
        long id = 1L;
        ActsViCData act = new ActsViCData("234", "02.02.2023", "NewLab");
        act.setId(id);
        when(actsViCRepository.findById(id)).thenReturn(Optional.of(act));

        mockMvc.perform(get("/acts-vic-table/{id}/acts-vic-edit", id))
                .andExpect(status().isOk())
                .andExpect(view().name("actsVicEdit"))
                .andExpect(model().attributeExists("actsVicDataOptional"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetActsVicEditPageActNotFound() throws Exception {
        long id = 1L;
        when(actsViCRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/acts-vic-table/{id}/acts-vic-edit", id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/acts-vic-table"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testPostActsVicUpdate() throws Exception {
        long id = 1L;
        String numberActViC = "234";
        String dataActViC = "02.02.2023";
        String nameLaboratory = "NewLab";

        mockMvc.perform(post("/acts-vic-table/{id}/acts-vic-edit", id)
                        .param("numberActViC", numberActViC)
                        .param("dataActViC", dataActViC)
                        .param("nameLaboratory", nameLaboratory))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/acts-vic-table"));

        verify(actsViCRepository).save(any(ActsViCData.class));
    }
}
