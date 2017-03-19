package com.leftjs.lfc;

import com.leftjs.lfc.model.domain.Clerk;
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
 * Created by jason on 2017/3/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClerkControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ClerkRepository clerkRepository;

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
    public void createClerk() throws Exception {

        Clerk clerk = new Clerk("200", "200_password");
        String xmlString = XmlParseUtils.objectToXmlString(clerk);
        this.mvc.perform(MockMvcRequestBuilders.post("/api/clerks")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .content(xmlString))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void getClerk() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("300", "300s_password"));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/clerks/{id}", Long.toString(clerk.getId()))

                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateClerk() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("600", "600s_password"));
        clerk.setClerkId("501");
        clerk.setPassword("501s_password");
        this.mvc.perform(MockMvcRequestBuilders.put("/api/clerks/{id}", Long.toString(clerk.getId()))
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML)
                .content(XmlParseUtils.objectToXmlString(clerk)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void listClerk() throws Exception {
        clerkRepository.save(new Clerk("400", "400s_password"));
        clerkRepository.save(new Clerk("500", "500s_password"));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/clerks?page=0&size=10")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteClerk() throws  Exception {
        Clerk clerk = clerkRepository.save(new Clerk("700", "700s_password"));
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/clerks/{id}", Long.toString(clerk.getId())))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
