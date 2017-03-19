package com.leftjs.lfc;

import com.leftjs.lfc.model.domain.Admin;
import com.leftjs.lfc.repository.AdminRepository;
import com.leftjs.lfc.utils.XmlParseUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by jason on 2017/3/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AdminRepository adminRepository;

    private MockMvc mvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {
        this.document = MockMvcRestDocumentation.document("{method-name}", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.document)
                .build();
    }



    @Test
    public void createAdmin() throws Exception {

        Admin admin = new Admin("200admin", "200admin_password");
        String xmlString = XmlParseUtils.objectToXmlString(admin);
        this.mvc.perform(MockMvcRequestBuilders.post("/api/admins")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .content(xmlString))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void getAdmin() throws Exception {
        Admin admin = adminRepository.save(new Admin("300admin", "300admins_password"));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/admins/{id}", Long.toString(admin.getId()))

                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateAdmin() throws Exception {
        Admin admin = adminRepository.save(new Admin("600admin", "600admins_password"));
        admin.setUsername("501");
        admin.setPassword("501s_password");
        this.mvc.perform(MockMvcRequestBuilders.put("/api/admins/{id}", Long.toString(admin.getId()))
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML)
                .content(XmlParseUtils.objectToXmlString(admin)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void listAdmin() throws Exception {
        adminRepository.save(new Admin("400admin", "400admins_password"));
        adminRepository.save(new Admin("500admin", "500admins_password"));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/admins?page=0&size=10")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteAdmin() throws  Exception {
        Admin admin = adminRepository.save(new Admin("700admin", "700admins_password"));
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/admins/{id}", Long.toString(admin.getId())))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
