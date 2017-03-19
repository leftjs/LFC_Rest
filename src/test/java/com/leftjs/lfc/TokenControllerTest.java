package com.leftjs.lfc;

import com.leftjs.lfc.model.domain.Admin;
import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.repository.AdminRepository;
import com.leftjs.lfc.repository.ClerkRepository;
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
 * Created by jason on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ClerkRepository clerkRepository;

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
    public void clerkLogin() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("clerkLoginUser", "clerkLoginUsers_password"));
        clerk.setId(null);
        this.mvc.perform(MockMvcRequestBuilders.post("/api/token/clerk")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .content(XmlParseUtils.objectToXmlString(clerk))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void adminLogin() throws  Exception {
        Admin admin = adminRepository.save(new Admin("adminLoginUser", "adminLoginUser_password"));
        admin.setId(null);
        this.mvc.perform(MockMvcRequestBuilders.post("/api/token/admin")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .content(XmlParseUtils.objectToXmlString(admin))
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
