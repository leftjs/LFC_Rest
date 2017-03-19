package com.leftjs.lfc;

import com.leftjs.lfc.auth.manager.TokenManager;
import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.model.domain.Insurance;
import com.leftjs.lfc.repository.ClerkRepository;
import com.leftjs.lfc.repository.insurance.InsuranceRepository;
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

import java.util.Date;

/**
 * Created by jason on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsuranceControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ClerkRepository clerkRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

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


    private String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<ROOT> \n" +
            "  <INFO> \n" +
            "    <HOLDER_NAME>杨康</HOLDER_NAME>  \n" +
            "    <SEX>M</SEX>  \n" +
            "    <BIRTH_DATE>1970-08-09</BIRTH_DATE>  \n" +
            "    <MOBILE>13819223888</MOBILE>  \n" +
            "    <POL_NAME>意外险</POL_NAME>  \n" +
            "    <MONEY>200</MONEY>  \n" +
            "  </INFO>  \n" +
            "  <INSUREDS> \n" +
            "    <INSURED> \n" +
            "      <REL>父子</REL>  \n" +
            "      <INSURE_NAME>杨过</INSURE_NAME> \n" +
            "    </INSURED>  \n" +
            "    <INSURED> \n" +
            "      <REL>夫妻</REL>  \n" +
            "      <INSURE_NAME>小龙女</INSURE_NAME> \n" +
            "    </INSURED> \n" +
            "  </INSUREDS> \n" +
            "</ROOT>";


    @Test
    public void createInsurance() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance1", "testInsurance_password"));
        String token = TokenManager.createToken("clerk:" + clerk.getId()).getToken();

        this.mvc.perform(MockMvcRequestBuilders.post("/api/insurances")
                .contentType(MediaType.APPLICATION_XML)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_XML)
                .content(xmlString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void listInsurances() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance2", "testInsurance_password"));
        Insurance insurance = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerk);
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceRepository.save(insuranceInDB);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/insurances?page={page}&size={size}", 0, 10)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getInsuranceById() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance3", "testInsurance_password"));
        Insurance insurance = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerk);
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceRepository.save(insuranceInDB);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/insurances/{id}", insuranceInDB.getId())
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void updateInsuranceById() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance4", "testInsurance_password"));

        Insurance insurance = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerk);
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceInDB = insuranceRepository.save(insuranceInDB);


        this.mvc.perform(MockMvcRequestBuilders.put("/api/insurances/{id}", insuranceInDB.getId())
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML)
                .content(XmlParseUtils.objectToXmlString(insuranceInDB)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deleteInsuranceById() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance5", "testInsurance_password"));

        Insurance insurance = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerk);
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceInDB = insuranceRepository.save(insuranceInDB);

        this.mvc.perform(MockMvcRequestBuilders.delete("/api/insurances/{id}", insuranceInDB.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    @Test
    public void listInsurancesByClerk() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance6", "testInsurance_password"));


        Insurance insurance = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerk);
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceRepository.save(insuranceInDB);

        Insurance insurance2 = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB2 = insuranceRepository.save(insurance2);
        insuranceInDB2.getInfo().setClerk(clerk);
        insuranceInDB2.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB2.getInfo().setBaodanNo("baodan" + insuranceInDB2.getInfo().getId()); // 设置保单号
        insuranceRepository.save(insuranceInDB2);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/insurances/query?clerk={clerkId}", clerk.getClerkId())
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void listInsurancesByHolder() throws Exception {
        Clerk clerk = clerkRepository.save(new Clerk("testInsurance7", "testInsurance_password"));


        Insurance insurance = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerk);
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceRepository.save(insuranceInDB);

        Insurance insurance2 = (Insurance) XmlParseUtils.xmlStringToObject(xmlString, Insurance.class);
        Insurance insuranceInDB2 = insuranceRepository.save(insurance2);
        insuranceInDB2.getInfo().setClerk(clerk);
        insuranceInDB2.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB2.getInfo().setBaodanNo("baodan" + insuranceInDB2.getInfo().getId()); // 设置保单号
        insuranceRepository.save(insuranceInDB2);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/insurances/query?holder={holderName}", insuranceInDB.getInfo().getHolderName())
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }





}
