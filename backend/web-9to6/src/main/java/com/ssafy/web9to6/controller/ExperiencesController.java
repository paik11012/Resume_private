package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.Experiences;
import com.ssafy.web9to6.domain.Licenses;
import com.ssafy.web9to6.dto.AwardsResponseDto;
import com.ssafy.web9to6.dto.ExperiencesResponseDto;
import com.ssafy.web9to6.dto.LicensesResponseDto;
import com.ssafy.web9to6.service.AwardsService;
import com.ssafy.web9to6.service.ExperiencesService;
import com.ssafy.web9to6.service.LicensesService;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
public class ExperiencesController {
    @Autowired
    private AwardsService as;
    @Autowired
    private UsersService us;
    @Autowired
    private ExperiencesService ex;
    @Autowired
    private LicensesService ls;

    @ApiOperation("수상내역 조회")
    @RequestMapping(value = "/awards", method = RequestMethod.GET)
    public List<Awards> findAllAwards(HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        return as.findAll(us.findById(user_id));
    }

    @ApiOperation("수상 정보 저장")
    @RequestMapping(value = "/awards/save", method = RequestMethod.POST)
    public Awards saveAwards(HttpSession session,@RequestBody AwardsResponseDto awd) {
        String user_id = (String)session.getAttribute("user_id");
        awd.setUser(us.findById(user_id));
        return as.save(awd);
    }

    @ApiOperation("수상정보 1개 삭제")
    @RequestMapping(value = "/awards/del/{id}", method = RequestMethod.DELETE)
    public void deleteAwards(HttpSession session, @PathVariable String id) {
        Long aid = Long.parseLong(id);
        as.delete(aid);
    }

    @ApiOperation("수상 정보 수정")
    @RequestMapping(value = "/awards/update", method = RequestMethod.PUT)
    public Awards updateAwards(HttpSession session, @RequestBody AwardsResponseDto awd) {
        String user_id = (String)session.getAttribute("user_id");
        awd.setUser(us.findById(user_id));
        return as.save(awd);
    }

    @ApiOperation("경험 내역 전체 조회")
    @RequestMapping(value = "/exp", method = RequestMethod.GET)
    public List<Experiences> findAllExperiences(HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        return ex.findAll(us.findById(user_id));
    }

    @ApiOperation("경험 정보 저장")
    @RequestMapping(value = "/exp/save", method = RequestMethod.POST)
    public Experiences saveExperiences(HttpSession session,@RequestBody ExperiencesResponseDto exp) {
        String user_id = (String)session.getAttribute("user_id");
        exp.setUser(us.findById(user_id));
        return ex.save(exp);
    }

    @ApiOperation("경험 정보 1개 삭제")
    @RequestMapping(value = "/exp/del/{id}", method = RequestMethod.DELETE)
    public void deleteExperiences(HttpSession session, @PathVariable String id) {
        Long aid = Long.parseLong(id);
        ex.delete(aid);
    }

    @ApiOperation("경험 정보 수정")
    @RequestMapping(value = "/exp/update", method = RequestMethod.PUT)
    public Experiences updateExperiences(HttpSession session, @RequestBody ExperiencesResponseDto exp) {
        String user_id = (String)session.getAttribute("user_id");
        exp.setUser(us.findById(user_id));
        return ex.save(exp);
    }

    @ApiOperation("자격내역 조회")
    @RequestMapping(value = "/lic", method = RequestMethod.GET)
    public List<Licenses> findAllLicenses(HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        return ls.findAll(us.findById(user_id));
    }

    @ApiOperation("자격 정보 저장")
    @RequestMapping(value = "/lic/save", method = RequestMethod.POST)
    public Licenses saveLicenses(HttpSession session, @RequestBody LicensesResponseDto lic) {
        String user_id = (String)session.getAttribute("user_id");
        lic.setUser(us.findById(user_id));
        return ls.save(lic);
    }

    @ApiOperation("자격 정보 1개 삭제")
    @RequestMapping(value = "/lic/del/{id}", method = RequestMethod.DELETE)
    public void deleteLicense(HttpSession session, @PathVariable String id) {
        Long aid = Long.parseLong(id);
        ls.delete(aid);
    }

    @ApiOperation("자격 정보 수정")
    @RequestMapping(value = "/lic/update", method = RequestMethod.PUT)
    public Licenses updateLicenses(HttpSession session, @RequestBody LicensesResponseDto lic) {
        String user_id = (String) session.getAttribute("user_id");
        lic.setUser(us.findById(user_id));
        return ls.save(lic);
    }
}
